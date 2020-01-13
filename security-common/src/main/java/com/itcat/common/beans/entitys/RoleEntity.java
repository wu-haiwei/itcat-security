package com.itcat.common.beans.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_role")
@Data
@Accessors(chain = true)
public class RoleEntity {

    private Integer roleId;

    private String name;

    private String role;
}
