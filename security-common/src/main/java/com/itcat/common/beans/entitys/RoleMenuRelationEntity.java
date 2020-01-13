package com.itcat.common.beans.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_role_menu_relation")
@Data
@Accessors(chain = true)
public class RoleMenuRelationEntity {

    private Integer roleId;

    private Integer menuId;
}
