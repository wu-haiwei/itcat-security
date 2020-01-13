package com.itcat.service;

import com.itcat.common.beans.entitys.UserAccountEntity;
import com.itcat.common.beans.entitys.UserInfoEntity;
import com.itcat.common.beans.manager.req.CreateUserForm;
import com.itcat.common.beans.resp.BaseResponse;
import com.itcat.repository.UserAccountDao;
import com.itcat.repository.UserInfoDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserAccountDao userAccountDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserAccountEntity queryAccountByUserName(String userName) {
        return userAccountDao.selectOne(new UserAccountEntity().setAccount(userName));
    }

    @Override
    public UserInfoEntity queryById(Integer userId) {
        return userInfoDao.selectById(userId);
    }

    @Override
    public BaseResponse<Integer> createUser(CreateUserForm form) {
        BaseResponse<Integer> response = new BaseResponse<>();
        UserInfoEntity entity = new UserInfoEntity();
        entity.setName(form.getName()).setMobile(form.getMobile()).setAccountType(form.getAccountType());
        userInfoDao.insert(entity);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserAccountEntity accountEntity = new UserAccountEntity();
        accountEntity.setAccount(form.getAccount())
                .setPassword(passwordEncoder.encode(form.getPassword()))
                .setUserId(entity.getUserId());
        userAccountDao.insert(accountEntity);

        response.setData(entity.getUserId());
        return response;
    }
}
