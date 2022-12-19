package com.gt.common.entity;

import java.util.List;

/**
 * 分页
 * {
 * “success”：“成功”，
 * “code”：10000
 * “message”：“ok”，
 * ”data“：{
 * total：//总条数
 * rows ：//数据列表
 * }
 * }
 */

public class PageResult<T> {
    private Long total;
    private List<T> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
