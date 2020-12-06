package cn.edu.buaa.scholarshipserver.services.scholar;

import cn.edu.buaa.scholarshipserver.dao.ScholarDao;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScholarMethod {
        @Autowired
        private ScholarDao scholarDao;
        public Scholar getScholarById(Integer id){
                return  scholarDao.findByScholarId(id);
        }
        public Scholar updateScholar(Scholar scholar){
                return scholarDao.save(scholar);
        }

}



