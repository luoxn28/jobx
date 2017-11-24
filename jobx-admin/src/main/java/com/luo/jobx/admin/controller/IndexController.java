package com.luo.jobx.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页Controller
 *
 * @author xiangnan
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "jobx-admin";
    }

}
