package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.bean.ExecutorInfoBean;
import com.luo.jobx.admin.convert.ExecutorInfoConvert;
import com.luo.jobx.admin.dao.ExecutorInfoDao;
import com.luo.jobx.admin.service.ExecutorInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExecutorInfoServiceImpl implements ExecutorInfoService {

    private final static Logger logger = LogManager.getLogger(ExecutorInfoServiceImpl.class);

    @Resource
    private ExecutorInfoDao executorDao;

    public List<ExecutorInfoBean> getExecutorList() {
        return ExecutorInfoConvert.toBeanList(executorDao.selectList());
    }

}
