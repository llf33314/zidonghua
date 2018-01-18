package com.gt.instruct.core.service;

/**
 * @author linweicong
 * @version 2018-01-18 10:29:34
 */
public interface HeartBeatService {
    /**
     * 心跳机制
     *
     * @param projectName 项目名称
     * @return url请求返回状态码
     * @throws Exception Exception
     */
    int beat(String projectName) throws Exception;
}
