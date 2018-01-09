package com.gt.instruct.core.service.impl;

import com.gt.instruct.common.util.CmdUtils;
import com.gt.instruct.core.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public String runCmd(String cmd) throws Exception {
        String result = CmdUtils.exec(cmd);
        return result;
    }

}
