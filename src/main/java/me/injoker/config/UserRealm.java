package me.injoker.config;

import me.injoker.pojo.User;
import me.injoker.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm  extends AuthorizingRealm {


    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject= SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        info.addStringPermission(principal.getPerms());
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");



        UsernamePasswordToken token1 = (UsernamePasswordToken) token;

        User user = userService.queryUser(token1.getUsername());
        if(user==null){
            return null;
        }

        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
