package com.gt.instruct.core.service;

import com.gt.instruct.core.entity.Server;

import java.util.List;

/**
 * @author psr
 *         Created by psr on 2017/7/19 0019.
 */
public interface InstructService {

    /**
     * 启动项目
     *
     * @param projectName
     * @return
     * @exception Exception
     */
    String runStartCmd(String projectName) throws Exception;

    /**
     * 关闭项目
     *
     * @param projectName
     * @return
     * @exception Exception
     */
    String runStopCmd(String projectName) throws Exception;

    /**
     * 同步项目
     *
     * @param projectName
     * @return
     * @exception Exception
     */
    String rumSynchrocmd(String projectName) throws Exception;

    /**
     * 获取服务列表
     *
     * @return
     */
    List<Server> listServer() throws Exception;

    /**
     * 清除PID
     *
     * @param projectName
     * @return
     */
    String rumPscmd(String projectName) throws Exception;
}
