package com.itcat.common.beans.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_role_user_relation")
@Data
@Accessors(chain = true)
public class RoleUserRelationEntity {

    private Integer roleId;

    private Integer userId;
}
