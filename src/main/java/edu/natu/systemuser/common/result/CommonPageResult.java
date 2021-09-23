package edu.natu.systemuser.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author LJH
 * @des CommonPageResult
 * @date 2021-09-15 16:16:45
 */
@Data
public class CommonPageResult<T> {
    /**
     * 当前页码
     */
    private Long pageNum;
    /**
     * 每页数量
     */
    private Long pageSize;
    /**
     * 总页数
     */
    private Long totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> data;

    public static <T> CommonPageResult<T> success(IPage<T> pageInfo) {
        CommonPageResult<T> result = new CommonPageResult<T>();
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getCurrent());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotal());
        result.setData(pageInfo.getRecords());
        return result;
    }

}
