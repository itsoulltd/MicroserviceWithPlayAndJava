package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import domain.repositories.StudentRepository;
import play.core.j.ClassLoaderExecutionContext;
import utility.ResponseEntity;

import javax.inject.Inject;

public class StudentController {

    private ClassLoaderExecutionContext ec;
    private StudentRepository repository;

    @Inject
    public StudentController(ClassLoaderExecutionContext ec, StudentRepository repository) {
        this.ec = ec;
        this.repository = repository;
    }

    private void check() {
        ObjectNode node = ResponseEntity.ok("Message Okay!");
        ObjectNode error = ResponseEntity.internalServerError(new Exception("Internal Server Error Message"));
    }
}
