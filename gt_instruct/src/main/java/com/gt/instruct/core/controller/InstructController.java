package com.gt.instruct.core.controller;

import com.gt.instruct.common.dto.ResponseDTO;
import com.gt.instruct.common.exception.SystemException;
import com.gt.instruct.core.entity.Server;
import com.gt.instruct.core.service.InstructService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author psr
 *         Created by Administrator on 2017/7/18 0018.
 */
@Api(value = "/app/instruct", description = "自动化部署Controller")
@RestController
@RequestMapping("/app/instruct")
public class InstructController {

    private static final Logger logger = Logger.getLogger(InstructController.class);

    @Autowired
    private InstructService instructService;

    /**
     * 获取服务列表
     *
     * @return
     */
    @ApiOperation(value = "获取服务列表")
    @RequestMapping(value = "listServer", method = RequestMethod.POST)
    public ResponseDTO listServer() {
        try {
            logger.debug("listServer");
            List<Server> serverList = instructService.listServer();
            return ResponseDTO.createBySuccess(serverList);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }

    /**
     * 启动服务
     *
     * @param projectName
     * @return
     */
    @ApiOperation(value = "启动服务")
    @RequestMapping(value = "rumStartcmd/{projectName}", method = RequestMethod.POST)
    public ResponseDTO rumStartcmd(@PathVariable String projectName) {
        try {
            logger.debug(projectName);
            String results = instructService.runStartCmd(projectName);
            return ResponseDTO.createBySuccess(results);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }

    /**
     * 关闭服务
     *
     * @param projectName
     * @return
     */
    @ApiOperation(value = "关闭服务")
    @RequestMapping(value = "rumStopcmd/{projectName}", method = RequestMethod.POST)
    public ResponseDTO rumStopcmd(@PathVariable String projectName) {
        try {
            logger.debug(projectName);
            String results = instructService.runStopCmd(projectName);
            return ResponseDTO.createBySuccess(results);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }

    /**
     * 同步代码
     *
     * @param projectName
     * @return
     */
    @ApiOperation(value = "同步代码")
    @RequestMapping(value = "rumSynchrocmd/{projectName}", method = RequestMethod.POST)
    public ResponseDTO rumSynchrocmd(@PathVariable String projectName) {
        try {
            logger.debug(projectName);
            String results = instructService.rumSynchrocmd(projectName);
            return ResponseDTO.createBySuccess(results);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }


    /**
     * 清除PID
     *
     * @param projectName
     * @return
     */
    @ApiOperation(value = "清除PID")
    @RequestMapping(value = "rumPscmd/{projectName}", method = RequestMethod.POST)
    public ResponseDTO rumPscmd(@PathVariable String projectName) {
        try {
            logger.debug(projectName);
            String results = instructService.rumPscmd(projectName);
            return ResponseDTO.createBySuccess(results);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }

}
