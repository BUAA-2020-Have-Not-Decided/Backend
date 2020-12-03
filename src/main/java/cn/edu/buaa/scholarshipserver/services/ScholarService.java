package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.services.scholar.GetScholar;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class ScholarService {
        private GetScholar getScholar;
        public ScholarService(GetScholar getScholar){
                this.getScholar = getScholar;
        }
        public ResponseEntity<Response> GetScholar(Integer id){
                return  getScholar.getScholarById(id);
        }
        public ResponseEntity<Response> PutScholar(Integer id){
                return  getScholar.updateScholar(id);
        }
        public ResponseEntity<Response> GetSameNameUser(String username){
                return  getScholar.getScholarByUserName(username);
        }
        public ResponseEntity<Response> PostScholar_DataScholar(){

        }
        public ResponseEntity<Response> GetAdminScholar(){

        }
        public ResponseEntity<Response> GetAdminScholar(){

        }
        public ResponseEntity<Response> DeleteAdminScholar(){

        }
        public ResponseEntity<Response> GetSubscribe(){

        }
        public ResponseEntity<Response> PostSubscribe(){

        }
        public ResponseEntity<Response> DeleteSubscribe(){

        }
        public ResponseEntity<Response> PostSearch(){

        }
        public ResponseEntity<Response> PostMessage(){

        }

}
