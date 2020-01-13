package com.itcat.security.service;

import com.itcat.common.beans.entitys.UserAccountEntity;
import com.itcat.common.beans.entitys.UserInfoEntity;
import com.itcat.repository.UserMenuRelationDao;
import com.itcat.security.entity.LoginUser;
import com.itcat.service.IUserInfoService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component("rbacService")
public class RbacServiceImpl implements IRbacService {

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private UserMenuRelationDao userMenuRelationDao;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof LoginUser) {
            Set<String> urls = ((LoginUser) principal).getUrlPermission();
            Integer userId = ((LoginUser) principal).getUserId();
            if(userId.equals(1)){
                // 超级管理员具有所有接口访问权限
                return true;
            }
            if (CollectionUtils.isEmpty(urls)) {
                return hasPermission;
            }
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }

    @Override
    public LoginUser buildLoginUser(UserAccountEntity accountEntity) {
        LoginUser loginUser = new LoginUser();
        if (accountEntity == null) {
            return null;
        }
        UserInfoEntity userInfoEntity = userInfoService.queryById(accountEntity.getUserId());
        if (userInfoEntity == null) {
            return null;
        }
        loginUser.setUserId(userInfoEntity.getUserId())
                .setUsername(accountEntity.getAccount())
                .setPassword(accountEntity.getPassword())
                .setLockedFlag(true);

        List<String> permissions = userMenuRelationDao.listUserPermissions(userInfoEntity.getUserId());
        loginUser.setUrlPermission(new HashSet<>(permissions));
        return loginUser;
    }
}
