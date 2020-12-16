package cn.edu.buaa.scholarshipserver.test;

import cn.edu.buaa.scholarshipserver.dao.PaperDao;
import cn.edu.buaa.scholarshipserver.es.Paper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ESTest {

    @Autowired
    private PaperDao paperDao;

    @Test
    public void getPaper() {
        long id = 2892786837L;
        Paper paper = paperDao.findByPaperId(id);
        System.out.println(paper);
    }
}
