package com.lihao.common.utils;

import lombok.Data;

import java.util.List;

/**
 * 基础数状视图对象
 */
@Data
public abstract class BaseTreeVO {

    private Integer id;

    private Integer pId;

    private String name;


    private List<BaseTreeVO> children;
}
