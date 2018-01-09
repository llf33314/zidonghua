package com.gt.instruct.core.service.impl;

import com.gt.insos.common.axis.user.bean.dto.UserDTO;
import com.gt.instruct.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by psr on 2018/1/9 0009.
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${instruct.sso.url}")
    private String ssoUrl;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDTO getUserByToken(String token) {
        try {
            String url = ssoUrl + "token/getUserByToken";
            HttpEntity<String> formEntity = new HttpEntity<>(token, null);
            Map map = restTemplate.postForObject(url, formEntity, Map.class);
            String code = String.valueOf(map.get("code"));
            if (!"100".equals(code)) {
                return null;
            }
            Map<String, Object> dataMap = (Map<String, Object>) map.get("data");
            UserDTO userDTO = new UserDTO();
            Object userId = dataMap.get("userId");
            Object userName = dataMap.get("userName");
            Object roleStatus = dataMap.get("roleStatus");
            userDTO.setUserId(Integer.valueOf(userId.toString()));
            userDTO.setUserName(String.valueOf(userName.toString()));
            userDTO.setRoleStatus(Integer.valueOf(roleStatus.toString()));
            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
