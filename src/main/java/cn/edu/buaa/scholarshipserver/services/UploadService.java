package cn.edu.buaa.scholarshipserver.services;

import cn.edu.buaa.scholarshipserver.dao.ScholarDao;
import cn.edu.buaa.scholarshipserver.es.Scholar;
import cn.edu.buaa.scholarshipserver.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

@Service
public class UploadService {
        @Autowired
        private ScholarDao scholarDao;
        public ResponseEntity<Response> UploadImage(Long scholarId,String base64Data){
                String dataPrix = ""; //base64格式前头
                String data = "";//实体部分数据
                if(base64Data==null||"".equals(base64Data)){
                        return ResponseEntity.ok(new Response(401,"上传图片为空",""));
                }else {
                        String [] d = base64Data.split("base64,");//将字符串分成数组
                        if(d != null && d.length == 2){
                                dataPrix = d[0];
                                data = d[1];
                        }else {
                                return ResponseEntity.ok(new Response(401,"上传图片不合法",""));
                        }
                }
                String suffix = "";//图片后缀，用以识别哪种格式数据
                //data:image/jpeg;base64,base64编码的jpeg图片数据
                if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
                        suffix = ".jpg";
                }else if("data:image/png;".equalsIgnoreCase(dataPrix)){
                        //data:image/png;base64,base64编码的png图片数据
                        suffix = ".png";
                }else {
                        return ResponseEntity.ok(new Response(401,"上传图片格式不合法",""));
                }
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String tempFileName=uuid+suffix;
                String imgFilePath = "C:\\Users\\Administrator\\Documents\\GitHub\\"+tempFileName;//新生成的图片
                Base64.Decoder decoder = Base64.getDecoder();
                try {
                        //Base64解码
                        byte[] b = decoder.decode(data);
                        for(int i=0;i<b.length;++i) {
                                if(b[i]<0) {
                                        //调整异常数据
                                        b[i]+=256;
                                }
                        }
                        OutputStream out = new FileOutputStream(imgFilePath);
                        out.write(b);
                        out.flush();
                        out.close();
                        String imgurl="http://xxxxxxxx/"+tempFileName;
                        Scholar scholar = scholarDao.findByScholarId(scholarId);
                        scholar.setAvatarUrl(imgurl);
                        scholarDao.save(scholar);
                        return ResponseEntity.ok(new Response(1001,"上传图片成功",""));
                } catch (IOException e) {
                        e.printStackTrace();
                        return ResponseEntity.ok(new Response(401,"上传图片失败",""));
                }
        }
        public ResponseEntity<Response> UploadImage(String base64Data){
                String dataPrix = ""; //base64格式前头
                String data = "";//实体部分数据
                if(base64Data==null||"".equals(base64Data)){
                        return ResponseEntity.ok(new Response(401,"上传图片为空",""));
                }else {
                        String [] d = base64Data.split("base64,");//将字符串分成数组
                        if(d != null && d.length == 2){
                                dataPrix = d[0];
                                data = d[1];
                        }else {
                                return ResponseEntity.ok(new Response(401,"上传图片不合法",""));
                        }
                }
                String suffix = "";//图片后缀，用以识别哪种格式数据
                //data:image/jpeg;base64,base64编码的jpeg图片数据
                if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){
                        suffix = ".jpg";
                }else if("data:image/png;".equalsIgnoreCase(dataPrix)){
                        //data:image/png;base64,base64编码的png图片数据
                        suffix = ".png";
                }else {
                        return ResponseEntity.ok(new Response(401,"上传图片格式不合法",""));
                }
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String tempFileName=uuid+suffix;
                String imgFilePath = "C:\\Users\\Administrator\\Documents\\GitHub\\"+tempFileName;//新生成的图片
                Base64.Decoder decoder = Base64.getDecoder();
                try {
                        //Base64解码
                        byte[] b = decoder.decode(data);
                        for(int i=0;i<b.length;++i) {
                                if(b[i]<0) {
                                        //调整异常数据
                                        b[i]+=256;
                                }
                        }
                        OutputStream out = new FileOutputStream(imgFilePath);
                        out.write(b);
                        out.flush();
                        out.close();
                        String imgurl="http://xxxxxxxx/"+tempFileName;
                        return ResponseEntity.ok(new Response(1001,"上传图片成功",""));
                } catch (IOException e) {
                        e.printStackTrace();
                        return ResponseEntity.ok(new Response(401,"上传图片失败",""));
                }
        }
}
