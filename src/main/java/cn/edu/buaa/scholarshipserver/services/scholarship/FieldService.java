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
        hotFields.add(fieldDao.findById(2L).orElse(null));
        hotFields.add(fieldDao.findById(5L).orElse(null));
        hotFields.add(fieldDao.findById(3L).orElse(null));
        hotFields.add(fieldDao.findById(4L).orElse(null));
        hotFields.add(fieldDao.findById(6L).orElse(null));
        hotFields.add(fieldDao.findById(7L).orElse(null));
        hotFields.add(fieldDao.findById(12L).orElse(null));
        hotFields.add(fieldDao.findById(9L).orElse(null));
        hotFields.add(fieldDao.findById(10L).orElse(null));
        hotFields.add(fieldDao.findById(11L).orElse(null));
        return ResponseEntity.ok(new Response(hotFields));
    }
}
