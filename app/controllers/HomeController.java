package controllers;

import com.infoworks.objects.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Result;
import utility.DatabaseInitializationTask;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private static Logger LOG = LoggerFactory.getLogger(HomeController.class);
    private DatabaseInitializationTask task;

    @Inject
    public HomeController(DatabaseInitializationTask task) {
        this.task = task;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        LOG.info("Start UP...");
        Response response = this.task.execute(null);
        LOG.info(response.getMessage());
        return ok(views.html.index.render());
    }

}
