package com.itcat.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itcat.common.beans.entitys.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {

}
