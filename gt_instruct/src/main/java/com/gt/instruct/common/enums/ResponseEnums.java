package com.gt.instruct.common.enums;

/**
 * 响应成功Code 类
 * Created by psr on 2017/8/24 0024.
 */
public enum ResponseEnums {

    SUCCESS(100, "请求成功"),
    // 200+针对请求失败
    ERROR(200, "请求失败"),
    LOGIN(201, "请先登录"),
    BUS_NULL(202, "数据不存在"),
    // 300+针对统一错误
    DIFF_USER(301, "无权操作的信息"),
    INFO_NULL(302, "操作信息不存在"),
    DATA_ERROR(303, "数据不合法");
    // 400+针对自己的错误
    ;

    private final int code;
    private final String desc;

    ResponseEnums(int code, String desc ) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}