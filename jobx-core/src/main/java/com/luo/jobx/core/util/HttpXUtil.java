package com.luo.jobx.core.util;

import com.luo.jobx.core.bean.RegisterParam;
import com.luo.jobx.core.bean.ReturnX;
import com.xiaoleilu.hutool.http.HttpRequest;
import com.xiaoleilu.hutool.http.HttpResponse;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

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
            } else {
                return new ReturnX<>(R.status.FAIL, "注册时返回结果不是json格式数据");
            }
        } catch (Exception e) {
            logger.error("执行器注册失败: " + e);
            return new ReturnX<>(R.status.FAIL, e.toString());
        }
    }

    /**
     * RPC通信
     */
    public static byte[] postRpc(String url, byte[] param) {
        return HttpRequest.post(url)
                .body(param)
                .contentType("application/json")
                .timeout(10000)
                .execute()
                .bodyBytes();
    }

    public static byte[] readByte(HttpServletRequest request) throws IOException {

        request.setCharacterEncoding("UTF-8");
        int contentLen = request.getContentLength();
        InputStream in = request.getInputStream();

        if (contentLen > 0) {
            int readLenThisTime;
            int readLen = 0;
            byte[] data = new byte[contentLen];

            while (readLen != contentLen) {
                readLenThisTime = in.read(data, readLen, contentLen - readLen);
                if (readLenThisTime < 0) {
                    return new byte[] {};
                }
                readLen += readLenThisTime;
            }

            return data;
        }

        return new byte[] {};
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
