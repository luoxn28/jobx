package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.convert.ExecutorInfoConvert;
import com.luo.jobx.admin.dao.ExecutorInfoDao;
import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import com.luo.jobx.admin.service.ExecutorInfoService;
import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.RegisterResult;
import com.luo.jobx.core.bean.ReturnX;
import com.luo.jobx.core.util.BusinessIDGenerator;
import com.luo.jobx.core.util.JacksonUtil;
import com.luo.jobx.core.util.R;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExecutorInfoServiceImpl implements ExecutorInfoService {

    private final static Logger logger = LogManager.getLogger(ExecutorInfoServiceImpl.class);

    @Resource
    private ExecutorInfoDao executorDao;

    @Override
    public ReturnX<String> register(RegisterParam param) {

        ExecutorInfoEntity entity = ExecutorInfoConvert.toEntity(param);
        entity.setStatus(R.executorStatus.ONLINE);

        if (executorDao.updateForceByIpPort(entity) != 1) {
            entity.setExecutorId(BusinessIDGenerator.getId());
            executorDao.insert(entity);
        }

        logger.info("执行器注册: " + param.getIp() + ":" + param.getPort());
        return new ReturnX<>(R.status.SUCCESS,
                JacksonUtil.toJson(new RegisterResult("api/keep/alive")));
    }

    @Override
    public ReturnX<String> keepAlive(String ip, int port) {
        executorDao.updateTimeByIpPort(ip, port);
        return ReturnX.SUCCESS;
    }

}
