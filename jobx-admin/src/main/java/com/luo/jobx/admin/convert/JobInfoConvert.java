package com.luo.jobx.admin.convert;

import com.luo.jobx.admin.bean.JobInfoBean;
import com.luo.jobx.admin.entity.JobInfoEntity;
import com.xiaoleilu.hutool.date.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务信息转换类
 *
 * @author xiangnan
 */
public class JobInfoConvert extends BaseConvert {

    public static JobInfoBean toBean(JobInfoEntity entity) {
        JobInfoBean bean = null;

        if (entity != null) {
            bean = mapper.map(entity, JobInfoBean.class);

            bean.setCreateTime(DateUtil.format(entity.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            bean.setUpdateTime(DateUtil.format(entity.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
        }

        return bean;
    }

    public static List<JobInfoBean> toBeanList(List<JobInfoEntity> entityList) {
        List<JobInfoBean> beanList = new ArrayList<>();

        if (entityList != null) {
            entityList.forEach(entity -> beanList.add(toBean(entity)));
        }

        return beanList;
    }

}
