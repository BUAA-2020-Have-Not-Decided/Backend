package cn.edu.buaa.scholarshipserver.config;

import cn.edu.buaa.scholarshipserver.dao.UserMapper;
import cn.edu.buaa.scholarshipserver.models.User;
import cn.edu.buaa.scholarshipserver.services.users.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper user_mapper;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof UsernamePasswordToken;
    }

    //授权过程，根据identify来授予相应的权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证过程，认证这是不是一个正常的用户
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String email = token.getUsername();
        User u = user_mapper.getUserByEmail(email);
        if(u==null){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(u, u.getPassword(), "UserRealm");
    }
}
