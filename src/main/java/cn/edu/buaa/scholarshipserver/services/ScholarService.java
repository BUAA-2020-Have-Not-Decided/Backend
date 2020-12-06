package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.es.DataScholar;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import cn.edu.buaa.scholarshipserver.es.Subscribe;
import cn.edu.buaa.scholarshipserver.services.scholar.DataScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.ScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.SubscribeMethod;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScholarService {
        @Autowired
        private ScholarMethod scholarMethod;
        @Autowired
        private DataScholarMethod dataScholarMethod;
        @Autowired
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
                    return ResponseEntity.ok(new Response(1001,"success",""));
                }
                else {
                    return ResponseEntity.ok(new Response(-1,"关系已添加",""));
                }
            }
            else {
                return ResponseEntity.ok(new Response(-1, "该数据库门户不存在",""));
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
            List<Subscribe> subscribes = subscribeMethod.getSubscribeByFanId(fanId);
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
            return ResponseEntity.ok(new Response(1001,res));
        }


        public ResponseEntity<Response> PostSubscribe(Integer UserId,Integer ScholarId){
            Subscribe subscribe = subscribeMethod.getSubscribeByFanIdAndScholarId(UserId, ScholarId);
            if(subscribe==null){
                subscribe = new Subscribe();
                subscribe.setFanId(UserId);
                subscribe.setScholarId(ScholarId);
                subscribe.setSubscribeDatetime(new DateTime());
                subscribeMethod.updateSubscribe(subscribe);
                return ResponseEntity.ok(new Response(1001,"success",""));
            }
            else return ResponseEntity.ok(new Response(-1,"该关注关系已存在",""));
        }

        public ResponseEntity<Response> DeleteSubscribe(Integer UserId,Integer ScholarId){
            Subscribe subscribe = subscribeMethod.getSubscribeByFanIdAndScholarId(UserId, ScholarId);
            if(subscribe!=null){
                subscribeMethod.deleteSubscribe(subscribe);
                return ResponseEntity.ok(new Response(1001,"success",""));
            }
            else return ResponseEntity.ok(new Response(-1,"该关注关系不存在",""));
        }
        /*
        public ResponseEntity<Response> PostSearch(){

        }
        public ResponseEntity<Response> PostMessage(){

        }*/
}


