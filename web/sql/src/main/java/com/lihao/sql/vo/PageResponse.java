package com.lihao.sql.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页响应体
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class PageResponse<T> {

    /**
     * 查询数据列表
     */
    private List<T> records = null;

    /**
     * 总数
     */
    private long total;

    /**
     * 每页显示条数，默认 10
     */
    private long size;

    /**
     * 当前页
     */
    private long current;

    /**
     * 总页数
     */
    private long pages;

    public <R> PageResponse(IPage<R> iPage, Function<R, T> mapper) {
        this.total = iPage.getTotal();
        this.size = iPage.getSize();
        this.current = iPage.getCurrent();
        this.pages = iPage.getPages();
        this.records = iPage.getRecords().stream().map(mapper).collect(Collectors.toList());
    }


    public PageResponse(IPage<T> iPage) {
        this.total = iPage.getTotal();
        this.size = iPage.getSize();
        this.current = iPage.getCurrent();
        this.pages = iPage.getPages();
        this.records = iPage.getRecords();
    }
}
