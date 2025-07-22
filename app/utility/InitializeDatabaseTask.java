package utility;

import com.infoworks.lab.beans.tasks.nuts.ExecutableTask;
import com.infoworks.lab.rest.models.Message;
import com.infoworks.lab.rest.models.Response;
import com.it.soul.lab.connect.io.ScriptRunner;
import play.Application;
import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class InitializeDatabaseTask extends ExecutableTask<Message, Response> {

    private Application application;
    private Database db;

    @Inject
    public InitializeDatabaseTask(Application application, Database db) {
        this.application = application;
        this.db = db;
    }

    @Override
    public Response execute(Message message) throws RuntimeException {
        Response response = new Response().setStatus(200);
        if (application.isDev()){
            String filename = application.config().getString("app.db.init.file");
            Optional<File> file = application.environment().getExistingFile(filename);
            if(file.isPresent()) {
                System.out.println(file.get().getAbsolutePath());
                try(InputStream stream = new FileInputStream(file.get());
                    Connection connection = db.getConnection()) {
                    //Execute:
                    ScriptRunner runner = new ScriptRunner();
                    String[] cmds = runner.commands(stream);
                    runner.execute(cmds, connection);
                    response.setMessage("DEV MODE: Database initialized.");
                } catch (SQLException | FileNotFoundException e) {
                    response.setError(e.getMessage()).setStatus(500);
                } catch (IOException e) {
                    response.setError(e.getMessage()).setStatus(500);
                }
            }
        } else {
            response.setMessage("PROD MODE: In Execution.");
        }
        return response;
    }
}
