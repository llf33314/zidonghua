package com.gt.instruct.core.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 多粉服务
 * </p>
 *
 * @author psr
 * @since 2018-01-09
 */
@Data
@Accessors(chain = true)
@TableName("t_server")
public class Server extends Model<Server> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 服务环境
     */
    private String serverEnv;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目所属数据库
     */
    private String projectDb;
    /**
     * 服务监控检测地址
     */
    private String serverHealthUrl;
    /**
     * 服务状态（0：正常，1：停用）
     */
    private Integer serverStatus;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 日志所在文件夹位置
     */
    private String logDirectory;

    /**
     * 日志文件编码
     */
    private String logCharset;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerEnv() {
        return serverEnv;
    }

    public void setServerEnv(String serverEnv) {
        this.serverEnv = serverEnv;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDb() {
        return projectDb;
    }

    public void setProjectDb(String projectDb) {
        this.projectDb = projectDb;
    }

    public String getServerHealthUrl() {
        return serverHealthUrl;
    }

    public void setServerHealthUrl(String serverHealthUrl) {
        this.serverHealthUrl = serverHealthUrl;
    }

    public Integer getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(Integer serverStatus) {
        this.serverStatus = serverStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLogDirectory() {
        return logDirectory;
    }

    public void setLogDirectory(String logDirectory) {
        this.logDirectory = logDirectory;
    }

    public String getLogCharset() {
        return logCharset;
    }

    public void setLogCharset(String logCharset) {
        this.logCharset = logCharset;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
