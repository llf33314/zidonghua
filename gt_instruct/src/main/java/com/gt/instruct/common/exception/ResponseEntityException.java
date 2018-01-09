package com.gt.instruct.common.exception;


import com.gt.instruct.common.enums.ResponseEnums;

/**
 * Ajax 异常
 * <pre>
 *     ajax 请求的异常处理类
 * </pre>
 *
 * @author zhangmz
 * @create 2017/6/21
 */
public class ResponseEntityException extends SystemException {

    public ResponseEntityException(String message ) {
	super( message );
    }

    public ResponseEntityException(int code, String message ) {
	super( code, message );
    }

    public ResponseEntityException(ResponseEnums responseEnums ) {
	    super( responseEnums );
    }
}
