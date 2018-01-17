package com.gt.instruct.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gt.instruct.core.dao.ServerDAO;
import com.gt.instruct.core.entity.Server;
import com.gt.instruct.core.service.ServerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 多粉服务 服务实现类
 * </p>
 *
 * @author psr
 * @since 2018-01-09
 */
@Service
public class ServerServiceImpl extends ServiceImpl<ServerDAO, Server> implements ServerService {

    @Override
    public Server getByProjectNameAndServerStatus(String projectName, Integer serverStatus) throws Exception {
        if (projectName == null || serverStatus == null) {
            throw new Exception("参数错误");
        }

        EntityWrapper<Server> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("project_name", projectName)
                .eq("server_status", serverStatus);

        return selectOne(entityWrapper);
    }
}
