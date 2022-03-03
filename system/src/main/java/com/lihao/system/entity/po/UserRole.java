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
@TableName("user_role_relation")
public class UserRole extends BasePo {
    private Integer userId;
    private Integer roleId;
}
