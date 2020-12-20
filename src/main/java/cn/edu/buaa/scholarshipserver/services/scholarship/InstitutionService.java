package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.InstitutionDao;
import cn.edu.buaa.scholarshipserver.es.Institution;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionService {

    private InstitutionDao institutionDao;

    public InstitutionService(InstitutionDao institutionDao) {
        this.institutionDao = institutionDao;
    }

    // public ResponseEntity<Response> getTopInstitution() {
    public List<Institution> getTopInstitution() {
        List<Institution> institutions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            institutions.add(institutionDao.findByRank(i));
        }
        return institutions;
    }
}
