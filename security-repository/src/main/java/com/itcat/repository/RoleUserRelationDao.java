package com.itcat.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itcat.common.beans.entitys.RoleUserRelationEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleUserRelationDao extends BaseMapper<RoleUserRelationEntity> {
}
