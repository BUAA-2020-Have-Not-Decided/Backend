package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.services.scholarship.HelloWorld;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipService {

    HelloWorld helloWorld;

    ScholarshipService(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public ResponseEntity<Response> getHello() {
        return helloWorld.hello();
    }
}
