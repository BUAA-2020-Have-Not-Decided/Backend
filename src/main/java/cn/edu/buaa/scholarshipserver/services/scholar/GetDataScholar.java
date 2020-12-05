package cn.edu.buaa.scholarshipserver.services.scholar;

import cn.edu.buaa.scholarshipserver.dao.DataScholarDao;
import cn.edu.buaa.scholarshipserver.es.DataScholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDataScholar {
    @Autowired
    private DataScholarDao dataScholarDao;
    public DataScholar getDataScholarByAuthorId(Integer authorId){
        return dataScholarDao.findByAuthorId(authorId);
    }
    public void updateDataScholar(DataScholar dataScholar){
        dataScholarDao.save(dataScholar);
    }
}
