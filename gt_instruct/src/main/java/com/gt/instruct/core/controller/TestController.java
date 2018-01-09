package com.gt.instruct.core.controller;

import com.gt.instruct.common.dto.ResponseDTO;
import com.gt.instruct.core.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author psr
 *         Created by psr on 2018/1/9 0009.
 */
@Api(value = "/test", description = "自动化部署Controller")
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    TokenService tokenService;

    @ApiOperation(value = "测试")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseDTO test() {
        tokenService.getUserByToken("");
        return ResponseDTO.createBySuccess();
    }

}
