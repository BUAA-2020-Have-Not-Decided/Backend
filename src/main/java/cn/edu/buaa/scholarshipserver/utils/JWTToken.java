package cn.edu.buaa.scholarshipserver.utils;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {
    private String JWTString;

    public JWTToken(String jwt){
        this.JWTString = jwt;
    }
    @Override
    public Object getPrincipal() {
        return JWTString;
    }

    @Override
    public Object getCredentials() {
        return JWTString;
    }
}
