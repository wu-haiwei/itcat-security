package com.itcat.common.beans.entitys;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@TableName("t_menu")
@Data
@Accessors(chain = true)
public class MenuEntity {

    @TableId
    private Integer menuId;

    private String name;

    private Integer parentId;

    private Integer type;

    private String url;

    private String permission;
}
