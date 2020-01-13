package com.itcat.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LoginUser implements UserDetails {

    private Integer userId;

    private String username;

    private String password;

    private boolean lockedFlag;

    private List<Role> roles;

    private Set<String> urlPermission;

    public LoginUser() {
        roles = new ArrayList<>();
        urlPermission = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (CollectionUtils.isEmpty(roles)) {
            return authorities;
        }
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 用户账号是否过期
     * true: 未过期
     * false: 已过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被锁定
     * true: 未锁定
     * false: 锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return lockedFlag;
    }

    /**
     * 用户账号凭证(密码)是否过期
     * 简单的说就是可能会因为修改了密码导致凭证过期这样的场景
     * true: 过期
     * false: 无效
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被启用
     * true: 启用
     * false: 未启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    public LoginUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public LoginUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public LoginUser setLockedFlag(boolean lockedFlag) {
        this.lockedFlag = lockedFlag;
        return this;
    }

    public LoginUser setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public LoginUser addRoles(Role role) {
        this.roles.add(role);
        return this;
    }

    public Set<String> getUrlPermission() {
        return urlPermission;
    }

    public LoginUser setUrlPermission(Set<String> urlPermission) {
        this.urlPermission = urlPermission;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public LoginUser setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
