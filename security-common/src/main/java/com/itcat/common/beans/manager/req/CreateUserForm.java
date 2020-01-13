package com.itcat.common.beans.manager.req;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserForm {

    private String account;

    private String name;

    private String password;

    private String mobile;

    private Integer accountType;
}
