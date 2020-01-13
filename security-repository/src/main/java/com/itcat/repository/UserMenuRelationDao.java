package com.itcat.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMenuRelationDao {


    List<String> listUserPermissions(@Param("userId") Integer userId);
}
