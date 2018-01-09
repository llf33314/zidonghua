package com.gt.instruct.common.filter;

import com.gt.insos.common.axis.user.bean.dto.UserDTO;
import com.gt.instruct.common.util.CommonUtil;
import com.gt.instruct.core.service.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 项目过滤器
 * Created by psr on 2017/9/13 0013.
 */
public class AppFilter implements Filter {

    private static Logger logger = Logger.getLogger(AppFilter.class);

    @Value("${instruct.login.url}")
    private String loginUrl;

    @Autowired
    TokenService tokenService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Accept, Origin, XRequestedWith, Content-Type, LastModified, token, Cookie");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json; charset=utf-8");

        logger.debug("app filter");

        String token = (String) httpServletRequest.getSession().getAttribute("token");
        if (token == null) {
            httpServletResponse.sendRedirect(loginUrl);
            return;
        } else {
            //不为空
            UserDTO userDTO = tokenService.getUserByToken(token);
            if (CommonUtil.isEmpty(userDTO)) {
                httpServletResponse.sendRedirect(loginUrl);
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }

}
