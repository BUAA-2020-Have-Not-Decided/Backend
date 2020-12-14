package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.PaperDao;
import cn.edu.buaa.scholarshipserver.es.CorrectPaper;
import cn.edu.buaa.scholarshipserver.es.CorrectPaperDao;
import cn.edu.buaa.scholarshipserver.es.Paper;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
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


}
