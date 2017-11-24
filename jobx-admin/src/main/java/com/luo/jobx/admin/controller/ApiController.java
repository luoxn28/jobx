package com.luo.jobx.admin.controller;

import com.luo.jobx.core.bean.RegisterBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 与执行器通信Controller
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/register")
    public Object register(RegisterBean bean) {
        System.out.println(bean);
        return bean;
    }

}
