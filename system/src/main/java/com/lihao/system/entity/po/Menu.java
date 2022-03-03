package com.lihao.system.entity.po;

import com.lihao.sql.po.BasePo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BasePo {
    private Integer parentId;
    private String name;
    private String type;
    private String hrefType;
    private String href;
    private String icon;
    private int orderNum;
    private String description;
    private String permission;

    @ApiModelProperty("自定义图标")
    private String customIcon;

}
