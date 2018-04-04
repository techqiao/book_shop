package com.wjq.common;

import org.springframework.data.domain.Sort;

/**
 * 排序工具类
 */
public class SortTools {

    /**
     * 根据 id 排序
     *
     * @param id 实体类的id
     * @return org.springframework.data.domain.Sort
     */
    public static Sort basicSort(String id) {
        return basicSort("desc", id);
    }

    /**
     * 根据字段名排序
     *
     * @param sortType   排序方式
     * @param orderField 排序字段
     * @return org.springframework.data.domain.Sort
     */
    public static Sort basicSort(String sortType, String orderField) {
        return new Sort(Sort.Direction.fromString(sortType), orderField);
    }

    /**
     * 根据多个字段进行排序
     *
     * @param dtos 排序DTO，可变参数，不传默认
     * @return org.springframework.data.domain.Sort
     * @author zhangjiao 2016年12月5日
     */
    public static Sort basicSort(SortDto... dtos) {
        Sort result = null;
        for (SortDto sortDto : dtos) {
            if (result == null) {
                result = new Sort(Sort.Direction.fromString(sortDto.getSortType()), sortDto.getSortField());
            } else {
                result = result.and(new Sort(Sort.Direction.fromString(sortDto.getSortType()), sortDto.getSortField()));
            }
        }
        return result;
    }

}
