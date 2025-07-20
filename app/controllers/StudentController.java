package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import domain.entities.Student;
import domain.repositories.StudentRepository;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import utility.ResponseEntity;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static play.mvc.Results.notFound;
import static play.mvc.Results.ok;

public class StudentController {

    private StudentRepository repository;

    @Inject
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    private void check() {
        ObjectNode node = ResponseEntity.ok("Message Okay!");
        ObjectNode badRequest = ResponseEntity.badRequest();
        ObjectNode error = ResponseEntity.internalServerError(new Exception("Internal Server Error Message"));
    }

    public Result retrieve(int id) {
        final Optional<Student> studentOptional = repository.findById(id);
        return studentOptional.map(student -> {
            JsonNode jsonObjects = Json.toJson(student);
            return ok(ResponseEntity.ok(jsonObjects));
        }).orElse(notFound(ResponseEntity.notFound("Student with id:" + id + " not found")));
    }

    public Result retrieveAll() {
        List<Student> result = repository.findAll(1, 10);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
        return ok(ResponseEntity.ok(jsonData));
    }

    public Result create(Http.Request request) {
        return ok(ResponseEntity.ok("UnderConstruction"));
    }

    public Result update(Http.Request request) {
        return ok(ResponseEntity.ok("UnderConstruction"));
    }

    public Result delete(int id) {
        return ok(ResponseEntity.ok("UnderConstruction"));
    }
}
