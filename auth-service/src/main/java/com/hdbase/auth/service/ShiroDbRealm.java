package com.hdbase.auth.service;

import com.hdbase.auth.bean.MySimpleByteSource;
import com.hdbase.auth.bean.ShiroUser;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

public class ShiroDbRealm extends AuthorizingRealm
{
    public static final String HASH_ALGORITHM = "SHA-1";

    public static final int HASH_INTERATIONS = 1024;

    private static final int SALT_SIZE = 8;

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
        throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        byte[] salt = new byte[0];
        try {
            salt = Hex.decodeHex("123456".toCharArray());
            ShiroUser shiroUser=new ShiroUser(1,token.getUsername(), token.getUsername());
            return new SimpleAuthenticationInfo(shiroUser,123456, new MySimpleByteSource(salt), getName());
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        ShiroUser shiroUser = (ShiroUser)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> list=new ArrayList<String>();
        list.add("*");
        info.addStringPermissions(list);
        return info;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @SuppressWarnings("static-access")
    @PostConstruct
    public void initCredentialsMatcher()
    {
//        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
//        matcher.setHashIterations(HASH_INTERATIONS);
//        setCredentialsMatcher(matcher);
        setCredentialsMatcher(new HashedCredentialsMatcher("md5"));
    }

    public String encrypt(String password,String salt) {
        userLogin.setSalt(randomNumberGenerator.nextBytes().
                toHex());
        String newPassword =
                new SimpleHash(algorithmName,userLogin.getPassword(),
                        ByteSource.Util.bytes(userLogin.getCredentialsSalt())           ,hashIterations).toHex();

        userLogin.setPassword(newPassword);
        return userLogin;
    }
}

