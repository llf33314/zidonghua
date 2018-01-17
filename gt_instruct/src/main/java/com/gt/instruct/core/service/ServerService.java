package com.gt.instruct.core.service;

import com.baomidou.mybatisplus.service.IService;
import com.gt.instruct.core.entity.Server;

/**
 * <p>
 * 多粉服务 服务类
 * </p>
 *
 * @author psr
 * @since 2018-01-09
 */
public interface ServerService extends IService<Server> {

    /**
     * 获取服务信息
     *
     * @param projectName  项目名称
     * @param serverStatus 服务状态
     * @return Server
     * @throws Exception Exception
     */
    Server getByProjectNameAndServerStatus(String projectName, Integer serverStatus) throws Exception;

}
