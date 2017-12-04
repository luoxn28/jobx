package com.luo.jobx.admin.component;

import com.luo.jobx.admin.dao.ExecutorInfoDao;
import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import com.luo.jobx.core.util.R;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class ExecutorStatusCheckTask {

    private final static Logger logger = LogManager.getLogger(ExecutorStatusCheckTask.class);

    // 线程检测周期
    private static final int CHECK_PERIOD = 10000;

    // 执行器状态检查线程
    private Thread thread;
    private volatile boolean running = true;

    @Resource
    private ExecutorInfoDao executorDao;

    public ExecutorStatusCheckTask(ExecutorInfoDao executorDao) {
        this.executorDao = executorDao;
    }

    public void startCheck() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {

                    // 从数据读取在线执行器列表
                    List<ExecutorInfoEntity> entityList = executorDao.selectListOnline();

                    long currentTime = new Date().getTime();
                    entityList.forEach(entity -> {
                        long periodTime = 3 * (entity.getKeepAliveTime() > 0 ?
                                (1000 * entity.getKeepAliveTime()) : CHECK_PERIOD);
                        long updateTime = entity.getUpdateTime().getTime();
                        if ((currentTime > updateTime) &&
                                ((currentTime - updateTime) > periodTime)) {
                            // 将执行器设置为下线状态
                            if (1 == executorDao.updateStatusByExecutorId(
                                    R.executorStatus.OFFLINE, entity.getExecutorId())) {
                                logger.info("将执行器设置为下线，executorId=" + entity.getExecutorId());
                            } else {
                                logger.info("将执行器设置为下线失败，executor=" + entity.getIp() + ":" +
                                    entity.getPort());
                            }
                        }
                    });

                    try {
                        Thread.sleep(CHECK_PERIOD);
                    } catch (InterruptedException e) {
                        if (running) {
                            logger.warn("执行器状态检查线程异常退出: " + e);
                        }
                    }
                }
            }
        });
        thread.start();
    }

    public void stopCheck() {
        if (thread.isAlive()) {
            running = false;
            thread.interrupt();
        }
    }

}
