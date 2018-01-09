package com.gt.instruct.common.util;

import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 命令工具类
 * @author psr
 * @since 2017/7/7
 * @version v1.0
 */
public class CmdUtils {

    private static Logger logger = Logger.getLogger(CmdUtils.class);

    /**
     * single
     */
    private CmdUtils(){}

    /**
     * 执行cmd命令
     * @param cmd
     * @return
     */
    public static String exec(String cmd){
        try {
            logger.debug("run cmd --> " + cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                logger.warn("call shell failed. error code is :" + exitValue);
            }
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream(),"GBK"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line).append("\n");
            }
            return sb.toString();
        }catch (Exception e){
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * 执行cmd命令
     * @param cmd
     * @return
     */
    public static List<String> execList(String cmd){
        List<String> list = new ArrayList<>();
        try {
            logger.debug("run cmd --> " + cmd);
            Process process = Runtime.getRuntime().exec(cmd);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                logger.warn("call shell failed. error code is :" + exitValue);
            }
            LineNumberReader br = new LineNumberReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String line;
            while ((line = br.readLine()) != null){
                list.add(line);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            list.add(e.getMessage());
        }
        return list;
    }

}
