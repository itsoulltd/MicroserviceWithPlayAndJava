package utility;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

public class ResponseEntity<E> {

    private static <T> void updateObjectNode(ObjectNode result, T body) {
        if (body instanceof String) {
            result.put("body", (String) body);
        } else {
            result.putPOJO("body", body);
        }
    }

    public static <T> Result ok(T body) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", true);
        result.put("status", 200);
        updateObjectNode(result, body);
        return Results.ok(result);
    }

    public static <T> Result created(T body) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", true);
        result.put("status", 201);
        updateObjectNode(result, body);
        return Results.created(result);
    }

    public static Result unauthorized(String...message) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 401);
        result.put("reason", String.join("; ", message));
        return Results.unauthorized(result);
    }

    public static Result badRequest(String...message) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 400);
        result.put("reason", String.join("; ", message));
        return Results.badRequest(result);
    }

    public static Result notFound(String...message) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 404);
        result.put("reason", String.join("; ", message));
        return Results.notFound(result);
    }

    public static <T> Result internalServerError(T error) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 500);
        if (error instanceof Throwable) {
            result.put("error", ((Exception) error).getMessage());
        } else {
            result.put("error", error.toString());
        }
        return Results.internalServerError(result);
    }

}
