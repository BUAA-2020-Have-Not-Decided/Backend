package cn.edu.buaa.scholarshipserver.filter;

import cn.edu.buaa.scholarshipserver.utils.JWTToken;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.http.HttpStatus;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

//这个项目中所有的过滤器的父类，各个类的不同主要体现在isAccessAllowed和executeLogin
//这个过滤器用来放在不需要任何登录的地方，通过一切请求。
//JWTUserFilter：用来过滤掉未登录的请求
//JWTScholarFilter：用来过滤掉不是scholar的请求
//JWTAdminFilter：用来过滤掉不是admin的请求
public class JWTBasicFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // System.out.println("preHandle!");
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse) response;
        res.setHeader("Access-control-Allow-Origin",req.getHeader("Origin"));
        res.setHeader("Access-control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        res.setHeader("Access-control-Allow-Headers",req.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setStatus(HttpStatus.SC_OK);
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue){
        // System.out.println("isAccessAllowed!");
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        System.out.println("testing!");
        HttpServletRequest req= (HttpServletRequest) request;
        String token=req.getHeader("Authorization");
        return token !=null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response)throws Exception{
        JWTToken token = new JWTToken(((HttpServletRequest)request).getHeader("Authorization"));
        System.out.println("before login!");
        try{
            getSubject(request, response).login(token);
        }
        catch(Exception e){
            System.out.println("executeLogin:caught!");
            return false;
        }
        System.out.println("after login");
        return true;
    }
}