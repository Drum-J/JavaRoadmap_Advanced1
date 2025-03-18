package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

// 사용자 정의 풀 전략
public class PoolSizeMainV4 {

    static final int TASK_SIZE = 55; // 1. 일반 1100
    //static final int TASK_SIZE = 60; // 2. 긴급 1200
    //static final int TASK_SIZE = 61; // 3. 거절 1201

    public static void main(String[] args) {
        // 예제에서 숫자의 크기는 20배 크다. corePoolSize: 100, maximumPoolSize: 200, capacity: 1000
        ExecutorService es = new ThreadPoolExecutor(5, 10, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));
        printState(es);

        long startMs = System.currentTimeMillis();

        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;

            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }

        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));
    }
}
