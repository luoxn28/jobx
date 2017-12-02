package com.luo.jobx.admin.controller;

import com.luo.jobx.admin.bean.ExecutorInfoBean;
import com.luo.jobx.admin.service.ExecutorInfoService;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public Object updateExecutorInfo(@RequestBody ExecutorInfoBean bean) {
        return executorService.updateExecutorInfo(bean);
    }

    @DeleteMapping("/{executorId}")
    public Object deleteExecutorInfo(@PathVariable String executorId) {
        if (StrUtil.isBlank(executorId)) {
            return 0;
        }

        return executorService.deleteExecutorInfo(executorId);
    }

}
