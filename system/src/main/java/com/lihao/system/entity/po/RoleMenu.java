package com.lihao.system.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lihao.sql.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_menu_relation")
public class RoleMenu extends BasePo {
    private Integer roleId;
    private String menuId;
}
