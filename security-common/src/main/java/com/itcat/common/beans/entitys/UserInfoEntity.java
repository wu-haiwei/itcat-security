package com.itcat.common.beans.entitys;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_user_info")
@Data
@Accessors(chain = true)
public class UserInfoEntity {

    @TableId
    private Integer userId;

    private String name;

    private String mobile;

    private Integer accountType;
}
