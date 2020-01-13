package com.itcat.security.service;

import com.itcat.common.beans.entitys.UserAccountEntity;
import com.itcat.security.entity.LoginUser;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IRbacService {

    /**
     * 判断接口范文权限
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);

    /**
     * 构建登录信息
     * @param accountEntity
     * @return
     */
    LoginUser buildLoginUser(UserAccountEntity accountEntity);
}
