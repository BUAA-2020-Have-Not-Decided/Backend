package cn.edu.buaa.scholarshipserver.config;

import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.utils.JWTToken;
import cn.edu.buaa.scholarshipserver.utils.RedisUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper user_mapper;

    @Autowired
    private RedisUtil redis_util;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JWTToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JWTToken jwt_token = (JWTToken)authenticationToken;
        User u = redis_util.getUserByJWT((String)jwt_token.getPrincipal());
        if(u == null){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(u, jwt_token.getPrincipal(), "JWTRealm");
    }
}
