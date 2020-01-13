package com.itcat.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itcat.common.beans.entitys.UserAccountEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAccountDao extends BaseMapper<UserAccountEntity> {
}
