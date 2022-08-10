package as.florenko.transfer.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

public class Response {

    public ResponseEntity<Object> generateWrongResponse(String message, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("id", status.value());
        System.out.println(map);
        return new ResponseEntity<>(map, status);
    }

    public ResponseEntity<Object> generateOkResponse(Integer operationId, HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("operationId", String.valueOf(operationId));
        System.out.println(map);
        return new ResponseEntity<>(map, status);
    }
}
