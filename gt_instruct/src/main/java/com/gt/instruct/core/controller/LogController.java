package com.gt.instruct.core.controller;

import com.gt.instruct.common.dto.ResponseDTO;
import com.gt.instruct.common.exception.SystemException;
import com.gt.instruct.common.util.OsUtil;
import com.gt.instruct.core.service.LogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author linweicong
 * @version 2018-01-17 09:52:42
 */
@RestController
@Api(value = "/app/log", description = "日志查看Controller")
@RequestMapping("/app/log")
public class LogController {
    private static final Logger logger = Logger.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @ApiOperation(value = "获取日志文件名列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseDTO listLogFileName(
            @ApiParam(value = "项目名称", name = "projectName", required = true)
            @RequestParam(value = "projectName") String projectName) {
        try {
            logger.debug("获取日志文件名列表(" + projectName + ")");
            List<String> result = logService.listLogFileName(projectName);
            return ResponseDTO.createBySuccess(result);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }

    @ApiOperation(value = "获取日志文件内容")
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public ResponseDTO getLogFileContent(
            @ApiParam(value = "项目名称", name = "projectName", required = true)
            @RequestParam(value = "projectName") String projectName,
            @ApiParam(value = "日志内容开始位置", name = "position")
            @RequestParam(value = "position", required = false) Long optPosition) {
        try {
            logger.debug("获取日志文件内容(" + projectName + ")");
            Map<String, Object> result = logService.getLogFileContent(projectName, optPosition);
            return ResponseDTO.createBySuccess(result);
        } catch (SystemException e) {
            logger.error(e.getMessage(), e.fillInStackTrace());
            return ResponseDTO.createByErrorCodeMessage(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.createByError();
        }
    }

    @ApiOperation(value = "下载日志文件")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadLogFile(
            HttpServletResponse response,
            @ApiParam(value = "项目名称", name = "projectName", required = true)
            @RequestParam(value = "projectName") String projectName,
            @ApiParam(value = "日志文件名", name = "logFileName", required = true)
            @RequestParam(value = "logFileName") String logFileName) {
        OutputStream outputStream = null;
        try {
            logger.debug("下载日志文件(" + projectName + ")");
            List<String> logFileContentList = logService.listLogFileContent(projectName, logFileName);
            String[] filePaths = logFileName.split(OsUtil.isLinux() ? "/" : "\\\\");
            String downLoadFileName = projectName + "_" + filePaths[filePaths.length - 1];

            response.reset();
            outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(downLoadFileName, "UTF-8") + "\"");
            response.setContentType("text/plain");
            for (String logFileContent : logFileContentList) {
                outputStream.write(logFileContent.getBytes());
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

