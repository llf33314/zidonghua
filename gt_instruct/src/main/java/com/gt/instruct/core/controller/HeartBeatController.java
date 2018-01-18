package com.gt.instruct.core.controller;

import com.gt.instruct.common.dto.ResponseDTO;
import com.gt.instruct.common.exception.SystemException;
import com.gt.instruct.core.service.HeartBeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linweicong
 * @version 2018-01-18 10:26:39
 */
@RestController
@Api(value = "/app/heartBeat", description = "心跳Controller")
@RequestMapping("/app/heartBeat")
public class HeartBeatController {
    private static final Logger logger = Logger.getLogger(HeartBeatController.class);

    @Autowired
    private HeartBeatService heartBeatService;

    @ApiOperation(value = "心跳机制")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseDTO beat(
            @ApiParam(value = "项目名称", name = "projectName", required = true)
            @RequestParam(value = "projectName") String projectName) {
        try {
            logger.debug("执行心跳机制(" + projectName + ")");
            int result = heartBeatService.beat(projectName);
            return ResponseDTO.createBySuccess(result);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }

}
