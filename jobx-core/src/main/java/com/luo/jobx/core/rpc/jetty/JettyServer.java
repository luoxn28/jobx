package com.luo.jobx.core.rpc.jetty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.springframework.stereotype.Component;

/**
 * Jetty服务器
 *
 * @author xiangnan
 */
@Component
public class JettyServer {

    private final static Logger logger = LogManager.getLogger();

    private Server jettyServer;
    private Thread startThread;

    public Thread start(String ip, int port) {
        startThread = new Thread(() -> {
            jettyServer = new Server(new ExecutorThreadPool());
            ServerConnector connector = new ServerConnector(jettyServer);
            connector.setHost(ip);
            connector.setPort(port);
            jettyServer.setConnectors(new Connector[]{ connector });

            // jetty handler
            HandlerCollection handlers =new HandlerCollection();
            handlers.setHandlers(new Handler[] {new JettyServerHandler()});
            jettyServer.setHandler(handlers);

            try {
                jettyServer.start();

                logger.info("jobx executor start success...");
                jettyServer.join();
            } catch (Exception e) {
                logger.error("Jetty服务器启动失败: " + e);
            }
        });

        startThread.start();

        // 调用者可根据thread来进行join操作
        return startThread;
    }

    public void join() throws InterruptedException {
        startThread.join();
    }

    public void destroy() {

        if (jettyServer != null) {
            try {
                jettyServer.stop();
                jettyServer.destroy();
            } catch (Exception e) {
                logger.error("Jetty服务器停止失败: " + e);
            }
        }

        if (startThread.isAlive()) {
            startThread.interrupt();
        }
    }

}
