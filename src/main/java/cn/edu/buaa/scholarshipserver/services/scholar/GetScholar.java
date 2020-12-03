package cn.edu.buaa.scholarshipserver.services.scholar;

import cn.edu.buaa.scholarshipserver.dao.ScholarDao;
import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetScholar {
        @Autowired
        private ScholarDao scholarDao;

        public ResponseEntity<Response> getScholarById(Integer id){
                Scholar scholar;
                if((scholar = scholarDao.findByScholarId(id))==null){
                        scholar = new Scholar();
                        return  ResponseEntity.ok(new Response(404,"没有找到相关学者门户",scholar));
                }

                return ResponseEntity.ok(new Response(scholar));
        }

        public ResponseEntity<Response> updateScholar(Integer id){
                Scholar scholar =new Scholar();
                scholarDao.save(scholar);

                return  ResponseEntity.ok(new Response(1));
        }

        public ResponseEntity<Response> getScholarByUserName(String username){
                List<Scholar> scholarList = new ArrayList<>();
                scholarList = scholarDao.findByNormalizedName(username);
                return ResponseEntity.ok(new Response(scholarList));
        }
}
