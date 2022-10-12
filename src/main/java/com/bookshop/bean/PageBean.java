package com.bookshop.bean;

/**
 * 保存分页信息
 */
public class PageBean {
    private Integer pageNow;//当前页，从页面获取
    private Integer pageSize=10;//每页的行数，自己定义
    private Integer rowCount;//表中总行数，查询数据库
    private Integer pageCount;//总页数，需计算

    public PageBean() {
    }
    public PageBean(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public PageBean(Integer pageNow, Integer pageSize) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
    }

    public PageBean(Integer pageNow, Integer pageSize, Integer rowCount) {
        this.pageNow = pageNow;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNow=" + pageNow +
                ", pageSize=" + pageSize +
                ", rowCount=" + rowCount +
                ", pageCount=" + pageCount +
                '}';
    }
}
