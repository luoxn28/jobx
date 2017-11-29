package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.dao.ExecutorInfoDao;
import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import com.luo.jobx.admin.service.ExecutorInfoService;
import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.RegisterResult;
import com.luo.jobx.core.bean.ReturnX;
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
    public ReturnX<RegisterResult> register(RegisterParam param) {

        ExecutorInfoEntity oldEntity = executorDao.selectByIpPort(param.getIp(), param.getPort());
        if (oldEntity == null) {
            // 首次注册
            ExecutorInfoEntity newEntity = convertToEntity(param);
            newEntity.setStatus(R.executorStatus.ONLINE);
            newEntity.setExecutorId("1111");
            executorDao.insert(newEntity);
        } else {
            oldEntity.setToken(param.getToken());
            oldEntity.setName(param.getName());
            oldEntity.setStatus(R.executorStatus.ONLINE);
            executorDao.update(oldEntity);
        }

        logger.info("执行器注册: " + param.getIp() + ":" + param.getPort());
        return new ReturnX<>(R.status.SUCCESS, new RegisterResult());
    }

    private ExecutorInfoEntity convertToEntity(RegisterParam param) {
        ExecutorInfoEntity entity = new ExecutorInfoEntity();

        entity.setToken(param.getToken());
        entity.setIp(param.getIp());
        entity.setPort(param.getPort());
        entity.setName(param.getName());

        return entity;
    }

}
