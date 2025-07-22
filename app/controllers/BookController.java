package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import domain.entities.Book;
import domain.repositories.BookRepository;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utility.ResponseEntity;
import utility.ValidationConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class BookController extends Controller {

    private BookRepository repository;

    @Inject
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    public Result count() {
        long count = repository.count();
        ObjectNode result = Json.newObject();
        result.put("count", count);
        return ResponseEntity.ok(result);
    }

    public Result retrieve(long id) {
        final Optional<Book> studentOptional = repository.findById(id);
        return studentOptional.map(student -> {
            JsonNode jsonObjects = Json.toJson(student);
            return ResponseEntity.ok(jsonObjects);
        }).orElse(ResponseEntity.notFound("Book with id:" + id + " not found"));
    }

    public Result retrieveAll(int page, int limit) {
        List<Book> result = repository.findAll(page, limit);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ResponseEntity.ok(jsonData);
    }

    public Result create(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return ResponseEntity.badRequest("Expecting Json data");
        }
        //Deserialize Object from Json:
        Book book = Json.fromJson(json, Book.class);
        //Bean-Validation
        String messages = ValidationConfig.validateWithMessage(book);
        if (messages != null) {
            return ResponseEntity.badRequest(messages);
        }
        //Save:
        Optional<Book> saved = repository.save(book);
        return saved.map(svd -> {
            JsonNode jsonObject = Json.toJson(svd);
            return ResponseEntity.created(jsonObject);
        }).orElse(ResponseEntity.internalServerError("Could not create data."));
    }

    public Result update(Http.Request request) {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return ResponseEntity.badRequest("Expecting Json data");
        }
        //Deserialize Object from Json:
        Book book = Json.fromJson(json, Book.class);
        //Bean-Validation
        String messages = ValidationConfig.validateWithMessage(book);
        if (messages != null) {
            return ResponseEntity.badRequest(messages);
        }
        //Update:
        Optional<Book> updated = repository.update(book);
        return updated.map(upd -> {
            if (upd == null) {
                return ResponseEntity.notFound("Book not found");
            }
            JsonNode jsonObject = Json.toJson(upd);
            return ResponseEntity.ok(jsonObject);
        }).orElse(ResponseEntity.internalServerError("Could not create data."));
    }

    public Result delete(long id) {
        boolean status = repository.delete(id);
        if (!status) {
            return ResponseEntity.notFound("Book with id:" + id + " not found");
        }
        return ResponseEntity.ok("Book with id:" + id + " deleted");
    }
}
