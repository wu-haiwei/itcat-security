package com.itcat.service;

import com.itcat.common.beans.entitys.UserAccountEntity;
import com.itcat.common.beans.entitys.UserInfoEntity;
import com.itcat.common.beans.manager.req.CreateUserForm;
import com.itcat.common.beans.resp.BaseResponse;

public interface IUserInfoService {

    /**
     * 更具用户名查找账号信息
     *
     * @param userName 用户名
     * @return
     */
    UserAccountEntity queryAccountByUserName(String userName);

    /**
     * 根据id查询
     * @param userId
     * @return
     */
    UserInfoEntity queryById(Integer userId);

    /**
     * 创建用户
     * @param form
     * @return
     */
    BaseResponse<Integer> createUser(CreateUserForm form);
}
