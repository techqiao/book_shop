package com.wjq.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 分页工具类
 */
public class PageableTools {

    /**
     * 基础分页对象,默认15条
     *
     * @param pageNo   第几页
     * @param pageSize 每页数据条数
     * @param dtos     排序DTO
     * @return org.springframework.data.domain.Pageable
     */
    public static Pageable basicPage(Integer pageNo, Integer pageSize, SortDto... dtos) {
        Sort sort = SortTools.basicSort(dtos);
        pageNo = (pageNo == null || pageNo < 0) ? 0 : pageNo;
        pageSize = (pageSize == null || pageSize < 0) ? 15 : pageSize;
        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        return pageable;
    }

    /**
     * 基础分页对象，每页条数默认15条,默认以id降序排序
     *
     * @param pageNo 第几页
     * @return org.springframework.data.domain.Pageable
     */
    public static Pageable basicPage(Integer pageNo) {
        return basicPage(pageNo, 0, new SortDto("desc", "id"));
    }

    /**
     * 基础分页对象，每页条数默认15条
     *
     * @param pageNo 第几页
     * @param dtos   排序DTO
     * @return 基础分页对象，每页条数默认15条
     */
    public static Pageable basicPage(Integer pageNo, SortDto... dtos) {
        return basicPage(pageNo, 0, dtos);
    }

    /**
     * 基础分页对象，根据字段排序，排序方式默认降序
     *
     * @param pageNo    第几页
     * @param pageSize  每页数据条数
     * @param sortField 排序字段
     * @return org.springframework.data.domain.Pageable
     */
    public static Pageable basicPage(Integer pageNo, Integer pageSize, String sortField) {
        return basicPage(pageNo, pageSize, new SortDto("desc", sortField));
    }

    /**
     * 基础分页对象，根据字段排序，排序方式默认降序
     *
     * @param pageNo    第几页
     * @param sortField 排序字段
     * @return org.springframework.data.domain.Pageable
     */
    public static Pageable basicPage(Integer pageNo, String sortField) {
        return basicPage(pageNo, 0, new SortDto("desc", sortField));
    }

}
