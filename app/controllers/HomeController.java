package controllers;

import com.infoworks.lab.rest.models.Response;
import play.mvc.Controller;
import play.mvc.Result;
import utility.InitializeDatabaseTask;

import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private InitializeDatabaseTask task;

    @Inject
    public HomeController(InitializeDatabaseTask task) {
        this.task = task;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        System.out.println("Start UP...");
        Response response = this.task.execute(null);
        System.out.println(response.getMessage());
        return ok(views.html.index.render());
    }

}
