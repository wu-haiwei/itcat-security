package com.itcat.security.validate;

import com.itcat.common.beans.entitys.UserAccountEntity;
import com.itcat.security.entity.LoginUser;
import com.itcat.security.service.IRbacService;
import com.itcat.service.IUserInfoService;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

public class LoginValidateAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private IRbacService rbacService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取输入的用户名
        String username = (String) authentication.getPrincipal();
        //获取输入的明文
        String rawPassword = (String) authentication.getCredentials();

        UserAccountEntity userAccountEntity = userInfoService.queryAccountByUserName(username);

        if (userAccountEntity == null) {
            throw new UsernameNotFoundException("账号未注册!");
        }

        //验证密码
        if (!passwordEncoder.matches(rawPassword, userAccountEntity.getPassword())) {
            throw new BadCredentialsException("输入密码错误!");
        }

        LoginUser loginUser = rbacService.buildLoginUser(userAccountEntity);

//        if (!loginUser.isEnabled()) {
//            throw new DisabledException("该账户已被禁用，请联系管理员");
//
//        } else if (!loginUser.isAccountNonLocked()) {
//            throw new LockedException("该账号已被锁定");
//
//        } else if (!loginUser.isAccountNonExpired()) {
//            throw new AccountExpiredException("该账号已过期，请联系管理员");
//
//        } else if (!loginUser.isCredentialsNonExpired()) {
//            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录");
//        }
        return new UsernamePasswordAuthenticationToken(loginUser, rawPassword, loginUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //确保authentication能转成该类
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
