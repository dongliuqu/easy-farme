package com.lihao.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author lihao
 * @description: 业务异常
 * @date 2022/3/1 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private String message;
}
