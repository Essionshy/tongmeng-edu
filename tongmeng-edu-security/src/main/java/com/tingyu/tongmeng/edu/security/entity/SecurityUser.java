package com.tingyu.tongmeng.edu.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author essionshy
 * @Create 2020/11/2 20:26
 * @Version tongmeng-edu
 */
@Data
public class SecurityUser implements UserDetails {

    //当前用户
    private transient LoginUser currentLoginUser;

    //当前权限
    private List<String> permissionValueList;

    public SecurityUser(){}

    public SecurityUser(LoginUser user){
        if(user!=null){
            this.currentLoginUser=user;
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String permissionValue : permissionValueList) {
            if(StringUtils.isEmpty(permissionValue)) continue;
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
            authorities.add(authority);
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return currentLoginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return currentLoginUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
