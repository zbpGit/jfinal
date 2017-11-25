package com.zbp.myRealm;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/21.
 */
public class MyRealm extends AuthorizingRealm {


    /**
     * 为登录成功的用户授予权限或者角色
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
         String userName = (String) principalCollection.getPrimaryPrincipal();
         SimpleAuthorizationInfo info= new SimpleAuthorizationInfo();
         try {
             Record record = Db.findFirst("select * from t_user u,t_role r where u.roleId = r.id and u.userName = ?",userName);
             //设置用户的角色
             info.setRoles(shiroSet((String) record.get("roleName")));
             Record record1 = Db.findFirst("select * from t_user u,t_role r,t_permission p where u.roleId = r.id and p.roleId = r.id and u.userName = ?",userName);
             //设置用户的权限
             info.setStringPermissions(shiroSet((String) record1.get("permissionName")));
         }catch (Exception e){
             e.printStackTrace();
         }
        return info;
    }
    /**
     * 验证当前登录的用户
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取当前登录的用户
        String userName = (String) token.getPrincipal();
        try {
            Record record = Db.findFirst("select * from t_user where userName = ?",userName);
            if (record!=null){
                String userName1 = record.get("userName");
                String password = record.get("password");
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userName1,password,"zbp");
                return authcInfo;
            }else {
                return null;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public Set<String> shiroSet(String shiro){
        Set<String> roles = new HashSet<String>();
        roles.add(shiro);
        return roles;
    }
}
