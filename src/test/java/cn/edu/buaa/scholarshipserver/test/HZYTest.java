package cn.edu.buaa.scholarshipserver.test;

import cn.edu.buaa.scholarshipserver.dao.FieldDao;
import cn.edu.buaa.scholarshipserver.dao.PatentDao;
import cn.edu.buaa.scholarshipserver.dao.ProjectDao;
import cn.edu.buaa.scholarshipserver.services.scholarship.FieldService;
import cn.edu.buaa.scholarshipserver.services.scholarship.InstitutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HZYTest {

    @Autowired
    private FieldService fieldService;
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private PatentDao patentDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private InstitutionService institutionService;

    @Test
    public void getHotFields() {
        System.out.println(fieldService.getHotFields());
    }

    @Test
    public void getPatentAndProject() {
        System.out.println(patentDao.findById(1L).orElse(null));
        System.out.println(projectDao.findById(3682L).orElse(null));
    }

    @Test
    public void topInstitutionTest() {
        System.out.println(institutionService.getTopInstitution());
    }

    @Test
    public void getTopInstitutionByField() {
        System.out.println(institutionService.getTopInstitutionByField(154945302L));
    }
}
