package com.lihao.common.entity.dto;

import lombok.Data;

/**
 * 统计图表数据格式
 * @author liangjinquan
 * */
@Data
public class StatisticalNode<T>{

    public StatisticalNode(){}

    public StatisticalNode(String name, String code, T value){
        this.name = name;
        this.code = code;
        this.value = value;
    }

    private String name;
    private String code;
    private T value;
	
}
