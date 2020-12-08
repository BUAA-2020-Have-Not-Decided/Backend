package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.ProjectDao;
import cn.edu.buaa.scholarshipserver.models.Project;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
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
public class ProjectService {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ProjectDao projectDao;

    public ResponseEntity<Response> getProjectById(String projectId) {
        Long id = Long.parseLong(projectId);
        Optional<Project> project = projectDao.findById(id);
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("project",project);
        return ResponseEntity.ok(new Response(responseMap));
    }

    public ResponseEntity<Response> getProjectListByKeyword(String keyword,String page,String size) {
        int pageNum = Integer.parseInt(page);
        int sizeNum = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNum,sizeNum);

        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("organization",keyword))
                .should(QueryBuilders.matchQuery("authors",keyword))
                .should(QueryBuilders.matchQuery("journal",keyword))
                .should(QueryBuilders.matchQuery("fundProject",keyword));
        //构建高亮查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder).withPageable(pageable)
                .withHighlightFields(
                        new HighlightBuilder.Field("organization")
                        ,new HighlightBuilder.Field("authors")
                        ,new HighlightBuilder.Field("journal")
                        ,new HighlightBuilder.Field("fundProject"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        //查询
        SearchHits<Project> search = elasticsearchRestTemplate.search(searchQuery, Project.class);
        //得到查询返回的内容
        List<SearchHit<Project>> searchHits = search.getSearchHits();
        //设置一个最后需要返回的实体类集合
        List<Project> projects = new ArrayList<>();
        //遍历返回的内容进行处理
        for(SearchHit<Project> searchHit:searchHits){
            //高亮的内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            //将高亮的内容填充到content中
            searchHit.getContent().setOrganization(highlightFields.get("organization")==null ? searchHit.getContent().getOrganization():highlightFields.get("organization").get(0));
            searchHit.getContent().setAuthors(highlightFields.get("authors")==null ? searchHit.getContent().getAuthors():highlightFields.get("authors").get(0));
            searchHit.getContent().setJournal(highlightFields.get("journal")==null ? searchHit.getContent().getJournal():highlightFields.get("journal").get(0));
            searchHit.getContent().setFundProject(highlightFields.get("fundProject")==null ? searchHit.getContent().getFundProject():highlightFields.get("fundProject").get(0));
            //放到实体类中
            projects.add(searchHit.getContent());
        }
        Map<String, Object> responseMap = new TreeMap<>();
        responseMap.put("projectList",projects);
        return ResponseEntity.ok(new Response(responseMap));
    }
}
