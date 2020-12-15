package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.PaperDao;
import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.CorrectPaperDao;
import cn.edu.buaa.scholarshipserver.es.Paper;
import cn.edu.buaa.scholarshipserver.es.Patent;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
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

    public CorrectPaper getPaperByPaperId(String paperId){
        Long id = Long.parseLong(paperId);
        CorrectPaper correctPaper = correctPaperDao.findByPaperId(id);
        return correctPaper;
    }

    public ResponseEntity<Response> advancedSearchPaper(String titleKW, String abstractKW, int doctype,
                                                        String organizationKW, String authorKW,
                                                        String startDate, String endDate,
                                                        String page, String size) {
        int pageNum = Integer.parseInt(page);
        int sizeNum = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNum-1, sizeNum);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = getProjectFunctionScoreQueryBuilder(titleKW,abstractKW,doctype,
                organizationKW, authorKW,startDate,endDate);

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
            searchHit.getContent().setPaperTitle(highlightFields.get("paperTitle") == null ? searchHit.getContent().getPaperTitle() : highlightFields.get("title").get(0));
            searchHit.getContent().setPaper_abstract(highlightFields.get("paper_abstract") == null ? searchHit.getContent().getPaper_abstract() : highlightFields.get("Abstract").get(0));
            //放到实体类中
            correctPapers.add(searchHit.getContent());
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("paperList", correctPapers);
        responseMap.put("total",search.getTotalHits());
        return ResponseEntity.ok(new Response(responseMap));
    }

    public FunctionScoreQueryBuilder getProjectFunctionScoreQueryBuilder(String titleKW, String abstractKW, int doctype,
                                                                         String organizationKW, String authorKW,
                                                                         String startDate, String endDate) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(!titleKW.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("paperTitle",titleKW));
        if(!abstractKW.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("paper_abstract",abstractKW));

        switch (doctype){
            case 0:
                break;
            case 1:
                boolQuery.must(QueryBuilders.termQuery("doctype","Journal"));
                break;
            case 2:
                boolQuery.must(QueryBuilders.termQuery("doctype","Conference"));
                break;
        }
        if(!organizationKW.equals("")){
            // 有关作者搜索
        }
        if(!authorKW.equals("")) {
            // 有关组织机构搜索
        }
        if(!startDate.equals("")) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("date")
                    .from(startDate).to(endDate);
            boolQuery.must(rangeQueryBuilder);
        }
        FunctionScoreQueryBuilder functionScoreQueryBuilder= QueryBuilders.functionScoreQuery(boolQuery);
        return functionScoreQueryBuilder;
    }


}
