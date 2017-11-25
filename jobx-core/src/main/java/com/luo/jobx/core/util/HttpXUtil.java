package com.luo.jobx.core.util;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.ReturnX;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * http辅助类
 *
 * @author xiangnan
 */
public class HttpXUtil {

    private final static Logger logger = LogManager.getLogger(HttpXUtil.class);

    /**
     * 执行器向调度中心注册
     */
    public static ReturnX register(String registerUrl, RegisterParam param) {
        try {
            HttpResponse response = postSend(registerUrl, param);
            if (response.getStatus() != 200) {
                logger.warn("执行器注册异常: " + registerUrl);
                return new ReturnX<>(R.status.FAIL, response.toString());
            }

            String body = response.body();
            if (StrUtil.isNotBlank(body) && StrUtil.startWith(body, "{")) {
                return JSONUtil.parseObj(body).toBean(ReturnX.class);
            }
        } catch (Exception e) {
            logger.error("执行器注册失败: " + e);
            throw e;
        }

        return ReturnX.FAIL;
    }

    /**
     * post发送数据
     *
     * @param url http 路径
     * @param param http body 数据
     * @return 不同业务返回值可能不同
     */
    private static HttpResponse postSend(String url, Object param) {
        return HttpRequest.post(url)
                .body(JSONUtil.toJsonStr(param))
                .contentType("application/json")
                .timeout(10000)
                .execute();
    }

}
