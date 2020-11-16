package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.services.scholarship.HelloWorld;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipService {

    HelloWorld helloWorld;

    ScholarshipService(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public String getHello() {
        return helloWorld.hello();
    }
}
