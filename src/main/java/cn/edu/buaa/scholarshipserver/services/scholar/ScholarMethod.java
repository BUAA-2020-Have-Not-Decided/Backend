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
        /*
        public ResponseEntity<Response> updateScholar(Integer id){
                Scholar scholar =new Scholar();
                scholarDao.save(scholar);

                return  ResponseEntity.ok(new Response(1));
        }

        public ResponseEntity<Response> getScholarByUserName(String username){
                List<Scholar> scholarList = new ArrayList<>();
                scholarList = scholarDao.findByName(username);
                return ResponseEntity.ok(new Response(scholarList));
        }*/
}



