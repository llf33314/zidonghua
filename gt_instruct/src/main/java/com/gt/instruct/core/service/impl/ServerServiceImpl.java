package com.gt.instruct.core.service.impl;

import com.gt.instruct.core.entity.Server;
import com.gt.instruct.core.dao.ServerDAO;
import com.gt.instruct.core.service.ServerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
	
}
