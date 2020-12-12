package cn.edu.buaa.scholarshipserver.filter;

import cn.edu.buaa.scholarshipserver.utils.JWTToken;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.http.HttpStatus;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class JWTUserFilter extends BasicHttpAuthenticationFilter {

}
