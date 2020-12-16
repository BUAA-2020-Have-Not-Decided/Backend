package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.PatentDao;
import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.Patent;
import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PatentService {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private PatentDao patentDao;

    public Patent getPatentByPatentId(String patentId){
        long id = Long.parseLong(patentId);
        Optional<Patent> patent = patentDao.findById(id);
        //格式化日期
        String temp = patent.get().getApplicationDate();
        patent.get().setApplicationDate(temp.substring(0,4)+'-'+temp.substring(4,6)+'-'+temp.substring(6,8));

        return patent.get();
    }

    //相关度排序
    public ResponseEntity<Response> advancedSearchPatent(String titleKW, String abstractKW,
                                                         String organizationKW, String authorKW,
                                                         String startDate, String endDate,
                                                         String page, String size) {
        int pageNum = Integer.parseInt(page);
        int sizeNum = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNum-1, sizeNum);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = getProjectFunctionScoreQueryBuilder(titleKW,abstractKW,
                organizationKW, authorKW,startDate,endDate);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder).withPageable(pageable).withSort(SortBuilders.scoreSort())
                .withHighlightFields(
                        new HighlightBuilder.Field("title")
                        , new HighlightBuilder.Field("Abstract")
                        , new HighlightBuilder.Field("inventor")
                        , new HighlightBuilder.Field("agent")
                        , new HighlightBuilder.Field("applicant"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span class='highlight'>").postTags("</span>"))
                .build();
        //取消10000最大数量限制
        searchQuery.setTrackTotalHits(true);
        //查询
        SearchHits<Patent> search = elasticsearchRestTemplate.search(searchQuery, Patent.class);
        //得到查询返回的内容
        List<SearchHit<Patent>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<Patent> patents = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<Patent> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setTitle(highlightFields.get("title") == null ? searchHit.getContent().getTitle() : highlightFields.get("title").get(0));
            searchHit.getContent().setAbstract(highlightFields.get("Abstract") == null ? searchHit.getContent().getAbstract() : highlightFields.get("Abstract").get(0));
            searchHit.getContent().setInventor(highlightFields.get("inventor") == null ? searchHit.getContent().getInventor() : highlightFields.get("inventor").get(0));
            searchHit.getContent().setAgent(highlightFields.get("agent") == null ? searchHit.getContent().getAgent() : highlightFields.get("agent").get(0));
            searchHit.getContent().setApplicant(highlightFields.get("applicant") == null ? searchHit.getContent().getApplicant() : highlightFields.get("applicant").get(0));
            //格式化日期
            String temp = searchHit.getContent().getApplicationDate();
            searchHit.getContent().setApplicationDate(temp.substring(0,4)+'-'+temp.substring(4,6)+'-'+temp.substring(6,8));
            //放到实体类中
            patents.add(searchHit.getContent());
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("patenList", patents);
        responseMap.put("total",search.getTotalHits());
        return ResponseEntity.ok(new Response(responseMap));
    }
    //日期排序
    public ResponseEntity<Response> advancedSearchPatentSortByDate(String titleKW, String abstractKW,
                                                                   String organizationKW, String authorKW,
                                                                   String startDate, String endDate,
                                                                   String page, String size) {
        int pageNum = Integer.parseInt(page);
        int sizeNum = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNum-1, sizeNum);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = getProjectFunctionScoreQueryBuilder(titleKW,abstractKW,
                organizationKW, authorKW,startDate,endDate);

        // 排序条件
        FieldSortBuilder ageSortBuilder = SortBuilders.fieldSort("applicationDate").order(SortOrder.DESC);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder).withPageable(pageable)
                .withSort(ageSortBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("title")
                        , new HighlightBuilder.Field("Abstract")
                        , new HighlightBuilder.Field("inventor")
                        , new HighlightBuilder.Field("agent")
                        , new HighlightBuilder.Field("applicant"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span class='highlight'>").postTags("</span>"))
                .build();
        //取消10000最大数量限制
        searchQuery.setTrackTotalHits(true);
        //查询
        SearchHits<Patent> search = elasticsearchRestTemplate.search(searchQuery, Patent.class);
        //得到查询返回的内容
        List<SearchHit<Patent>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<Patent> patents = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<Patent> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setTitle(highlightFields.get("title") == null ? searchHit.getContent().getTitle() : highlightFields.get("title").get(0));
            searchHit.getContent().setAbstract(highlightFields.get("Abstract") == null ? searchHit.getContent().getAbstract() : highlightFields.get("Abstract").get(0));
            searchHit.getContent().setInventor(highlightFields.get("inventor") == null ? searchHit.getContent().getInventor() : highlightFields.get("inventor").get(0));
            searchHit.getContent().setAgent(highlightFields.get("agent") == null ? searchHit.getContent().getAgent() : highlightFields.get("agent").get(0));
            searchHit.getContent().setApplicant(highlightFields.get("applicant") == null ? searchHit.getContent().getApplicant() : highlightFields.get("applicant").get(0));
            //格式化日期
            String temp = searchHit.getContent().getApplicationDate();
            searchHit.getContent().setApplicationDate(temp.substring(0,4)+'-'+temp.substring(4,6)+'-'+temp.substring(6,8));
            //放到实体类中
            patents.add(searchHit.getContent());
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("patenList", patents);
        responseMap.put("total",search.getTotalHits());
        return ResponseEntity.ok(new Response(responseMap));
    }

    public FunctionScoreQueryBuilder getProjectFunctionScoreQueryBuilder(String titleKW, String abstractKW,
                                                                         String organizationKW, String authorKW,
                                                                         String startDate, String endDate) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(!titleKW.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("title",titleKW));
        if(!abstractKW.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("Abstract",abstractKW));
        if(!authorKW.equals("")){
            boolQuery.should(QueryBuilders.matchQuery("inventor",authorKW));
            boolQuery.should(QueryBuilders.matchQuery("agent",authorKW));
        }
        if(!organizationKW.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("applicant",organizationKW));
        if(!startDate.equals("")) {
            String begin = startDate;
            String end = endDate;
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("applicationDate")
                    .from(begin).to(end);
            boolQuery.must(rangeQueryBuilder);
        }
        FunctionScoreQueryBuilder functionScoreQueryBuilder= QueryBuilders.functionScoreQuery(boolQuery);
        return functionScoreQueryBuilder;
    }


}
