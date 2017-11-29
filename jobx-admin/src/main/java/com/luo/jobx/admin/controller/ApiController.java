package com.luo.jobx.admin.controller;

import com.luo.jobx.admin.service.ExecutorInfoService;
import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.RegisterResult;
import com.luo.jobx.core.bean.ReturnX;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 与执行器通信Controller
 *
 * @author xiangnan
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private ExecutorInfoService executorService;

    @PostMapping("/register")
    public ReturnX<RegisterResult> register(@RequestBody RegisterParam param) {
        return executorService.register(param);
    }

}
