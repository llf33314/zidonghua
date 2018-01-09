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
            HttpEntity<String> formEntity = new HttpEntity<>(token, null);
            Map map = restTemplate.postForObject(ssoUrl, formEntity, Map.class);
            if (!map.get("code").equals("100")) {
                return null;
            }
            UserDTO userDTO = (UserDTO) map.get("data");
            return userDTO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
