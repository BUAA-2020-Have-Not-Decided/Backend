package cn.edu.buaa.scholarshipserver.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UtilTest {

    @Test
    public void checkDate() {
        System.out.println(new Date());
    }
}
