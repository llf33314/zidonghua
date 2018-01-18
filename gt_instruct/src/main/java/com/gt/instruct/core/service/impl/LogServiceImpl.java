package com.gt.instruct.core.service.impl;

import com.gt.instruct.common.exception.SystemException;
import com.gt.instruct.core.constant.ServerConstant;
import com.gt.instruct.core.entity.Server;
import com.gt.instruct.core.service.LogService;
import com.gt.instruct.core.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileFilter;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * @author linweicong
 * @version 2018-01-17 10:17:57
 */
@Service
public class LogServiceImpl implements LogService {
    private static int DEFAULT_LINE_COUNT = 30;
    private static String DEFAULT_LOG_FILE = "catalina.out";

    @Autowired
    private ServerService serverService;

    @Override
    public List<String> listLogFileName(String projectName) throws Exception {
        if (projectName == null) {
            throw new SystemException("参数错误");
        }

        Server server = serverService.getByProjectNameAndServerStatus(projectName, ServerConstant.SERVER_STATUS_OPEN);
        if (server == null) {
            throw new SystemException("找不到服务信息");
        }

        String logDirectoryPath = server.getLogDirectory();
        if (logDirectoryPath == null) {
            throw new SystemException("没有设置服务日志位置");
        }

        File directoryFile = new File(logDirectoryPath);
        if (!directoryFile.exists()) {
            throw new SystemException("找不到服务日志位置");
        }

        File[] files = directoryFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
        List<String> result = new ArrayList<>();
        if (files != null) {
            for (File tempFile : files) {
                result.add(tempFile.getPath());
            }
        }

        // 倒序
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        return result;
    }

    @Override
    public Map<String, Object> getLogFileContent(String projectName, Long optPosition) throws Exception {
        if (projectName == null) {
            throw new SystemException("参数错误");
        }

        Server server = serverService.getByProjectNameAndServerStatus(projectName, ServerConstant.SERVER_STATUS_OPEN);
        if (server == null) {
            throw new SystemException("找不到服务信息");
        }

        String logDirectoryPath = server.getLogDirectory();
        if (logDirectoryPath == null) {
            throw new SystemException("没有设置服务日志位置");
        }

        String logFilePath = logDirectoryPath + File.separator + DEFAULT_LOG_FILE;
        File logFile = new File(logFilePath);
        if (!logFile.exists()) {
            throw new SystemException("找不到日志文件");
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(logFile, "r");
        String logContent = getLogContent(randomAccessFile, optPosition);

        Map<String, Object> result = new HashMap<>(2);
        result.put("content", logContent);
        result.put("position", randomAccessFile.length());

        return result;
    }

    private String getLogContent(RandomAccessFile randomAccessFile, Long optPosition) throws Exception {
        optPosition = optPosition != null ? optPosition : 0L;
        randomAccessFile.seek(optPosition);

        List<String> lineContentList = new ArrayList<>();
        int lineCount = 0;
        String temp;
        while ((temp = randomAccessFile.readLine()) != null) {
            lineContentList.add(new String(temp.getBytes("ISO8859-1"), "UTF-8"));
            lineCount++;
        }

        StringBuilder sbResult = new StringBuilder();
        if (optPosition == 0L) {
            // 默认取倒数30行
            for (int lineIndex = lineCount > DEFAULT_LINE_COUNT ? lineCount - DEFAULT_LINE_COUNT : 0; lineIndex < lineCount; lineIndex++) {
                sbResult.append(lineContentList.get(lineIndex)).append("\n");
            }
        } else {
            for (String lineContent : lineContentList) {
                sbResult.append(lineContent).append("\n");
            }
        }

        return sbResult.toString();
    }

    @Override
    public List<String> listLogFileContent(String projectName, String logFileName) throws Exception {
        if (projectName == null || logFileName == null) {
            throw new SystemException("参数错误");
        }

        Server server = serverService.getByProjectNameAndServerStatus(projectName, ServerConstant.SERVER_STATUS_OPEN);
        if (server == null) {
            throw new SystemException("找不到服务信息");
        }

        String logDirectoryPath = server.getLogDirectory();
        if (logDirectoryPath == null) {
            throw new SystemException("没有设置服务日志位置");
        }
        if (!logFileName.contains(logDirectoryPath)) {
            throw new SystemException("日志不在可查看的范围内");
        }

        File logFile = new File(logFileName);
        if (!logFile.exists()) {
            throw new SystemException("找不到日志文件");
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(logFile, "r");
        List<String> result = new ArrayList<>();
        String temp;
        while ((temp = randomAccessFile.readLine()) != null) {
            result.add(new String(temp.getBytes("ISO8859-1"), "UTF-8") + "\r\n");
        }

        return result;
    }
}
