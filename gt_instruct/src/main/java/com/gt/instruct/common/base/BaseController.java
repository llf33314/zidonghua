package com.gt.instruct.common.base;

import com.gt.instruct.common.exception.ResponseEntityException;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * BaseController
 *
 * @author psr
 * @create 2017/7/10
 */
public abstract class BaseController {
    /**
     * 日志
     */
    protected static final Logger logger = Logger.getLogger( BaseController.class );

    /**
     * 参数校验是否合法
     *
     * @param result BindingResult
     */
    protected void InvalidParameter( BindingResult result ) {
        if ( result.hasErrors() ) {
            List<ObjectError> errorList = result.getAllErrors();
            for ( ObjectError error : errorList ) {
                logger.warn( error.getDefaultMessage() );
                throw new ResponseEntityException( error.getDefaultMessage() );
            }
        }
    }

}
