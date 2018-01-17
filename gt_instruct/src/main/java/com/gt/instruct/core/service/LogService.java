package com.gt.instruct.core.service;

import java.util.List;
import java.util.Map;

/**
 * @author linweicong
 * @version 2018-01-17 09:57:23
 */
public interface LogService {
    /**
     * 获取日志文件名列表
     *
     * @param projectName 项目名称
     * @return List<String>
     * @throws Exception Exception
     */
    List<String> listLogFileName(String projectName) throws Exception;

    /**
     * 获取日志文件内容
     *
     * @param projectName 项目名称
     * @param logFileName 日志文件名
     * @param optPosition 内容开始位置
     * @return String
     * @throws Exception Exception
     */
    Map<String, Object> getLogFileContent(String projectName, String logFileName, Long optPosition) throws Exception;

    /**
     * 获取日志文件所有内容
     *
     * @param projectName 项目名称
     * @param logFileName 日志文件名
     * @return File
     * @throws Exception Exception
     */
    List<String> listLogFileContent(String projectName, String logFileName) throws Exception;
}
