package cn.hdbase.common.shiro;

import cn.hdbase.common.utils.Digests;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ShiroDbRealm extends AuthorizingRealm
{
    public static final String HASH_ALGORITHM = "SHA-1";

    public static final int HASH_INTERATIONS = 1024;

    private static final int SALT_SIZE = 8;

//    byte[] salt = Digests.generateSalt(SALT_SIZE);

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
        throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        try {
            String password=encrypt("123456");
            System.out.println("password:"+password);
            ShiroUser shiroUser=new ShiroUser(1,token.getUsername(), token.getUsername());
            return  new SimpleAuthenticationInfo(shiroUser, password,getName());
//            return new SimpleAuthenticationInfo(shiroUser,password, new MySimpleByteSource(salt), getName());
        } catch (Exception e) {
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
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
        matcher.setHashIterations(HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    public String encrypt(String password) {
        byte[] hashPassword = Digests.sha1(password.getBytes(), null,HASH_INTERATIONS);
        return Hex.encodeHexString(hashPassword);
    }
}

