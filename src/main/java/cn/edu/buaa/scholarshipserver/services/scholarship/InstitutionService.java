package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.InstitutionDao;
import cn.edu.buaa.scholarshipserver.dao.InstitutionRankDao;
import cn.edu.buaa.scholarshipserver.es.Institution;
import cn.edu.buaa.scholarshipserver.es.InstitutionRank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionService {

    private InstitutionDao institutionDao;
    private InstitutionRankDao institutionRankDao;

    public InstitutionService(InstitutionDao institutionDao, InstitutionRankDao institutionRankDao) {
        this.institutionDao = institutionDao;
        this.institutionRankDao = institutionRankDao;
    }

    public List<Institution> getTopInstitution() {
        List<Institution> institutions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            institutions.add(institutionDao.findByRank(i));
        }
        return institutions;
    }

    public List<InstitutionRank> getTopInstitutionByField(Long fieldId) {
        List<InstitutionRank> institutionRankList = institutionRankDao.findByFieldId(fieldId);
        for (InstitutionRank institutionRank : institutionRankList) {
            Institution institution = institutionDao.findByInstitutionId(institutionRank.getInstitutionId());
            institutionRank.setInstitutionName(institution.getInstitutionName());
        }
        return institutionRankList;
    }
}
