package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.PaperDao;
import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.CorrectPaperDao;
import cn.edu.buaa.scholarshipserver.es.Paper;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    public ResponseEntity<Response> advancedSearchPaper(String paperTitle, String paper_abstract,
                                                        String doctypeString, String authorName,
                                                        String startYear, String endYear) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if(!paperTitle.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("paperTitle",paperTitle));
        if(!paper_abstract.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("paper_abstract",paper_abstract));
        if(!doctypeString.equals(""))
            boolQuery.should(QueryBuilders.matchQuery("doctypeString",paper_abstract));
        if(!authorName.equals("")){

        }

        return null;
    }
}
