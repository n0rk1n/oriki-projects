package cn.oriki.commons.manager;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPoolManager;

import java.util.concurrent.*;

/**
 * 提供多线程执行的线程管理器
 *
 * @author oriki.wang
 */
public class ThreadManager {

    /**
     * Executor
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * Singleton
     */
    private static ThreadManager threadManager;

    private ThreadManager() {
        // 内核数量：获取内核数 * 2
        int size = Runtime.getRuntime().availableProcessors() * 2;

        // 创建线程执行器
        this.threadPoolExecutor = new ThreadPoolExecutor(size, size, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), (ThreadFactory) Thread::new);
    }

    /**
     * 获取 ThreadManager 对象
     *
     * @return 单例对象
     */
    public static ThreadManager getInstance() {
        if (threadManager == null) {
            synchronized (ThreadPoolManager.class) {
                if (threadManager == null) {
                    threadManager = new ThreadManager();
                }
            }
        }
        return threadManager;
    }

    /**
     * 多线程执行任务
     *
     * @param futureTask FutureTask 对象
     * @param <V>        执行泛型
     */
    public <V> void submit(FutureTask<V> futureTask) {
        this.threadPoolExecutor.submit(futureTask);
    }

}
