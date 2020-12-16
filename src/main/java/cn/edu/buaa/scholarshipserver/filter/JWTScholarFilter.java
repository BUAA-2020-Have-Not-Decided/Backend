package cn.edu.buaa.scholarshipserver.filter;

import cn.edu.buaa.scholarshipserver.dao.ScholarMapper;
import cn.edu.buaa.scholarshipserver.models.Scholar;
import cn.edu.buaa.scholarshipserver.models.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class JWTScholarFilter extends JWTBasicFilter{

    @Autowired
    ScholarMapper scholar_mapper;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("welcome to scholar filter");
        if(this.isLoginAttempt(request, response)){
            try{
                boolean flag = this.executeLogin(request, response);
                User current_user = (User)SecurityUtils.getSubject().getPrincipal();
                if(!flag||current_user.getIdentify()!=2){
                    ((HttpServletResponse)response).sendRedirect("/user/unauthorized");
                    return false;
                }
                return true;
            }catch(Exception e){
                return false;
            }

        }
        else{
            try{
                ((HttpServletResponse)response).sendRedirect("/user/unauthorized");
            }
            catch(Exception e){
                System.out.println("wa!");
            }
            return false;
        }
    }
}
