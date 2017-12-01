package com.luo.jobx.admin.controller;

import com.luo.jobx.admin.service.ApiService;
import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.ReturnX;
import org.springframework.web.bind.annotation.*;

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
    private ApiService apiService;

    @PostMapping("/register")
    public ReturnX<String> register(@RequestBody RegisterParam param) {
        return apiService.register(param);
    }

    @PostMapping("/keep/alive/{ip}/{port}")
    public ReturnX<String> keepAlive(@PathVariable String ip, @PathVariable int port) {
        return apiService.keepAlive(ip, port);
    }

}
