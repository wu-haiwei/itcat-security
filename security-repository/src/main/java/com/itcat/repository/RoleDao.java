package com.itcat.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itcat.common.beans.entitys.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {
}
