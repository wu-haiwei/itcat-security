package com.itcat.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.itcat.common.beans.entitys.MenuEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {
}
