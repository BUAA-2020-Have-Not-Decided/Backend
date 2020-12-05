package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.es.DataScholar;
import cn.edu.buaa.scholarshipserver.services.scholar.GetDataScholar;
import cn.edu.buaa.scholarshipserver.services.scholar.GetSubscribe;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ScholarService {
        @Autowired
        //private GetScholar getScholar;
        private GetDataScholar getDataScholar;
        private GetSubscribe getSubscribe;
        /*public ResponseEntity<Response> GetScholar(Integer id){
                return  getScholar.getScholarById(id);
        }
        public ResponseEntity<Response> PutScholar(Integer id){
                return  getScholar.updateScholar(id);
        }
        public ResponseEntity<Response> GetSameNameUser(String username){
                return  getScholar.getScholarByUserName(username);
        }
        */
        public ResponseEntity<Response> PostScholar_DataScholar(Map<String,Integer> params) {
            DataScholar dataScholar = getDataScholar.getDataScholarByAuthorId(params.get("authorId"));
            if (dataScholar  != null) {
                if(dataScholar.getScholarId()==null) {
                    dataScholar.setScholarId(params.get("scholarId"));
                    getDataScholar.updateDataScholar(dataScholar);
                    return ResponseEntity.ok(new Response(200,"success"));
                }
                else {
                    return ResponseEntity.ok(new Response(200,"关系已添加"));
                }
            }
            else {
                return ResponseEntity.ok(new Response(404, "该数据库门户不存在"));
            }
        }
        /*public ResponseEntity<Response> GetAdminScholar(){

        }
        public ResponseEntity<Response> GetAdminScholar(){

        }
        public ResponseEntity<Response> DeleteAdminScholar(){

        }
        public ResponseEntity<Response> GetSubscribe(Integer userId){

        }

        public ResponseEntity<Response> PostSubscribe(){

        }
        public ResponseEntity<Response> DeleteSubscribe(){

        }
        public ResponseEntity<Response> PostSearch(){

        }
        public ResponseEntity<Response> PostMessage(){

        }*/
}


