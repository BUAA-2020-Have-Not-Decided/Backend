package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.dao.*;
import cn.edu.buaa.scholarshipserver.es.*;
import cn.edu.buaa.scholarshipserver.es.Paper;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.scholar.DataScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.ScholarMethod;
import cn.edu.buaa.scholarshipserver.services.scholar.SubscribeMethod;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


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
    @Autowired
    private SubscribeDao subscribeDao;
    @Autowired
    private ScholarDao scholarDao;
    @Autowired
    private InstitutionDao institutionDao;

    public ResponseEntity<Response> GetScholar(Integer uid,Integer id) {
        Map<String, Object> responseMap = new TreeMap<>();

        User u = (User) SecurityUtils.getSubject().getPrincipal();
        if(u.getUserID()!=uid){
            return ResponseEntity.ok(new Response(405,"Method Not Allowed",""));
        }

        //获取学者门户相关信息
        Scholar scholar = scholarMethod.getScholarById(id);
        if(scholar == null){
            return ResponseEntity.ok(new Response(404,"该学者门户不存在",""));
        }
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

        List<Optional<cn.edu.buaa.scholarshipserver.models.Project>> projectList = new ArrayList<>();
        List<Optional<Patent>> patentList = new ArrayList<>();
        List<Project_Scholar> projectScholarList = projectScholarDao.findByScholarId(scholar.getScholarId());
        for(Project_Scholar projectScholar : projectScholarList){
            projectList.add(projectDao.findById(projectScholar.getProjectId()));
        }
        responseMap.put("projectNum",projectList.size());
        responseMap.put("project",projectList);
        List<Patent_Scholar> patentScholarList = patentScholarDao.findByScholarId(scholar.getScholarId());
        for(Patent_Scholar patentScholar : patentScholarList){
            patentList.add(patentDao.findById(patentScholar.getPatentId()));
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
        boolean isSubscribed = false;
        if(subscribeDao.findByFanIdAndScholarId(uid,id)!=null){
            isSubscribed = true;
        }
        responseMap.put("isSubscribed",isSubscribed);
        return ResponseEntity.ok(new Response(responseMap));
    }

    public ResponseEntity<Response> GetDataScholar(Long authorId){
            Map<String, Object> responseMap = new TreeMap<>();
            DataScholar dataScholar =dataScholarMethod.getDataScholarByAuthorId(authorId);
            if(dataScholar == null){
                return ResponseEntity.ok(new Response(404,"该数据库门户不存在",""));
            }
            responseMap.put("dataScholar",dataScholar);
            Set<Paper> paperSet = new LinkedHashSet<>();
            List<Paper_DataScholar> paperDataScholarList = paperDataScholarDao.findByAuthorId(dataScholar.getAuthorId());
            for (Paper_DataScholar paperDataScholar : paperDataScholarList) {
                Paper paper = paperDao.findByPaperId(paperDataScholar.getPaperId());
                paperSet.add(paper);
            }
            List<Paper>paperList = new ArrayList<>(paperSet);
            responseMap.put("paperNum",paperList.size());
            responseMap.put("paper",paperList);
            if(dataScholar.getLastKnownAffiliationId()==-1L){
                responseMap.put("institution","");
            }
            else{
                responseMap.put("institution",institutionDao.findByInstitutionId(dataScholar.getLastKnownAffiliationId()).getInstitutionName());
            }
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
    public ResponseEntity<Response> GetSameNameUser (String username,String scholarId){
        List<DataScholar> dataScholarList = dataScholarMethod.getDataScholarByNormalizedName(username);
        System.out.println(username);
        //如果dataScholar的学者ID不为空，放在前面。
        Map<String,Object> tem1 = new LinkedHashMap<>();

        List<DataScholar> tem = new ArrayList<>();
        for (DataScholar dataScholar : dataScholarList) {
            if (-1 == dataScholar.getScholarId()) {
                tem.add(dataScholar);
            }
        }
        tem1.put("pos",tem.size());
        for (DataScholar dataScholar : dataScholarList) {
            if (-1 != dataScholar.getScholarId() && dataScholar.getScholarId() != Integer.parseInt(scholarId)) {
                tem.add(dataScholar);
            }
        }
        List<String>institutionList = new ArrayList<>();
        for(DataScholar dataScholar:tem){
            if(dataScholar.getLastKnownAffiliationId()==-1L)
                institutionList.add("");
            else
                institutionList.add(institutionDao.findByInstitutionId(dataScholar.getLastKnownAffiliationId()).getInstitutionName());
        }
        tem1.put("dataScholar",tem);
        tem1.put("institution",institutionList);
        return ResponseEntity.ok(new Response(tem1));
    }

    public ResponseEntity<Response> PutWorkExperience ( WorkExperience workExperienceList)
    {
        workExperienceDao.save(workExperienceList);
        return ResponseEntity.ok(new Response(1001, "success", ""));
    }
    public ResponseEntity<Response> DeleteWorkExperience(WorkExperience workExperience){
        WorkExperience workExperience1  = workExperienceDao.findByScholarIdAndAndIntroductionAndOrganizationAndYearStartAndYearEnd(workExperience.getScholarId(),workExperience.getIntroduction(),workExperience.getOrganization(),workExperience.getYearStart(),workExperience.getYearEnd());
        workExperienceDao.delete(workExperience1);
        return ResponseEntity.ok(new Response(1001, "success", ""));
    }
    public ResponseEntity<Response> GetScholar_DataScholar (Integer scholarId){
        List<DataScholar> dataScholars = dataScholarMethod.getDataScholarByScholarId(scholarId);
        return ResponseEntity.ok(new Response(1001,dataScholars));
    }

    public ResponseEntity<Response> PostScholar_DataScholar (Map < String, Object > params){
        DataScholar dataScholar = dataScholarMethod.getDataScholarByAuthorId((Long)params.get("authorId"));
        if (dataScholar != null) {
            if (dataScholar.getScholarId() == -1) {
                Scholar scholar = scholarDao.findByScholarId((int)params.get("scholarId"));

                if(scholar!=null){
                    if(null == scholar.getHIndex() || scholar.getHIndex()<dataScholar.getHIndex()){
                        scholar.setHIndex(dataScholar.getHIndex());
                        scholarMethod.updateScholar(scholar);
                    }
                }
                else
                    return ResponseEntity.ok(new Response(400, "学者id不存在", ""));
                dataScholar.setScholarId((Integer) params.get("scholarId"));
                dataScholarMethod.updateDataScholar(dataScholar);

                return ResponseEntity.ok(new Response(1001, "success", ""));
            } else {
                return ResponseEntity.ok(new Response(400, "关系已添加", ""));
            }
        } else {
            return ResponseEntity.ok(new Response(400, "该数据库门户不存在", ""));
        }
    }

    public ResponseEntity<Response> DeleteScholar_DataScholar (Map < String, Object> params){
        DataScholar dataScholar = dataScholarMethod.getDataScholarByAuthorId((Long)params.get("authorId"));
        if (dataScholar != null) {
            if (dataScholar.getScholarId().equals((Integer) params.get("scholarId"))) {
                dataScholar.setScholarId(-1);
                dataScholarMethod.updateDataScholar(dataScholar);
                int maxHIndex=0;
                Scholar scholar = scholarMethod.getScholarById((Integer) params.get("scholarId"));
                if(scholar!=null) {
                    List<DataScholar> dataScholars = dataScholarMethod.getDataScholarByScholarId((Integer) params.get("scholarId"));
                    if (dataScholars != null) {
                        for (DataScholar dataScholar1 : dataScholars) {
                            maxHIndex = maxHIndex < dataScholar1.getHIndex() ? dataScholar1.getHIndex() : maxHIndex;
                        }
                    }
                    scholar.setHIndex(maxHIndex);
                    scholarMethod.updateScholar(scholar);
                }
                else return ResponseEntity.ok(new Response(400, "学者id不存在", ""));
                return ResponseEntity.ok(new Response(1001, "success", ""));
            } else {
                return ResponseEntity.ok(new Response(400, "关系不存在", ""));
            }
        } else {
            return ResponseEntity.ok(new Response(400, "该数据库门户不存在", ""));
        }
    }

    public ResponseEntity<Response> GetAdminScholar (String ScholarName, String ScholarId){
        List<Map<String, String>> res = new ArrayList<Map<String, String>>();
        if (ScholarId.length() != 0) {
            Integer scholarId = Integer.valueOf(ScholarId);
            Scholar scholar = scholarMethod.getScholarById(scholarId);
            if (scholar != null) {
                System.out.println(ScholarName);
                if (ScholarName.length() != 0) {
                    if(!scholar.getName().equals(ScholarName)) {
                        return ResponseEntity.ok(new Response(400, "scholarId与ScholarName不对应", ""));
                    }
                }
                Map<String, String> ins = new HashMap<String, String>();
                ins.put("AvatarUrl", scholar.getAvatarUrl());
                ins.put("Name", scholar.getName());
                ins.put("ScholarId", String.valueOf(scholar.getScholarId()));
                ins.put("Institution", scholar.getOrganization());
                res.add(ins);
            } else return ResponseEntity.ok(new Response(400, "scholarId不存在", ""));
        } else if (ScholarName.length() != 0) {
            List<Scholar> scholars = scholarDao.findByName(ScholarName);
            for (int i = 0; i < scholars.size(); i++) {
                Map<String, String> ins = new HashMap<String, String>();
                ins.put("AvatarUrl", scholars.get(i).getAvatarUrl());
                ins.put("Name", scholars.get(i).getName());
                ins.put("ScholarId", String.valueOf(scholars.get(i).getScholarId()));
                ins.put("Institution", scholars.get(i).getOrganization());
                res.add(ins);
            }
        } else return ResponseEntity.ok(new Response(400, "2个参数都为空", ""));
        return ResponseEntity.ok(new Response(1001, res));
    }

    public ResponseEntity<Response> GetSubscribe (Integer fanId){
        List<Map<String, String>> res = new ArrayList<>();
        List<Subscribe> subscribes = subscribeMethod.getSubscribeByFanId(fanId);
        for (int i = 0; i < subscribes.size(); i++) {
            Map<String, String> ins = new HashMap<String, String>();
            int scholarId = subscribes.get(i).getScholarId();
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
            subscribe.setSubscribeDatetime(new DateTime().toString());
            subscribeMethod.updateSubscribe(subscribe);
            return ResponseEntity.ok(new Response(1001, "success", ""));
        } else return ResponseEntity.ok(new Response(400, "该关注关系已存在", ""));
    }

    public ResponseEntity<Response> DeleteSubscribe (Integer UserId, Integer ScholarId){
        Subscribe subscribe = subscribeMethod.getSubscribeByFanIdAndScholarId(UserId, ScholarId);
        if (subscribe != null) {
            subscribeMethod.deleteSubscribe(subscribe);
            return ResponseEntity.ok(new Response(1001, "success", ""));
        } else return ResponseEntity.ok(new Response(400, "该关注关系不存在", ""));
    }

    public ResponseEntity<Response> Search (String ScholarName, String Institution,Integer OrderType,Integer pageNumber){
        pageNumber-=1;
        Map<String, Object> res = new HashMap<String, Object>();
        List<Scholar> scholars;
        AtomicInteger totalPage = new AtomicInteger();
        if (ScholarName.length() != 0 && Institution.length() != 0) {
            scholars = scholarMethod.getScholarByNameAndOrganization(ScholarName, Institution,OrderType,pageNumber,totalPage);
        } else if (ScholarName.length() != 0) {
            scholars = scholarMethod.getScholarByName(ScholarName,OrderType,pageNumber,totalPage);
        }  else return ResponseEntity.ok(new Response(400, "两个参数都为空", ""));
        List<Map<String,Object> > SS = new ArrayList<Map<String,Object>>();
        for (Scholar scholar : scholars) {
            Map<String, Object> ins = new HashMap<String, Object>();
            ins.put("AvatarUrl", scholar.getAvatarUrl());
            ins.put("ScholarId", String.valueOf(scholar.getScholarId()));
            ins.put("Name", scholar.getName());
            ins.put("Institution", scholar.getOrganization());
            ins.put("PaperCount", scholar.getPapers());
            ins.put("CitationCount", scholar.getCitations());
            ins.put("hIndex", scholar.getHIndex());
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
                        fieldIns.put("FieldName",fields.getDisplayName());
                        fieldsIns.add(fieldIns);
                    }
                }
                ins.put("Fields",fieldsIns);
            }
            SS.add(ins);
        }
        res.put("scholars",SS);
        res.put("totalSize",totalPage);
        return ResponseEntity.ok(new Response(1001,res));
    }
    public ResponseEntity<Response> SearchDataScholar (String ScholarName,Integer OrderType,Integer pageNumber){
        pageNumber-=1;
        Map<String, Object> res = new HashMap<String, Object>();
        List<DataScholar> dataScholars;
        AtomicInteger totalPage = new AtomicInteger();
        if (ScholarName.length() != 0) {
            dataScholars = dataScholarMethod.Search(ScholarName,OrderType,pageNumber,totalPage);
        }  else return ResponseEntity.ok(new Response(400, "参数为空", ""));
        List<Map<String,Object> > DS = new ArrayList<Map<String,Object>>();
        for (DataScholar dataScholar : dataScholars) {
            Map<String, Object> ins = new HashMap<String, Object>();
            ins.put("scholarId", dataScholar.getScholarId());
            ins.put("authorId", dataScholar.getAuthorId());
            ins.put("normalizedName", dataScholar.getNormalizedName());
            ins.put("displayName", dataScholar.getDisplayName());
            ins.put("paperCount", dataScholar.getPaperCount());
            ins.put("paperFamilyCount", dataScholar.getPaperFamilyCount());
            ins.put("citationCount", dataScholar.getCitationCount());
            ins.put("hIndex", dataScholar.getHIndex());
            Institution institution = institutionDao.findByInstitutionId(dataScholar.getLastKnownAffiliationId());
            if(institution!=null){
                ins.put("institution",institution.getInstitutionName());
            }
            else {
                ins.put("institution","");
            }
            DS.add(ins);
        }
        res.put("dataScholars",DS);
        res.put("totalSize",totalPage);
        return ResponseEntity.ok(new Response(1001,res));
    }
}



