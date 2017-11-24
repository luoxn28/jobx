# 0 jobx
轻量级分布式调度系统。

项目前后端分离，前端基于vue，后端基于spring boot，并使用quartz作为任务调度器。


# 1 jobx模块

## 1.1 jobx-admin（jobx调度中心）

## 1.2 jobx-executor（jobx执行器）

jobx-executor启动：[nohup] java -jar jobx-executor-x.x.x.jar register_url jobx-executor.properties [port &]

## 1.3 jobx-core（jobx依赖的jar包）

# 参考资料
1. [Quartz应用与集群原理分析](https://tech.meituan.com/mt-crm-quartz.html)