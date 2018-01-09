package com.gt.instruct.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gt.instruct.common.enums.ResponseEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 服务响应类
 * 统一响应格式返回
 *
 * Created by psr on 2017/8/24 0024.
 */
@ApiModel(value = "统一响应格式")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

    @ApiModelProperty(value = "状态码", name = "状态码", example = "100")
    private int code;

    @ApiModelProperty(value = "状态码描述", name = "状态码描述")
    private String msg;

    @ApiModelProperty(value = "数据对象", name = "数据对象")
    private T data;

    @ApiModelProperty(value = "分页对象", name = "分页对象")
    private PageDTO page;

    protected ResponseDTO(int code){
        this.code = code;
    }

    protected ResponseDTO(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    protected ResponseDTO(int code, T data ) {
        this.code = code;
        this.data = data;
    }

    protected ResponseDTO(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    protected ResponseDTO(int code, String msg, T data, PageDTO page){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.page = page;
    }

    /**
     * 创建响应成功
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createBySuccess() {
        return createBySuccessMessage( ResponseEnums.SUCCESS.getDesc() );
    }

    /**
     * 创建响应成功
     *
     * @param data 数据包
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createBySuccess( T data ) {
        return createBySuccess( null, data );
    }

    /**
     * 创建响应成功
     *
     * @param msg 返回消息
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createBySuccessMessage( String msg ) {
        return createBySuccess( msg, null );
    }

    /**
     * 创建响应成功
     *
     * @param msg  消息
     * @param data 数据包
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createBySuccess( String msg, T data ) {
        return createBySuccessCodeMessage( ResponseEnums.SUCCESS.getCode(), msg, data );
    }

    /**
     * 创建响应成功
     *
     * @param msg  消息
     * @param data 数据包
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createBySuccessPage( String msg, T data, PageDTO page ) {
        return createBySuccessCodeMessagePage( ResponseEnums.SUCCESS.getCode(), msg, data, page );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态码
     * @param msg  消息
     * @param data 数据包
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createBySuccessCodeMessage( int code, String msg, T data ) {
        return new ResponseDTO<>( code, msg, data );
    }

    /**
     * 创建响应成功
     *
     * @param code 状态码
     * @param msg  消息
     * @param data 数据包
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createBySuccessCodeMessagePage( int code, String msg, T data, PageDTO page ) {
        return new ResponseDTO<>( code, msg, data, page );
    }

    /**
     * 创建响应失败
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createByError() {
        return createByErrorCodeMessage( ResponseEnums.ERROR.getCode(), ResponseEnums.ERROR.getDesc() );
    }

    /**
     * 创建响应失败
     *
     * @param errorMessage 消息
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createByErrorMessage( String errorMessage ) {
        return createByErrorCodeMessage( ResponseEnums.ERROR.getCode(), errorMessage );
    }

    /**
     * 创建响应失败
     *
     * @param errorCode    状态码
     * @param errorMessage 消息
     *
     * @return ResponseDTO
     */
    public static < T > ResponseDTO< T > createByErrorCodeMessage( int errorCode, String errorMessage ) {
        return new ResponseDTO<>( errorCode, errorMessage );
    }

    //使之不在json序列化结果当中，作用用于判断
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ResponseEnums.SUCCESS.getCode();
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public PageDTO getPage() {
        return page;
    }
}





























