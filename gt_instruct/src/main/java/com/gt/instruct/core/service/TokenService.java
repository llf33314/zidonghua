package com.gt.instruct.core.service;

import com.gt.insos.common.axis.user.bean.dto.UserDTO;

/**
 * @author psr
 *         Created by psr on 2018/1/9 0009.
 */
public interface TokenService {

    UserDTO getUserByToken(String token);

}
