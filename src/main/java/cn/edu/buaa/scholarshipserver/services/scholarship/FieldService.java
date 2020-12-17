package cn.edu.buaa.scholarshipserver.services.scholarship;

import cn.edu.buaa.scholarshipserver.dao.FieldDao;
import cn.edu.buaa.scholarshipserver.es.Fields;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldService {

    private FieldDao fieldDao;

    public FieldService(FieldDao fieldDao) {
        this.fieldDao = fieldDao;
    }

    public ResponseEntity<Response> getHotFields() {
        List<Fields> hotFields = new ArrayList<>();
        for (int i = 2; i < 11; i++) {
            hotFields.add(fieldDao.findByFieldsId(i));
        }
        return ResponseEntity.ok(new Response(hotFields));
    }
}
