package cn.edu.buaa.scholarshipserver.config;

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

public class JWTFiler extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request,response)){
            try {
                executeLogin(request,response);
                return true;
            }catch (Exception e){
                String msg = e.getMessage();
                Throwable cause = e.getCause();
                if (cause instanceof TokenExpiredException){
                    String result = refreshToken(request,response);
                    if (result.equals("success")){
                        return true;
                    }
                    msg = result;
                }
                responseError (response,msg);
            }
        }
        else {
            responseError (response,"no login");
        }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        return token!= null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        JWTToken jwt = new JWTToken(token);
        getSubject(request,response).login(jwt);
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Authorization",token);
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setHeader("Access-control-Allow-Origin",req.getHeader("Origin"));
        res.setHeader("Access-control-Allow-Methods","GET,POST,OPTIONS,PUT,DELETE");
        res.setHeader("Access-control-Allow-Headers",req.getHeader("Access-Control-Request-Headers"));
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())){
            res.setStatus(HttpStatus.SC_OK);
            return false;
        }
        return super.preHandle(request,response);
    }

    private void responseError(ServletResponse response,String msg){
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            msg = msg.replace(" ","-");
            msg = URLEncoder.encode(msg,"UTF-8");
            httpServletResponse.sendRedirect("/unauthorized/"+msg);
        }catch (IOException ignored){
        }
    }

    public <T> T getBean(Class<T> clazz,HttpServletRequest request){
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(clazz);
    }

    private String refreshToken(ServletRequest request, ServletResponse response){
        HttpServletRequest req = (HttpServletRequest) request;
        String accessToken = req.getHeader("Authorization");
        String email = JwtUtils.getEmail(accessToken);
        RedisUtils redisUtils = getBean(RedisUtils.class,req);
        if (redisUtils.hasKey(email)){
            long current = (long) redisUtils.get(email);
            if (current == JwtUtils.getExpire(accessToken)){
                long currentTimeMillis = System.currentTimeMillis();
                String token = JwtUtils.createToken(email,currentTimeMillis);
                redisUtils.set(email,currentTimeMillis,30*60);
                JWTToken jwtToken = new JWTToken(token);
                try {
                    getSubject(request,response).login(jwtToken);
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setHeader("Authorization",token);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                    return "success";
                }catch (Exception e){
                    return e.getMessage();
                }
            }
        }
        return "out of time";
    }
}
