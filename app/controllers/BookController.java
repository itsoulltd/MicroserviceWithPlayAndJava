package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import domain.models.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.BookService;
import utility.ResponseEntity;
import utility.ValidationConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class BookController extends Controller {

    private static Logger LOG = LoggerFactory.getLogger(BookController.class);
    private BookService bookService;

    @Inject
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Result count() {
        long count = bookService.count();
        ObjectNode result = Json.newObject();
        result.put("count", count);
        return ResponseEntity.ok(result);
    }

    public Result retrieve(long id) {
        final Optional<BookDTO> bookOptional = bookService.findById(id);
        return bookOptional.map(book -> {
            JsonNode jsonObjects = Json.toJson(book);
            return ResponseEntity.ok(jsonObjects);
        }).orElse(ResponseEntity.notFound("Book with id:" + id + " not found"));
    }

    public Result retrieveAll(int page, int limit) {
        List<BookDTO> result = bookService.findAll(page, limit);
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
        BookDTO book = Json.fromJson(json, BookDTO.class);
        //Bean-Validation
        String messages = ValidationConfig.validateWithMessage(book);
        if (messages != null) {
            return ResponseEntity.badRequest(messages);
        }
        //Save:
        Optional<BookDTO> saved = bookService.save(book);
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
        BookDTO book = Json.fromJson(json, BookDTO.class);
        //Bean-Validation
        String messages = ValidationConfig.validateWithMessage(book);
        if (messages != null) {
            return ResponseEntity.badRequest(messages);
        }
        //Update:
        Optional<BookDTO> updated = bookService.update(book);
        return updated.map(upd -> {
            JsonNode jsonObject = Json.toJson(upd);
            return ResponseEntity.ok(jsonObject);
        }).orElse(ResponseEntity.notFound("Book with id:" + book.getId() + " not found"));
    }

    public Result delete(long id) {
        boolean status = bookService.delete(id);
        if (!status) {
            return ResponseEntity.notFound("Book with id:" + id + " not found");
        }
        return ResponseEntity.ok("Book with id:" + id + " deleted");
    }
}
