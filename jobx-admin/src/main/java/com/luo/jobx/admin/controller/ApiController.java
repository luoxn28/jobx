package com.luo.jobx.admin.controller;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.ReturnX;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 与执行器通信Controller
 *
 * @author xiangnan
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/register")
    public Object register(@RequestBody RegisterParam bean) {
        System.out.println(bean);
        return ReturnX.SUCCESS;
    }

}
