package by.radzionau.imdb.pool;

import by.radzionau.imdb.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger(CustomConnectionPool.class);
    private static CustomConnectionPool instance;
    private static final int DEFAULT_POOL_SIZE = 30;
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private final BlockingDeque<ProxyConnection> freeConnections;
    private final BlockingDeque<ProxyConnection> busyConnections;

    private CustomConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        busyConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);

        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            ProxyConnection connection;
            try {
                connection = ConnectionFactory.createConnection();
                freeConnections.offer(connection);
            } catch (SQLException e) {
                logger.error("Connection can't be created.");
            }
        }
        if (freeConnections.isEmpty()) {
            logger.fatal("Connections pool can't be created. Database access error");
            throw new RuntimeException("Connections pool can't be created. Database access error");
        }
        //todo добавить восполнение упущенных коннекшенов?
    }

    public static CustomConnectionPool getInstance() {
        while (instance == null) {
            if (isInitialized.compareAndSet(false, true)) {
                instance = new CustomConnectionPool();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() throws ConnectionPoolException {
        try {
            ProxyConnection connection = freeConnections.take();
            busyConnections.put(connection);
            return connection;
        } catch (InterruptedException e) {
            logger.error("Cannot take or put connection: {}", e.getMessage());
            Thread.currentThread().interrupt();
        }
        throw new ConnectionPoolException("Time out for getting connection");
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            try {
                if (busyConnections.remove(proxyConnection)) {
                    freeConnections.put(proxyConnection);
                } else {
                    logger.error("Can't put connection in pool connection because connection isn't valid. {}", proxyConnection);
                }
            } catch (InterruptedException e) {
                logger.error("Cannot put connection {}", proxyConnection);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.error("Wild connection: {}", connection);
        }
    }
}
