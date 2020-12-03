package cn.edu.buaa.scholarshipserver.controllers;

import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.services.ScholarService;
import cn.edu.buaa.scholarshipserver.utils.Response;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/schlorship")
@Api(tags = {"学者门户相关接口"})

public class ScholarController {
    @Autowired
    private ScholarService scholarService;

    @GetMapping("/{ScholarId}")
    public ResponseEntity<Response> GetScholar(@PathVariable("ScholarId") String ScholarId) {

    }

    @PutMapping("/{ScholarId}")
    public ResponseEntity<Response> PutScholar(@PathVariable("ScholarId") String ScholarId) {

    }

    @GetMapping("/sameName/{UserName}")
    public ResponseEntity<Response> GetSameNameUser(@PathVariable("UserName") String UserName) {

    }

    @PostMapping("/Scholar_DataScholar")
    public ResponseEntity<Response> PostScholar_DataScholar() {

    }

    @GetMapping("/admin/{ScholarName}/{ScholarId}")
    public ResponseEntity<Response> GetAdminScholar(@PathVariable("ScholarName") String ScholarName,
                                                    @PathVariable("ScholarId") String ScholarId) {

    }

    @PostMapping("/admin/{ScholarId}/{DataScholarId}")
    public ResponseEntity<Response> GetAdminScholar(@PathVariable("ScholarId") String ScholarId,
                                                    @PathVariable("DataScholarId") String DataScholarId) {

    }

    @DeleteMapping("/admin/{ScholarId}/{DataScholarId}")
    public ResponseEntity<Response> DeleteAdminScholar(@PathVariable("ScholarId") String ScholarId,
                                                       @PathVariable("DataScholarId") String DataScholarId) {

    }

    @GetMapping("/subscribe/{UserId}")
    public ResponseEntity<Response> GetSubscribe(@PathVariable("UserId") String UserId) {

    }

    @PostMapping("/subscribe/{UserId}/{ScholarId}")
    public ResponseEntity<Response> PostSubscribe(@PathVariable("UserId") String UserId, @PathVariable("ScholarId") String ScholarId) {

    }

    @DeleteMapping("/subscribe/{UserId}/{ScholarId}")
    public ResponseEntity<Response> DeleteSubscribe(@PathVariable("UserId") String UserId, @PathVariable("ScholarId") String ScholarId) {

    }

    @PostMapping("/Scholar/Search")
    public ResponseEntity<Response> PostSearch() {

    }

    @PostMapping("/Message")
    public ResponseEntity<Response> PostMessage() {

    }
}

