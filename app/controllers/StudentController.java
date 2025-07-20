package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.entities.Student;
import domain.repositories.StudentRepository;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import utility.ResponseEntity;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class StudentController {

    private StudentRepository repository;

    @Inject
    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    public Result retrieve(int id) {
        final Optional<Student> studentOptional = repository.findById(id);
        return studentOptional.map(student -> {
            JsonNode jsonObjects = Json.toJson(student);
            return ResponseEntity.ok(jsonObjects);
        }).orElse(ResponseEntity.notFound("Student with id:" + id + " not found"));
    }

    public Result retrieveAll() {
        List<Student> result = repository.findAll(1, 10);
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
        Student student = Json.fromJson(json, Student.class);
        //TODO:Bean-Validation
        //Save:
        Optional<Student> saved = repository.save(student);
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
        Student student = Json.fromJson(json, Student.class);
        //TODO:Bean-Validation
        //Update:
        Optional<Student> updated = repository.update(student);
        return updated.map(upd -> {
            if (upd == null) {
                return ResponseEntity.notFound("Student not found");
            }
            JsonNode jsonObject = Json.toJson(upd);
            return ResponseEntity.ok(jsonObject);
        }).orElse(ResponseEntity.internalServerError("Could not create data."));
    }

    public Result delete(int id) {
        boolean status = repository.delete(id);
        if (!status) {
            return ResponseEntity.notFound("Student with id:" + id + " not found");
        }
        return ResponseEntity.ok("Student with id:" + id + " deleted");
    }
}
