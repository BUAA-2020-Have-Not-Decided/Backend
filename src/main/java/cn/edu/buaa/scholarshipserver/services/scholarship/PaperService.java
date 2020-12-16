package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.*;
import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.DataScholar;
import cn.edu.buaa.scholarshipserver.es.Paper_DataScholar;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class PaperService {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private PaperDao paperDao;

    @Autowired
    private CorrectPaperDao correctPaperDao;

    @Autowired
    private PaperDataScholarDao paperDataScholarDao;

    @Autowired
    private DataScholarDao dataScholarDao;

    @Autowired
    private ScholarDao scholarDao;

    public CorrectPaper getPaperByPaperId(String paperId) {
        Long id = Long.parseLong(paperId);
        CorrectPaper correctPaper = correctPaperDao.findByPaperId(id);
        //格式化日期
        correctPaper.setDate(correctPaper.getDate().substring(0, 10));
        return correctPaper;
    }

    public HashMap<String, Object> paperMap(String paperId) throws IOException {
        SearchRequest searchRequest = new SearchRequest("paper_datascholar");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("PaperId", paperId);
        sourceBuilder.query(termQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        List<Map<String,Object>> authorList = new ArrayList<>();
        List<Map<String,Object>> scholarList = new ArrayList<>();
        for(org.elasticsearch.search.SearchHit documentFields : searchResponse.getHits().getHits()){
            long authorid = Long.parseLong(documentFields.getSourceAsMap().get("authorId").toString());
            DataScholar dataScholar = dataScholarDao.findByAuthorId(authorid);
            if(dataScholar.getScholarId()== -1){
                HashMap<String,Object> authorMap = new HashMap<>();
                authorMap.put("type",0);
                authorMap.put("authorId",dataScholar.getAuthorId());
                authorMap.put("name",dataScholar.getDisplayName());
                authorMap.put("paperCount",dataScholar.getPaperCount());
                authorMap.put("citationCount",dataScholar.getCitationCount());
                authorMap.put("HIndex",dataScholar.getHIndex());
                authorList.add(authorMap);
            }else{
                int scholarId = dataScholar.getScholarId();
                Scholar scholar = scholarDao.findByScholarId(scholarId);
                HashMap<String,Object> scholarMap = new HashMap<>();
                scholarMap.put("type",1);
                scholarMap.put("scholarId",scholar.getScholarId());
                scholarMap.put("englishName",scholar.getEnglishName());
                scholarMap.put("name",scholar.getName());
                scholarMap.put("title",scholar.getTitle());
                scholarMap.put("organization",scholar.getOrganization());
                scholarMap.put("papers",scholar.getPapers());
                scholarMap.put("citations",scholar.getCitations());
                scholarMap.put("hIndex",scholar.getHIndex());
                scholarMap.put("gIndex",scholar.getGIndex());
                scholarList.add(scholarMap);
            }
        }
        HashMap<String,Object> authorMap = new HashMap<>();
        authorMap.put("authorList",authorList);
        authorMap.put("scholarList",scholarList);
        return authorMap;
    }

    public ResponseEntity<Response> advancedSearchPaper(String titleKW, String abstractKW, int doctype,
                                                        String organizationKW, String authorKW,
                                                        String startDate, String endDate,
                                                        String page, String size) {
        int pageNum = Integer.parseInt(page);
        int sizeNum = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNum - 1, sizeNum);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = getProjectFunctionScoreQueryBuilder(titleKW, abstractKW, doctype,
                organizationKW, authorKW, startDate, endDate);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder).withPageable(pageable).withSort(SortBuilders.scoreSort())
                .withHighlightFields(
                        new HighlightBuilder.Field("paperTitle")
                        , new HighlightBuilder.Field("paper_abstract"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span class='highlight'>").postTags("</span>"))
                .build();
        //取消10000最大数量限制
        searchQuery.setTrackTotalHits(true);
        //查询
        SearchHits<CorrectPaper> search = elasticsearchRestTemplate.search(searchQuery, CorrectPaper.class);
        //得到查询返回的内容
        List<SearchHit<CorrectPaper>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<CorrectPaper> correctPapers = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<CorrectPaper> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setPaperTitle(highlightFields.get("paperTitle")
                    == null ? searchHit.getContent().getPaperTitle() : highlightFields.get("paperTitle").get(0));
            searchHit.getContent().setPaper_abstract(highlightFields.get("paper_abstract")
                    == null ? searchHit.getContent().getPaper_abstract() : highlightFields.get("paper_abstract").get(0));
            //格式化日期
            searchHit.getContent().setDate(searchHit.getContent().getDate().substring(0, 10));
            //放到实体类中
            correctPapers.add(searchHit.getContent());
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("paperList", correctPapers);
        responseMap.put("total", search.getTotalHits());
        return ResponseEntity.ok(new Response(responseMap));
    }

    //日期排序
    public ResponseEntity<Response> advancedSearchPaperSortByDate(String titleKW, String abstractKW, int doctype,
                                                                  String organizationKW, String authorKW,
                                                                  String startDate, String endDate,
                                                                  String page, String size) {
        int pageNum = Integer.parseInt(page);
        int sizeNum = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNum - 1, sizeNum);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = getProjectFunctionScoreQueryBuilder(titleKW, abstractKW, doctype,
                organizationKW, authorKW, startDate, endDate);

        // 排序条件
        FieldSortBuilder ageSortBuilder = SortBuilders.fieldSort("date").order(SortOrder.DESC);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder).withPageable(pageable)
                .withSort(ageSortBuilder)
                .withHighlightFields(
                        new HighlightBuilder.Field("paperTitle")
                        , new HighlightBuilder.Field("paper_abstract"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span class='highlight'>").postTags("</span>"))
                .build();
        //取消10000最大数量限制
        searchQuery.setTrackTotalHits(true);
//        //限制查询结果为多少分以上
//        searchQuery.setMinScore(0.5f);
        //查询
        SearchHits<CorrectPaper> search = elasticsearchRestTemplate.search(searchQuery, CorrectPaper.class);
        //得到查询返回的内容
        List<SearchHit<CorrectPaper>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<CorrectPaper> correctPapers = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<CorrectPaper> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setPaperTitle(highlightFields.get("paperTitle")
                    == null ? searchHit.getContent().getPaperTitle() : highlightFields.get("paperTitle").get(0));
            searchHit.getContent().setPaper_abstract(highlightFields.get("paper_abstract")
                    == null ? searchHit.getContent().getPaper_abstract() : highlightFields.get("paper_abstract").get(0));
            //格式化日期
            searchHit.getContent().setDate(searchHit.getContent().getDate().substring(0, 10));
            //放到实体类中
            correctPapers.add(searchHit.getContent());
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("paperList", correctPapers);
        responseMap.put("total", search.getTotalHits());
        return ResponseEntity.ok(new Response(responseMap));

    }

    public ResponseEntity<Response> advancedSearchPaperSortByCitationCount(String titleKW, String abstractKW, int doctype, String organizationKW, String authorKW, String startDate, String endDate, String page, String size) {
        int pageNum = Integer.parseInt(page);
        int sizeNum = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNum - 1, sizeNum);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = getProjectFunctionScoreQueryBuilder(titleKW, abstractKW, doctype,
                organizationKW, authorKW, startDate, endDate);

        // 排序条件
        FieldSortBuilder citationCountSort = SortBuilders.fieldSort("citationCount").order(SortOrder.DESC);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder).withPageable(pageable)
                .withSort(citationCountSort)
                .withHighlightFields(
                        new HighlightBuilder.Field("paperTitle")
                        , new HighlightBuilder.Field("paper_abstract"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span class='highlight'>").postTags("</span>"))
                .build();
        //取消10000最大数量限制
        searchQuery.setTrackTotalHits(true);
//        //限制查询结果为多少分以上
//        searchQuery.setMinScore(0.5f);
        //查询
        SearchHits<CorrectPaper> search = elasticsearchRestTemplate.search(searchQuery, CorrectPaper.class);
        //得到查询返回的内容
        List<SearchHit<CorrectPaper>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<CorrectPaper> correctPapers = new ArrayList<>();
        //遍历返回的内容进行处理
        for (SearchHit<CorrectPaper> searchHit : searchHits) {
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setPaperTitle(highlightFields.get("paperTitle")
                    == null ? searchHit.getContent().getPaperTitle() : highlightFields.get("paperTitle").get(0));
            searchHit.getContent().setPaper_abstract(highlightFields.get("paper_abstract")
                    == null ? searchHit.getContent().getPaper_abstract() : highlightFields.get("paper_abstract").get(0));
            //格式化日期
            searchHit.getContent().setDate(searchHit.getContent().getDate().substring(0, 10));
            //放到实体类中
            correctPapers.add(searchHit.getContent());
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("paperList", correctPapers);
        responseMap.put("total", search.getTotalHits());
        return ResponseEntity.ok(new Response(responseMap));
    }

    public FunctionScoreQueryBuilder getProjectFunctionScoreQueryBuilder(String titleKW, String abstractKW, int doctype,
                                                                         String organizationKW, String authorKW,
                                                                         String startDate, String endDate) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (!titleKW.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("paperTitle", titleKW));
        if (!abstractKW.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("paper_abstract", abstractKW));

        switch (doctype) {
            case 0:
                break;
            case 1:
                boolQuery.must(QueryBuilders.matchQuery("doctype", "Journal"));
                break;
            case 2:
                boolQuery.must(QueryBuilders.matchQuery("doctype", "Conference"));
                break;
        }
        if (!organizationKW.equals("")) {
            // 有关作者搜索
        }
        if (!authorKW.equals("")) {
            // 有关组织机构搜索
        }
        if (!startDate.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            try {
                Date begin = sdf.parse(startDate);
                Date end = sdf.parse(endDate);
                RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("date")
                        .from(begin).to(end);
                boolQuery.must(rangeQueryBuilder);
            } catch (ParseException e) {
                FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQuery);
                return functionScoreQueryBuilder;
            }
        }
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(boolQuery);
        return functionScoreQueryBuilder;
    }

}
