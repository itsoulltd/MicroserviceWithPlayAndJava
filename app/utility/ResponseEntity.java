package utility;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class ResponseEntity<E> {

    private static <T> void updateObjectNode(ObjectNode result, T body) {
        if (body instanceof String) {
            result.put("body", (String) body);
        } else {
            result.putPOJO("body", body);
        }
    }

    public static <T> ObjectNode ok(T body) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", true);
        result.put("status", 200);
        updateObjectNode(result, body);
        return result;
    }

    public static <T> ObjectNode created(T body) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", true);
        result.put("status", 201);
        updateObjectNode(result, body);
        return result;
    }

    public static ObjectNode unauthorized() {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 401);
        //TODO:
        return result;
    }

    public static ObjectNode badRequest() {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 400);
        return result;
    }

    public static ObjectNode notFound() {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 404);
        return result;
    }

    public static <T> ObjectNode internalServerError(T error) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", false);
        result.put("status", 500);
        if (error instanceof Throwable) {
            result.put("error", ((Exception) error).getMessage());
        } else {
            result.put("error", error.toString());
        }
        return result;
    }

}
