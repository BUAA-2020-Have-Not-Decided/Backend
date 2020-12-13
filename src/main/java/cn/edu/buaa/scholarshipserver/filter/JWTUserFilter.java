package cn.edu.buaa.scholarshipserver.filter;

import cn.edu.buaa.scholarshipserver.utils.JWTToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JWTUserFilter extends JWTBasicFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("welcome to user filter");
        if(this.isLoginAttempt(request, response)){
            try{
                this.executeLogin(request, response);
                return true;
            }catch(Exception e){
                return false;
            }

        }
        else{
            return false;
        }
    }
}
