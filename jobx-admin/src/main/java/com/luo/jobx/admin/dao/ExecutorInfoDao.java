package com.luo.jobx.admin.dao;

import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * ExecutorInfo操作类
 *
 * @author xiangnan
 */
@Mapper
public interface ExecutorInfoDao {

    ExecutorInfoEntity selectByExecutorId(@Param("executorId") String executorId);

    ExecutorInfoEntity selectByIpPort(@Param("ip") String ip, @Param("port") int port);

    List<ExecutorInfoEntity> selectList();

    List<ExecutorInfoEntity> selectListOnline();

    int insert(ExecutorInfoEntity entity);

    int updateByExecutorId(ExecutorInfoEntity entity);

    int updateByIpPort(ExecutorInfoEntity entity);

    int updateForceByIpPort(ExecutorInfoEntity entity);

    int updateTimeByIpPort(@Param("updateTime") Date updateTime, @Param("status") String status,
                           @Param("ip") String ip, @Param("port") int port);

    int updateStatusByExecutorId(@Param("status") String status, @Param("executorId") String executorId);

}
