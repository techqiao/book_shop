package com.wjq.common;

/**
 * 排序DTO
 */
public class SortDto {

    /**
     * 排序方式
     */
    private String sortType;

    /**
     * 排序字段
     */
    private String sortField;

    public SortDto(String sortType, String sortField) {
        super();
        this.sortType = sortType;
        this.sortField = sortField;
    }

    /**
     * 默认排序
     *
     * @param sortField 排序字段
     */
    public SortDto(String sortField) {
        super();
        this.sortField = sortField;
        this.sortType = "desc";
    }

    public SortDto() {
        super();
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

}
