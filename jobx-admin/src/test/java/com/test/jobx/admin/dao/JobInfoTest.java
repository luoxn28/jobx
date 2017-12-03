package com.test.jobx.admin.dao;

import com.luo.jobx.admin.dao.JobInfoDao;
import com.luo.jobx.admin.entity.JobInfoEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {com.luo.jobx.admin.AdminApplication.class})
public class JobInfoTest {

    @Resource
    JobInfoDao jobInfoDao;

    @Test
    public void test() {

        JobInfoEntity entity = new JobInfoEntity();

        entity.setJobId("111");
        entity.setJobName("第一个任务");
        entity.setJobType("script");
        entity.setJobRole("单独任务");
        entity.setCron("111");

        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setStatus("create");

//        System.out.println(jobInfoDao.insert(entity));

        System.out.println(jobInfoDao.selectList(null));
        System.out.println(jobInfoDao.selectList("script"));
        System.out.println(jobInfoDao.selectList("AAA"));
    }

}
