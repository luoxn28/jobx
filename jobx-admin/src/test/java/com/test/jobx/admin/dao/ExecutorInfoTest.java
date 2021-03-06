package com.test.jobx.admin.dao;

import com.luo.jobx.admin.dao.ExecutorInfoDao;
import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {com.luo.jobx.admin.AdminApplication.class})
public class ExecutorInfoTest {

    @Autowired
    ExecutorInfoDao executorInfoDao;

    private ExecutorInfoEntity entity =  new ExecutorInfoEntity();
    {
        entity.setExecutorId("aaa");
        entity.setIp("127.0.0.1111");
        entity.setPort(9999);
        entity.setName("executor-demo");
    }

    @Test
    public void testInsert() {
        executorInfoDao.insert(entity);
    }

    @Test
    public void testSelect() {
        System.out.println(executorInfoDao.selectByExecutorId(entity.getExecutorId()));

        ExecutorInfoEntity infoEntity = executorInfoDao.selectByExecutorId(entity.getExecutorId());
        infoEntity.setToken("111");
        infoEntity.setName("111");
        executorInfoDao.updateByExecutorId(infoEntity);

        System.out.println(executorInfoDao.selectByIpPort(entity.getIp(), entity.getPort()));
    }

}
