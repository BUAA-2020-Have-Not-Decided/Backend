package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.es.DataScholar;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import cn.edu.buaa.scholarshipserver.es.Subscribe;
import cn.edu.buaa.scholarshipserver.services.scholar.DataScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.ScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.SubscribeMethod;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScholarService {
        @Autowired
        private ScholarMethod scholarMethod;
        private DataScholarMethod dataScholarMethod;
        private SubscribeMethod subscribeMethod;
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
            DataScholar dataScholar = dataScholarMethod.getDataScholarByAuthorId(params.get("authorId"));
            if (dataScholar  != null) {
                if(dataScholar.getScholarId()==null) {
                    dataScholar.setScholarId(params.get("scholarId"));
                    dataScholarMethod.updateDataScholar(dataScholar);
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
        */
        public ResponseEntity<Response> GetSubscribe(Integer fanId){
            List<Map<String,String>> res = new ArrayList<>();
            List<Subscribe> subscribes = subscribeMethod.getSubscribeByFansId(fanId);
            for(int i=0;i<subscribes.size();i++){
                Map<String,String> ins = new HashMap<String,String>();
                int scholarId = subscribes.get(0).getScholarId();
                Scholar scholar = scholarMethod.getScholarById(scholarId);
                ins.put("AvatarUrl",scholar.getAvatarUrl());
                ins.put("Name",scholar.getName());
                ins.put("ScholarId",String.valueOf(scholar.getScholarId()));
                ins.put("Institution",scholar.getOrganization());
                res.add(ins);
            }
            return ResponseEntity.ok(new Response(res));
        }

        /*
        public ResponseEntity<Response> PostSubscribe(){

        }
        public ResponseEntity<Response> DeleteSubscribe(){

        }
        public ResponseEntity<Response> PostSearch(){

        }
        public ResponseEntity<Response> PostMessage(){

        }*/
}


