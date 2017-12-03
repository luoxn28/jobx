package com.luo.jobx.admin.dao;

import com.luo.jobx.admin.entity.JobInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ExecutorInfo操作类
 *
 * @author xiangnan
 */
@Mapper
public interface JobInfoDao {

    List<JobInfoEntity> selectList(@Param("jobType") String jobType);

    int insert(JobInfoEntity entity);

}
