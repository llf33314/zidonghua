package com.gt.instruct.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by psr on 2018/1/9 0009.
 */
@Controller
@RequestMapping("/app/route")
public class RouteController {

    @RequestMapping("/index")
    public String toIndex(){
        return "instruct";
    }

}
