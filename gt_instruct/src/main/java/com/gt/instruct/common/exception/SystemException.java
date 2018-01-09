package com.gt.instruct.common.exception;


import com.gt.instruct.common.enums.ResponseEnums;

/**
 * 系统统一异常类
 * <pre>
 *     所有自定义的异常，都继承此类。
 * </pre>
 *
 * @author psr
 * @create 2017/6/16
 */
public class SystemException extends RuntimeException {

    private int code;//状态码

    private String message;//错误消息

    public SystemException(String message ) {
        super( message );
        this.message = message;
    }

    public SystemException(int code, String message ) {
        super( message );
        this.message = message;
        this.code = code;
    }

    public SystemException(ResponseEnums responseEnums ) {
        super( responseEnums.getDesc() );
        this.code = responseEnums.getCode();
        this.message = responseEnums.getDesc();
    }

    public int getCode() {
	    return code;
    }

    @Override
    public String getMessage() {
	    return message;
    }
}
