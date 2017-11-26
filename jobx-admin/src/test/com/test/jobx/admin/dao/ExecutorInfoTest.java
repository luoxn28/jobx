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
    }

    @Test
    public void testInsert() {
        executorInfoDao.insert(entity);
    }

    @Test
    public void testSelect() {
        System.out.println(executorInfoDao.selectByExecutorId(entity.getExecutorId()));
        System.out.println(executorInfoDao.selectByIpPort(entity.getIp(), entity.getPort()));
    }

}
