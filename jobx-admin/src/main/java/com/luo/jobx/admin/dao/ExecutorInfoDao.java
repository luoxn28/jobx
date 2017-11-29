package com.luo.jobx.admin.dao;

import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ExecutorInfo操作类
 *
 * @author xiangnan
 */
@Mapper
public interface ExecutorInfoDao {

    ExecutorInfoEntity selectByExecutorId(@Param("executorId") String executorId);

    ExecutorInfoEntity selectByIpPort(@Param("ip") String ip, @Param("port") int port);

    boolean insert(ExecutorInfoEntity entity);

    boolean update(ExecutorInfoEntity entity);

}