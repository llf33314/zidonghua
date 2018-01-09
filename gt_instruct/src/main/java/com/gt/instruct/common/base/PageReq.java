package com.gt.instruct.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * Created by psr on 2017/8/30 0030.
 */
@ApiModel(value = "分页请求对象")
public abstract class PageReq {

    @ApiModelProperty(value = "当前页数")
    @NotNull(message = "页数不能为空")
    protected Integer current = 0; // 当前页

    @ApiModelProperty(value = "每页显示数")
    @NotNull(message = "每页显示数不能为空")
    @DecimalMax(value = "100", message = "每页显示数不能大于100")
    protected Integer size = 10; // 每页显示数

    public PageReq(){}

    public PageReq(int current, int size){
        this.current = current;
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
