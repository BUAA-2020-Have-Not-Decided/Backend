package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HelloWorld {

    public ResponseEntity<Response> hello() {
        Response response = new Response("Hello, world!");
        return ResponseEntity.ok(response);
    }
}
