package me.injoker.config;



import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        Map<String ,String > filterMap=new LinkedHashMap<>();
//       filterMap.put("/user/add","authc");
//       filterMap.put("/user/update","authc");
       filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
       bean.setFilterChainDefinitionMap(filterMap);

       bean.setLoginUrl("/login");
       bean.setUnauthorizedUrl("/unauth");

        return bean;
    }


    @Bean(name="manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

  @Bean
  public UserRealm userRealm(){
      return new UserRealm();
  }

@Bean
  public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
  }
}
