package cn.edu.buaa.scholarshipserver.test;

import cn.edu.buaa.scholarshipserver.dao.FieldDao;
import cn.edu.buaa.scholarshipserver.services.scholarship.FieldService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HZYTest {

    @Autowired
    private FieldService fieldService;
    @Autowired
    private FieldDao fieldDao;

    @Test
    public void getHotFields() {
        System.out.println(fieldService.getHotFields());
    }
}
