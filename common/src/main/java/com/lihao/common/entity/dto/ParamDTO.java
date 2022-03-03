package com.lihao.common.entity.dto;

import lombok.Data;

/**
 * 用于解决前端参数json不能传数组的问题
 *
 * @param <T>
 */
@Data
public class ParamDTO<T> {

    /**
     * 参数
     */
    private T param;
}
