package com.gt.instruct.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author psr
 *         Created by psr on 2018/1/9 0009.
 */
@Controller
@RequestMapping(value = "token")
public class TokenController {

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public String loginToken(@PathVariable String token, HttpServletRequest request) {
        if (token != null) {
            request.getSession().setAttribute("token", token);
        }
        return "redirect:/app/route/index";
    }

}
