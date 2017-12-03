package com.test.jobx.admin.job;

import com.luo.jobx.admin.component.JobxScheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {com.luo.jobx.admin.AdminApplication.class})
public class JobTest {

    @Resource(name = "internalScheduler")
    private Scheduler scheduler;

    @Test
    public void infoScheduler() {
        System.out.println(scheduler);
    }

    @Resource
    private JobxScheduler jobxScheduler;

    @Test
    public void testJob() {

        try {
            jobxScheduler.addJob("jobId", "groupId", "0/5 * * * * ?");

            Thread.sleep(20000);

            jobxScheduler.deleteJob("jobId", "groupId");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
