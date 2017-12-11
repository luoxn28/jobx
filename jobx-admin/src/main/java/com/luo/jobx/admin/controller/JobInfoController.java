package com.luo.jobx.admin.controller;

import com.luo.jobx.admin.bean.JobInfoBean;
import com.luo.jobx.admin.service.JobInfoService;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Object postJob(@RequestBody JobInfoBean jobBean) {
        return jobService.addJob(jobBean);
    }

    @PutMapping("/trigger/{jobId}")
    public Object triggerJob(@PathVariable String jobId) {
        if (StrUtil.isBlank(jobId)) {
            return null;
        }

        return jobService.triggerJob(jobId);
    }

    @PutMapping("/pause/{jobId}")
    public Object pauseJob(@PathVariable String jobId) {
        if (StrUtil.isBlank(jobId)) {
            return null;
        }

        return jobService.pauseJob(jobId);
    }

    @PutMapping("/resume/{jobId}")
    public Object resumeJob(@PathVariable String jobId) {
        if (StrUtil.isBlank(jobId)) {
            return null;
        }

        return jobService.resumeJob(jobId);
    }

    @DeleteMapping("/{jobId}")
    public Object deleteJob(@PathVariable String jobId) {
        if (StrUtil.isBlank(jobId)) {
            return null;
        }

        return jobService.deleteJob(jobId);
    }

}
