package edu.natu.systemuser.common.result;

import lombok.Data;

/**
 * @author LJH
 * @des 公共分页请求参数
 * @date 2021-09-15 15:07:30
 */
@Data
public class CommonPageParams<T> {
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 真正参数
     */
    private T real;


}
