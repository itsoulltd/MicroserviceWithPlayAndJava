package utility;

import filters.RoutedLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.http.EnabledFilters;
import play.filters.cors.CORSFilter;
import play.http.DefaultHttpFilters;
import play.mvc.EssentialFilter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Filters extends DefaultHttpFilters {

    private static Logger LOG = LoggerFactory.getLogger(Filters.class);

    @Inject
    public Filters(EnabledFilters enabledFilters
            , CORSFilter corsFilter
            , RoutedLoggingFilter loggingFilter) {
        super(combine(enabledFilters.asJava().getFilters(), corsFilter.asJava(), loggingFilter));
    }

    private static List<EssentialFilter> combine(List<EssentialFilter> filters, EssentialFilter...toAppend) {
        LOG.info("Filters combine loading...");
        List<EssentialFilter> combinedFilters = new ArrayList<>(filters);
        combinedFilters.addAll(Arrays.asList(toAppend));
        return combinedFilters;
    }
}
