package com.hdbase.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by huangdan on 2018/1/25.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        if (account == null) {
            throw new UsernameNotFoundException("用户:" + account + ",不存在!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        boolean enabled = true; // 可用性 :true:可用 false:不可用
        boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定
//        List<SystemMenu> systemMenuList = systemUserService.listMenuByUserId(userEntity.getId());
//        for (SystemMenu menu:systemMenuList
//                ) {
//            GrantedAuthority authority = new SimpleGrantedAuthority(menu.getLink());
//            grantedAuthorities.add(authority);
//        }
        GrantedAuthority authority = new SimpleGrantedAuthority("root");
        grantedAuthorities.add(authority);
        User user = new User(account, account,
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
