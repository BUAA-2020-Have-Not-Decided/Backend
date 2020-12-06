package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.dao.*;
import cn.edu.buaa.scholarshipserver.es.*;
import cn.edu.buaa.scholarshipserver.es.Paper;
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
        @Autowired
        private PaperDataScholarDao paperDataScholarDao;
        @Autowired
        private PaperDao paperDao;
        @Autowired
        private ProjectDao projectDao;
        @Autowired
        private PatentDao patentDao;
        @Autowired
        private ProjectScholarDao projectScholarDao;
        @Autowired
        private PatentScholarDao patentScholarDao;
        @Autowired
        private CooperationDao cooperationDao;
        @Autowired
        private WorkExperienceDao workExperienceDao;
        public ResponseEntity<Response> GetScholar(Integer id){
                Map<String,Object>responseMap = new TreeMap<>();
                //获取学者门户相关信息
                Scholar scholar = scholarMethod.getScholarById(id);
                responseMap.put("scholar",scholar);
                //获取学者门户对应的数据库门户
                //获取paper
                List<DataScholar> dataScholarList = dataScholarMethod.getDataScholarByScholarId(id);
                Set<Paper>paperSet =  new LinkedHashSet<>();
                for(DataScholar dataScholar :dataScholarList){
                        List<Paper_DataScholar> paperDataScholarList = paperDataScholarDao.findByAuthorId(dataScholar.getAuthorId());
                        for(Paper_DataScholar paperDataScholar : paperDataScholarList){
                                Paper paper = paperDao.findByPaperId(paperDataScholar.getPaperId());
                                paperSet.add(paper);
                                /*
                                NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
                                nativeSearchQueryBuilder.withQuery(QueryBuilders.termsQuery("paperId",paperList));
                                 */
                        }
                }
                List<Paper>paperList = new ArrayList<>(paperSet);
                responseMap.put("paperNum",paperList.size());
                responseMap.put("paper",paperList);
                List<Project>projectList = new ArrayList<>();
                List<Patent>patentList = new ArrayList<>();
                List<Project_Scholar>projectScholarList = projectScholarDao.findByScholarId(scholar.getScholarId());
                for(Project_Scholar projectScholar : projectScholarList){
                        projectList.add(projectDao.findByProjectId(projectScholar.getProjectId()));
                }
                responseMap.put("projectNum",projectList.size());
                responseMap.put("project",projectList);
                List<Patent_Scholar> patentScholarList = patentScholarDao.findByScholarId(scholar.getScholarId());
                for(Patent_Scholar patentScholar : patentScholarList){
                        patentList.add(patentDao.findByPatentId(patentScholar.getPatentId()));
                }
                responseMap.put("patentNum",patentList.size());
                responseMap.put("patent",patentList);
                //下面获取合作学者
                Map<String,Integer>coAuthorsMap = new TreeMap<>();
                for(DataScholar dataScholar :dataScholarList){
                        List<Cooperation> cooperationList = cooperationDao.findByAuthorId1(dataScholar.getAuthorId());
                        for(Cooperation cooperation : cooperationList){
                                String authorName = dataScholarMethod.getDataScholarByAuthorId(cooperation.getAuthorId2()).getNormalizedName();
                                if(null == coAuthorsMap.get(authorName)){
                                        coAuthorsMap.put(authorName,cooperation.getTimes());
                                }else{
                                        Integer oldTimes = coAuthorsMap.get(authorName);
                                        coAuthorsMap.put(authorName,Math.max(oldTimes,cooperation.getTimes()));
                                }
                        }
                }
                responseMap.put("coAuthors",coAuthorsMap);
                //工作经历
                List<WorkExperience>workExperienceList = workExperienceDao.findByScholarId(scholar.getScholarId());
                responseMap.put("workExperience",workExperienceList);
                return ResponseEntity.ok(new Response(responseMap));
        }
        public ResponseEntity<Response> PutScholar(Integer id,Map<String,Object> params){
                Scholar scholar = scholarMethod.getScholarById(id);
                //scholar.setAvatarUrl((String)params.get("avatarUrl"));
                for(Map.Entry<String,Object>entry : params.entrySet()){
                        switch (entry.getKey()){
                                case "name":
                                        scholar.setName((String)entry.getValue());
                                        break;
                                case "email":
                                        scholar.setEmail((String)entry.getValue());
                                        break;
                                case "phone":
                                        scholar.setPhone((String)entry.getValue());
                                        break;
                                case "title":
                                        scholar.setTitle((String)entry.getValue());
                                        break;
                                case "introduction":
                                        scholar.setIntroduction((String)entry.getValue());
                                        break;
                                case "organization":
                                        scholar.setOrganization((String)entry.getValue());
                                        break;
                        }
                }
                scholarMethod.updateScholar(scholar);
                return ResponseEntity.ok(new Response(1001,"success",""));
        }
        public ResponseEntity<Response> GetSameNameUser(String username){
                List<DataScholar> dataScholarList = dataScholarMethod.getDataScholarByNormalizedName(username);
                //如果dataScholar的学者ID不为空，放在前面。
                List<DataScholar> tem = new ArrayList<>();
                for(DataScholar dataScholar : dataScholarList){
                        if(null == dataScholar.getScholarId()){
                                tem.add(dataScholar);
                        }
                }
                for (DataScholar dataScholar:dataScholarList){
                        if(null != dataScholar.getScholarId()){
                                tem.add(dataScholar);
                        }
                }
                return ResponseEntity.ok(new Response(tem));
        }
        public ResponseEntity<Response> PutWorkExperience(Integer scholarId,List<WorkExperience>workExperienceList){
                workExperienceDao.deleteAll(workExperienceDao.findByScholarId(scholarId));
                workExperienceDao.saveAll(workExperienceList);
                return ResponseEntity.ok(new Response(1001,"success",""));
        }
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


