package com.luo.jobx.admin.controller;

import com.luo.jobx.admin.service.ExecutorInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 执行器Controller
 *
 * @author xiangnan
 */
@RestController
@RequestMapping("/executor")
public class ExecutorInfoController {

    @Resource
    private ExecutorInfoService executorService;

    @GetMapping("/list")
    public Object getExecutorList() {
        return executorService.getExecutorList();
    }

}
