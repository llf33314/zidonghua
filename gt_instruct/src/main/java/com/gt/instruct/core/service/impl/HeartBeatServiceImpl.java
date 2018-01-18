package com.gt.instruct.core.service.impl;

import com.gt.instruct.common.exception.SystemException;
import com.gt.instruct.common.util.HttpUtil;
import com.gt.instruct.core.constant.ServerConstant;
import com.gt.instruct.core.entity.Server;
import com.gt.instruct.core.service.HeartBeatService;
import com.gt.instruct.core.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linweicong
 * @version 2018-01-18 10:31:31
 */
@Service
public class HeartBeatServiceImpl implements HeartBeatService {
    @Autowired
    private ServerService serverService;

    @Override
    public int beat(String projectName) throws Exception {
        if (projectName == null) {
            throw new SystemException("参数错误");
        }

        Server server = serverService.getByProjectNameAndServerStatus(projectName, ServerConstant.SERVER_STATUS_OPEN);
        if (server == null) {
            throw new SystemException("找不到服务信息");
        }

        String beatUrl = server.getServerHealthUrl();
        if (beatUrl == null || "".equals(beatUrl.trim())) {
            throw new SystemException("未设置心跳地址");
        }

        return HttpUtil.httpGet(beatUrl);
    }
}
