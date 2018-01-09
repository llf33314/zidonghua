package com.gt.instruct.core.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

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
	@TableId(value="id", type= IdType.AUTO)
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
