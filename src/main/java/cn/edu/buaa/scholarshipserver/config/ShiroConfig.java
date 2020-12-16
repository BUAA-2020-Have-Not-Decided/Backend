package cn.edu.buaa.scholarshipserver.config;

import cn.edu.buaa.scholarshipserver.filter.JWTAdminFilter;
import cn.edu.buaa.scholarshipserver.filter.JWTBasicFilter;
import cn.edu.buaa.scholarshipserver.filter.JWTScholarFilter;
import cn.edu.buaa.scholarshipserver.filter.JWTUserFilter;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {
    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new MyModularRealmAuthenticator();
        authenticator.setRealms(Collections.singletonList(jwtRealm()));
        authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return authenticator;
    }
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("jwtRealm") JWTRealm jwtRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        List<Realm> list = new ArrayList<>();
        list.add(jwtRealm);
        securityManager.setRealms(list);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator sessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(sessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        securityManager.setAuthenticator(authenticator());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

        //设置过滤器类型
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt_basic", new JWTBasicFilter());
        filterMap.put("jwt_user", new JWTUserFilter());
        filterMap.put("jwt_admin", new JWTAdminFilter());
        filterMap.put("jwt_scholar", new JWTScholarFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String,String> filterRuleMap = new HashMap<>();
        /*用户系统的过滤器*/
        filterRuleMap.put("/user/login","jwt_basic");
        filterRuleMap.put("/register","anon");
        filterRuleMap.put("/user/jwtLoginUserTest", "jwt_user");
        filterRuleMap.put("/user/toBeScholar", "jwt_user");
        filterRuleMap.put("/user/getInfo", "jwt_user");

        /*消息系统的过滤器*/

        /*学术成果系统的过滤器*/
        filterRuleMap.put("/scholarship/getProjectById/**","anon");
        filterRuleMap.put("/scholarship/advancedSearchProject","anon");
        filterRuleMap.put("/scholarship/advancedSearchProjectSortByDate","anon");

        filterRuleMap.put("/scholarship/getPaperByPaperId/**","anon");
        filterRuleMap.put("/scholarship/advancedSearchPaper","anon");
        filterRuleMap.put("/scholarship/advancedSearchPaperSortByDate","anon");
        filterRuleMap.put("/scholarship/advancedSearchPaperSortByCitationCount","anon");

        filterRuleMap.put("/scholarship/getPatentByPatentId/**","anon");
        filterRuleMap.put("/scholarship/advancedSearchPatent","anon");
        filterRuleMap.put("/scholarship/advancedSearchPatentSortByDate","anon");
        filterRuleMap.put("/scholarship/claimPatent/**","jwt_user");
        filterRuleMap.put("/scholarship/backClaimPatent/**","jwt_user");


        /*门户系统的过滤器*/
        filterRuleMap.put("/scholar/info/**","jwt_user");
        filterRuleMap.put("/scholar/dataScholar/**","anon");
        filterRuleMap.put("/scholar/image/**","jwt_scholar");
        filterRuleMap.put("/scholar/infoUpdate/**","jwt_scholar");
        filterRuleMap.put("/scholar/workExperience/**","jwt_scholar");
        filterRuleMap.put("/scholar/sameName/**","jwt_scholar");
        filterRuleMap.put("/scholar/Scholar_DataScholar/**","jwt_scholar");
        filterRuleMap.put("/scholar/admin/**","jwt_admin");
        filterRuleMap.put("/scholar/subscribe/**","jwt_user");
        filterRuleMap.put("/scholar/Scholar/Search/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return shiroFilterFactoryBean;
    }

    /*@Bean("userRealm")
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setAuthenticationCachingEnabled(false);
        return userRealm;
    }*/

    @Bean("jwtRealm")
    public JWTRealm jwtRealm(){
        JWTRealm jwt_realm = new JWTRealm();
        jwt_realm.setAuthenticationCachingEnabled(false);
        return jwt_realm;
    }

    @Bean(name = "matcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 采用MD5方式加密
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 设置加密次数
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }
}
