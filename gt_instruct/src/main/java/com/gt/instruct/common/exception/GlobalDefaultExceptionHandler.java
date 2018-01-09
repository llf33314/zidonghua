package com.gt.instruct.common.exception;

import com.gt.instruct.common.dto.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常统一处理
 * <pre>
 *
 * </pre>
 *
 * @author psr
 * @create 2017/6/21
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger( GlobalDefaultExceptionHandler.class );

    // 全局默认错误页
    public static final String DEFAULT_ERROR_VIEW = "error/defaultError";

    // 页面
    // 统一异常处理 页面跳转
    @ExceptionHandler( value = Exception.class )
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "ex", e );
        modelAndView.addObject( "url", request.getRequestURL() );
        modelAndView.setViewName( DEFAULT_ERROR_VIEW );
        return modelAndView;
    }

    // 统一异常处理 Ajax请求
    @ResponseBody
    @ExceptionHandler( value = ResponseEntityException.class )
    public ErrorDTO< String > defaultErrorHandler(HttpServletRequest request, ResponseEntityException e ) {
        logger.error( "Ajax 异常：{} , 请求地址：{}", e.getMessage(), request.getRequestURL(), e );
        return ErrorDTO.createByErrorCodeMessage( e.getCode(), e.getMessage(), null );
    }
}
