package cn.edu.buaa.scholarshipserver.services.scholar;

import cn.edu.buaa.scholarshipserver.dao.DataScholarDao;
import cn.edu.buaa.scholarshipserver.es.DataScholar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataScholarMethod {
    @Autowired
    private DataScholarDao dataScholarDao;
    public DataScholar getDataScholarByAuthorId(Integer authorId){
        return dataScholarDao.findByAuthorId(authorId);
    }
    public List<DataScholar> getDataScholarByNormalizedName(String normalizedName){
        return dataScholarDao.findByNormalizedName(normalizedName);
    }
    public List<DataScholar> getDataScholarByScholarId(Integer scholarId){
        return dataScholarDao.findByScholarId(scholarId);
    }
    public void updateDataScholar(DataScholar dataScholar){
        dataScholarDao.save(dataScholar);
    }
}
