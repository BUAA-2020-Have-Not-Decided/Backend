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

        public Scholar getScholarById(Long id){
                return  scholarDao.findByScholarId(id);
        }

        public Scholar updateScholar(Scholar scholar){
                return scholarDao.save(scholar);
        }

        public List<Scholar> getScholarByName(String Name){
                return  scholarDao.findByName(Name);
        }

        public List<Scholar> getScholarByOrganization(String Organization){
                return  scholarDao.findByOrganization(Organization);
        }

        public List<Scholar> getScholarByNameAndOrganization(String Name,String Organization){
                return  scholarDao.findByNameAndOrganization(Name,Organization);
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



