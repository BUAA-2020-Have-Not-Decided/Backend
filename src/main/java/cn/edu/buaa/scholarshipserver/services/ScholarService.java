package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.dao.*;
import cn.edu.buaa.scholarshipserver.es.*;
import cn.edu.buaa.scholarshipserver.es.Paper;
import cn.edu.buaa.scholarshipserver.services.scholar.DataScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.ScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.SubscribeMethod;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.models.auth.In;
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
    @Autowired
    private FieldDao fieldDao;
    @Autowired
    private ArticleFieldDao articleFieldDao;

    public ResponseEntity<Response> GetScholar(Integer id) {
        Map<String, Object> responseMap = new TreeMap<>();
        //获取学者门户相关信息
        Scholar scholar = scholarMethod.getScholarById(id);
        responseMap.put("scholar", scholar);
        //获取学者门户对应的数据库门户
        //获取paper
        List<DataScholar> dataScholarList = dataScholarMethod.getDataScholarByScholarId(id);
        Set<Paper> paperSet = new LinkedHashSet<>();
        for (DataScholar dataScholar : dataScholarList) {
            List<Paper_DataScholar> paperDataScholarList = paperDataScholarDao.findByAuthorId(dataScholar.getAuthorId());
            for (Paper_DataScholar paperDataScholar : paperDataScholarList) {
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
                                        coAuthorsMap.put(authorName,oldTimes+cooperation.getTimes());
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
                return ResponseEntity.ok(new Response(1001,"修改提交成功",""));

            }
    public ResponseEntity<Response> GetSameNameUser (String username){
        List<DataScholar> dataScholarList = dataScholarMethod.getDataScholarByNormalizedName(username);
        //如果dataScholar的学者ID不为空，放在前面。
        List<DataScholar> tem = new ArrayList<>();
        for (DataScholar dataScholar : dataScholarList) {
            if (null == dataScholar.getScholarId()) {
                tem.add(dataScholar);
            }
        }
        for (DataScholar dataScholar : dataScholarList) {
            if (null != dataScholar.getScholarId()) {
                tem.add(dataScholar);
            }
        }
        return ResponseEntity.ok(new Response(tem));
    }

    public ResponseEntity<Response> PutWorkExperience (Integer scholarId, List < WorkExperience > workExperienceList)
    {
        workExperienceDao.deleteAll(workExperienceDao.findByScholarId(scholarId));
        workExperienceDao.saveAll(workExperienceList);
        return ResponseEntity.ok(new Response(1001, "success", ""));
    }

    public ResponseEntity<Response> PostScholar_DataScholar (Map < String, Object > params){
        DataScholar dataScholar = dataScholarMethod.getDataScholarByAuthorId((Long)params.get("authorId"));
        if (dataScholar != null) {
            if (dataScholar.getScholarId() == null) {
                dataScholar.setScholarId((Integer) params.get("scholarId"));
                dataScholarMethod.updateDataScholar(dataScholar);
                return ResponseEntity.ok(new Response(1001, "success", ""));
            } else {
                return ResponseEntity.ok(new Response(-1, "关系已添加", ""));
            }
        } else {
            return ResponseEntity.ok(new Response(-1, "该数据库门户不存在", ""));
        }
    }

    public ResponseEntity<Response> DeleteScholar_DataScholar (Map < String, Object> params){
        DataScholar dataScholar = dataScholarMethod.getDataScholarByAuthorId((Long)params.get("authorId"));
        if (dataScholar != null) {
            if (dataScholar.getScholarId().equals((Integer) params.get("scholarId"))) {
                dataScholar.setScholarId(null);
                dataScholarMethod.updateDataScholar(dataScholar);
                return ResponseEntity.ok(new Response(1001, "success", ""));
            } else {
                return ResponseEntity.ok(new Response(-1, "关系已添加", ""));
            }
        } else {
            return ResponseEntity.ok(new Response(-1, "该数据库门户不存在", ""));
        }
    }

    public ResponseEntity<Response> GetAdminScholar (String ScholarName, String ScholarId){
        List<Map<String, String>> res = new ArrayList<Map<String, String>>();
        if (ScholarId.length() != 0) {
            Integer scholarId = Integer.valueOf(ScholarId);
            Scholar scholar = scholarMethod.getScholarById(scholarId);
            if (scholar != null) {
                if (ScholarName.length() != 0 && scholar.getName().equals(ScholarName)) {
                    Map<String, String> ins = new HashMap<String, String>();
                    ins.put("AvatarUrl", scholar.getAvatarUrl());
                    ins.put("Name", scholar.getName());
                    ins.put("ScholarId", String.valueOf(scholar.getScholarId()));
                    ins.put("Institution", scholar.getOrganization());
                    res.add(ins);
                } else return ResponseEntity.ok(new Response(-1, "scholarId与ScholarName不对应", ""));
            } else return ResponseEntity.ok(new Response(-1, "scholarId不存在", ""));
        } else if (ScholarName.length() != 0) {
            List<Scholar> scholars = scholarMethod.getScholarByName(ScholarName);
            for (int i = 0; i < scholars.size(); i++) {
                Map<String, String> ins = new HashMap<String, String>();
                ins.put("AvatarUrl", scholars.get(i).getAvatarUrl());
                ins.put("Name", scholars.get(i).getName());
                ins.put("ScholarId", String.valueOf(scholars.get(i).getScholarId()));
                ins.put("Institution", scholars.get(i).getOrganization());
                res.add(ins);
            }
        } else return ResponseEntity.ok(new Response(-1, "2个参数都为空", ""));
        return ResponseEntity.ok(new Response(1001, res));
    }

    public ResponseEntity<Response> GetSubscribe (Integer fanId){
        List<Map<String, String>> res = new ArrayList<>();
        List<Subscribe> subscribes = subscribeMethod.getSubscribeByFanId(fanId);
        for (int i = 0; i < subscribes.size(); i++) {
            Map<String, String> ins = new HashMap<String, String>();
            int scholarId = subscribes.get(0).getScholarId();
            Scholar scholar = scholarMethod.getScholarById(scholarId);
            ins.put("AvatarUrl", scholar.getAvatarUrl());
            ins.put("Name", scholar.getName());
            ins.put("ScholarId", String.valueOf(scholar.getScholarId()));
            ins.put("Institution", scholar.getOrganization());
            res.add(ins);
        }
        return ResponseEntity.ok(new Response(1001, res));
    }

    public ResponseEntity<Response> PostSubscribe (Integer UserId, Integer ScholarId){
        Subscribe subscribe = subscribeMethod.getSubscribeByFanIdAndScholarId(UserId, ScholarId);
        if (subscribe == null) {
            subscribe = new Subscribe();
            subscribe.setFanId(UserId);
            subscribe.setScholarId(ScholarId);
            subscribe.setSubscribeDatetime(new DateTime());
            subscribeMethod.updateSubscribe(subscribe);
            return ResponseEntity.ok(new Response(1001, "success", ""));
        } else return ResponseEntity.ok(new Response(-1, "该关注关系已存在", ""));
    }

    public ResponseEntity<Response> DeleteSubscribe (Integer UserId, Integer ScholarId){
        Subscribe subscribe = subscribeMethod.getSubscribeByFanIdAndScholarId(UserId, ScholarId);
        if (subscribe != null) {
            subscribeMethod.deleteSubscribe(subscribe);
            return ResponseEntity.ok(new Response(1001, "success", ""));
        } else return ResponseEntity.ok(new Response(-1, "该关注关系不存在", ""));
    }

    public ResponseEntity<Response> Search (String ScholarName, String Institution){
        List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
        List<Scholar> scholars;
        if (ScholarName.length() != 0 && Institution.length() != 0) {
            scholars = scholarMethod.getScholarByNameAndOrganization(ScholarName, Institution);
        } else if (ScholarName.length() != 0) {
            scholars = scholarMethod.getScholarByName(ScholarName);
        } else if (Institution.length() != 0) {
            scholars = scholarMethod.getScholarByOrganization(Institution);
        } else return ResponseEntity.ok(new Response(-1, "两个参数都为空", ""));
        for (Scholar scholar : scholars) {
            Map<String, Object> ins = new HashMap<String, Object>();
            ins.put("AvatarUrl", scholar.getAvatarUrl());
            ins.put("ScholarId", String.valueOf(scholar.getScholarId()));
            ins.put("Name", scholar.getName());
            ins.put("Institution", scholar.getOrganization());
            ins.put("PaperCount", scholar.getPapers());
            ins.put("CitationCount", scholar.getCitations());
            List<DataScholar> dataScholars = dataScholarMethod.getDataScholarByScholarId(scholar.getScholarId());
            for (DataScholar dataScholar : dataScholars){
                List<Map<String,String>> fieldsIns = new ArrayList<Map<String, String>>();
                List<Paper_DataScholar> paper_dataScholars = paperDataScholarDao.findByAuthorId(dataScholar.getAuthorId());
                for(Paper_DataScholar paper_dataScholar : paper_dataScholars){
                    Paper paper = paperDao.findByPaperId(paper_dataScholar.getPaperId());
                    List<Article_Field> article_fields = articleFieldDao.findByPaperId(paper.getPaperId());
                    for(Article_Field article_field : article_fields){
                        Map<String,String> fieldIns = new HashMap<String,String>();
                        Fields fields = fieldDao.findByFieldsId(article_field.getFieldsId());
                        fieldIns.put("FieldsId",String.valueOf(fields.getFieldsId()));
                        fieldIns.put("FieldName",fields.getNormalizedName());
                        fieldsIns.add(fieldIns);
                    }
                }
                ins.put("Fields",fieldsIns);
            }
            res.add(ins);
        }
        return ResponseEntity.ok(new Response(1001,res));
    }
}



