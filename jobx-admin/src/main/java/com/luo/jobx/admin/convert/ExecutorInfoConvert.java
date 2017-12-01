package com.luo.jobx.admin.convert;

import com.luo.jobx.admin.bean.ExecutorInfoBean;
import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import com.luo.jobx.core.bean.RegisterParam;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行器信息转换类
 *
 * @author xiangnan
 */
public class ExecutorInfoConvert extends BaseConvert {

    public static ExecutorInfoEntity toEntity(RegisterParam param) {
        ExecutorInfoEntity entity = null;

        if (param != null) {
            entity = mapper.map(param, ExecutorInfoEntity.class);
        }

        return entity;
    }

    public static ExecutorInfoEntity toEntity(ExecutorInfoBean bean) {
        ExecutorInfoEntity entity = null;

        if (bean != null) {
            entity = mapper.map(bean, ExecutorInfoEntity.class);
        }

        return entity;
    }

    public static ExecutorInfoBean toBean(ExecutorInfoEntity entity) {
        ExecutorInfoBean bean = null;

        if (entity != null) {
            bean = mapper.map(entity, ExecutorInfoBean.class);
        }

        return bean;
    }

    public static List<ExecutorInfoBean> toBeanList(List<ExecutorInfoEntity> entityList) {
        List<ExecutorInfoBean> beanList = new ArrayList<>();

        if (entityList != null) {
            entityList.forEach(entity -> beanList.add(toBean(entity)));
        }

        return beanList;
    }

}
