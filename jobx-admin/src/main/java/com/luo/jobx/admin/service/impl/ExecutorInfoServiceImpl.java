package com.luo.jobx.admin.service.impl;

import com.luo.jobx.admin.bean.ExecutorInfoBean;
import com.luo.jobx.admin.component.ExecutorStatusCheckTask;
import com.luo.jobx.admin.convert.ExecutorInfoConvert;
import com.luo.jobx.admin.dao.ExecutorInfoDao;
import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import com.luo.jobx.admin.service.ExecutorInfoService;
import com.luo.jobx.core.bean.ExecutorInfo;
import com.luo.jobx.core.rpc.RpcClientProxy;
import com.luo.jobx.core.rpc.service.ExecutorService;
import com.luo.jobx.core.util.R;
import com.xiaoleilu.hutool.util.StrUtil;
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

    @Override
    public List<ExecutorInfoBean> getExecutorList() {
        return ExecutorInfoConvert.toBeanList(executorDao.selectListOnline());
    }

    @Override
    public int updateExecutorInfo(ExecutorInfoBean bean) {
        ExecutorInfoEntity entity = ExecutorInfoConvert.toEntity(bean);
        int result = executorDao.updateByExecutorId(entity);
        if (result == 1) {
            logger.info("更新执行器成功: executorId=" + entity.getExecutorId()
                    + ", ip=" + entity.getIp()
                    + ", port=" + entity.getPort());

            // 在线通知执行器更改
            if (onlineUpdateExecutorInfo(executorDao.selectByExecutorId(entity.getExecutorId()))) {
                logger.info("在线更新执行器成功: executorId=" + entity.getExecutorId());
            } else {
                logger.info("在线更新执行器失败: executorId=" + entity.getExecutorId());
            }
        } else {
            logger.info("更新执行器任务失败，未找到任务 executorId=" + entity.getExecutorId());
        }

        return 1;
    }

    @Override
    public int deleteExecutorInfo(String executorId) {
        if (1 == executorDao.updateStatusByExecutorId(R.executorStatus.DELETED, executorId)) {
            logger.info("删除执行器成功: executorId=" + executorId);
        } else {
            logger.info("删除执行器失败: executorId=" + executorId);
        }
        return 1;
    }

    private boolean onlineUpdateExecutorInfo(ExecutorInfoEntity entity) {

        if (!StrUtil.equals(entity.getStatus(), R.executorStatus.ONLINE)) {
            return false;
        }

        ExecutorInfo executorInfo = new ExecutorInfo();
        executorInfo.setIp(entity.getIp());
        executorInfo.setPort(entity.getPort());
        executorInfo.setToken(entity.getToken());
        executorInfo.setName(entity.getName());
        executorInfo.setKeepAliveTime(entity.getKeepAliveTime());

        try {
            String toUrl = "http://" + entity.getIp() + ":" + entity.getPort();
            ExecutorService executorService = (ExecutorService)
                    new RpcClientProxy(ExecutorService.class, toUrl).getObject();

            return executorService.updateExecutorInfo(executorInfo);
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }

    }

}
