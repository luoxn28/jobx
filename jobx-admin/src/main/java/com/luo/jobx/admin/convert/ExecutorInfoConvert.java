package com.luo.jobx.admin.convert;

import com.luo.jobx.admin.entity.ExecutorInfoEntity;
import com.luo.jobx.core.bean.RegisterParam;

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

}
