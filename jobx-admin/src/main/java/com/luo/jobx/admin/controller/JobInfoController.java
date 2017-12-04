package com.luo.jobx.admin.controller;

import com.luo.jobx.admin.service.JobInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 任务信息Controller
 *
 * @author xiangnan
 */
@RestController
@RequestMapping("/job")
public class JobInfoController {

    @Resource
    private JobInfoService jobService;

    @GetMapping("/list")
    public Object getJobList() {
        return jobService.getJobList();
    }

    @GetMapping("/type")
    public Object getJobType() {
        return jobService.getJobType();
    }

}
