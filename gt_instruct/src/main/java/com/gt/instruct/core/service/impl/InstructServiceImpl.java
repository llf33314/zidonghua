package com.gt.instruct.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.gt.insos.common.axis.user.bean.dto.UserDTO;
import com.gt.instruct.core.entity.Server;
import com.gt.instruct.core.service.CommonService;
import com.gt.instruct.core.service.InstructService;
import com.gt.instruct.core.service.ServerService;
import com.gt.instruct.core.service.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
@Service
public class InstructServiceImpl implements InstructService {

    private static final Logger log = Logger.getLogger(InstructServiceImpl.class);

    @Autowired
    CommonService commonService;

    @Autowired
    TokenService tokenService;

    @Value("${instruct.home.url}")
    private String homeUrl;

    @Value("${instruct.home.suffix}")
    private String homeSuffix;

    @Value("${instruct.start.prefix}")
    private String startPrefix;

    @Value("${instruct.stop.prefix}")
    private String stopPrefix;

    @Value("${instruct.syn.prefix}")
    private String synPrefix;

    @Value("${instruct.ps.prefix}")
    private String psPrefix;

    @Value("${instruct.chg.prefix}")
    private String chgPrefix;

    @Autowired
    ServerService serverService;

    /**
     * 启动项目
     *
     * @param projectName
     * @return
     * @exception Exception
     */
    @Override
    public String runStartCmd(String projectName) throws Exception {
        String cmd = homeUrl + projectName + startPrefix + homeSuffix;
        String result = commonService.runCmd(cmd);
        log.debug(result);
        return result;
    }

    /**
     * 关闭项目
     *
     * @param projectName
     * @return
     * @exception Exception
     */
    @Override
    public String runStopCmd(String projectName) throws Exception {
        String cmd = homeUrl + projectName + stopPrefix + homeSuffix;
        String result = commonService.runCmd(cmd);
        log.debug(result);
        return result;
    }

    /**
     * 同步项目
     *
     * @param projectName
     * @return
     * @exception Exception
     */
    @Override
    public String rumSynchrocmd(String projectName) throws Exception {
        // 将项目复制到项目中
        String cmd = homeUrl + projectName + synPrefix + homeSuffix;
        String result = commonService.runCmd(cmd);
        log.debug(result);
        return result;
    }

    /**
     * 清除项目PID
     *
     * @param projectName
     * @return
     * @exception Exception
     */
    @Override
    public String rumPscmd(String projectName) throws Exception {
        // 将项目复制到项目中
        String cmd = homeUrl + projectName + psPrefix + homeSuffix;
        String result = commonService.runCmd(cmd);
        log.debug(result);
        return result;
    }

    /**
     * 获取服务列表
     *
     * @return
     */
    @Override
    public List<Server> listServer(String token) throws Exception {
        UserDTO userDTO = tokenService.getUserByToken(token);
        EntityWrapper<Server> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("server_status", 0);
        entityWrapper.like("user_group", userDTO.getUserName());
        List<Server> serverList = serverService.selectList(entityWrapper);
        return serverList;
    }

    /**
     * 更换目录不需要的文件
     *
     * @param projectName
     * @return
     */
    @Override
    public String rumChgcmd(String projectName) throws Exception {
        // 将项目复制到项目中
        String cmd = homeUrl + projectName + chgPrefix + homeSuffix;
        String result = commonService.runCmd(cmd);
        log.debug(result);
        return result;
    }
}






















