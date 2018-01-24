package com.hdbase.auth.repository;


import com.hdbase.auth.domain.SysUser;
import com.hdbase.auth.repository.support.WiselyRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import javax.persistence.Entity;
import java.util.Optional;

/**
 * Created by wangyunfei on 2017/6/9.
 */
public interface SysUserRepository extends WiselyRepository<SysUser,Long> {
    Optional<SysUser> findOneWithRolesByUsername(String username);
}
