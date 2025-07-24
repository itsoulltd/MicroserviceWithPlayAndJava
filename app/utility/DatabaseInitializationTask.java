package utility;

import com.infoworks.lab.beans.tasks.nuts.ExecutableTask;
import com.infoworks.lab.rest.models.Message;
import com.infoworks.lab.rest.models.Response;
import com.it.soul.lab.connect.io.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Application;
import play.db.Database;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Singleton
public class DatabaseInitializationTask extends ExecutableTask<Message, Response> {

    private static Logger LOG = LoggerFactory.getLogger(DatabaseInitializationTask.class);
    private Application application;
    private Database db;

    @Inject
    public DatabaseInitializationTask(Application application, Database db) {
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
                LOG.debug("Script Filepath: " + file.get().getAbsolutePath());
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
