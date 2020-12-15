package cn.edu.buaa.scholarshipserver.filter;

import cn.edu.buaa.scholarshipserver.utils.JWTToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTUserFilter extends JWTBasicFilter{
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("welcome to user filter");
        if(this.isLoginAttempt(request, response)){
            try{
                boolean flag = this.executeLogin(request, response);
                if(!flag){
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
