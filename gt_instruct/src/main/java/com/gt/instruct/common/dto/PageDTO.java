package com.gt.instruct.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by psr on 2017/9/14 0014.
 */
@ApiModel(value = "分页对象")
public class PageDTO {

    @ApiModelProperty(value = "总页数")
    protected Integer totalPages = 0; // 总页数

    @ApiModelProperty(value = "总数")
    protected Integer totalNums = 0; // 总数

    public PageDTO(){}

    public PageDTO(int totalPages, int totalNums){
        this.totalPages = totalPages;
        this.totalNums = totalNums;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(Integer totalNums) {
        this.totalNums = totalNums;
    }

}
