package com.itcat.controller;

import com.itcat.common.beans.manager.req.CreateUserForm;
import com.itcat.common.beans.resp.BaseResponse;
import com.itcat.service.IUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/user")
public class UserManagerController {

    private static Logger logger = LoggerFactory.getLogger(UserManagerController.class);

    @Resource
    private IUserInfoService userInfoService;

    @PostMapping("/create")
    public BaseResponse<Integer> create(@RequestBody CreateUserForm form){

        return userInfoService.createUser(form);
    }
}
