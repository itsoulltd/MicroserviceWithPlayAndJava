package filters;

import org.apache.pekko.stream.Materializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;
import play.routing.HandlerDef;
import play.routing.Router;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

@Singleton
public class RoutedLoggingFilter extends Filter {

    private static final Logger LOG = LoggerFactory.getLogger(RoutedLoggingFilter.class);

    @Inject
    public RoutedLoggingFilter(Materializer mat) {
        super(mat);
    }

    @Override
    public CompletionStage<Result> apply(
            Function<Http.RequestHeader, CompletionStage<Result>> nextFilter
            , Http.RequestHeader requestHeader) {
        long startTime = System.currentTimeMillis();
        return nextFilter
                .apply(requestHeader)
                .thenApply(
                        result -> {
                            HandlerDef handlerDef = requestHeader.attrs().get(Router.Attrs.HANDLER_DEF);
                            String actionMethod = handlerDef.controller() + "." + handlerDef.method();

                            long endTime = System.currentTimeMillis();
                            long requestTime = endTime - startTime;
                            LOG.info("{} took {}ms and returned {}", actionMethod, requestTime, result.status());

                            return result.withHeader("Request-Time", "" + requestTime);
                        });
    }
}
