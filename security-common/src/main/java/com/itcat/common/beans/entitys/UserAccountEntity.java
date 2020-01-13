package com.itcat.common.beans.entitys;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_user_account")
@Data
@Accessors(chain = true)
public class UserAccountEntity {

    private Integer userId;

    private String account;

    private String password;
}
