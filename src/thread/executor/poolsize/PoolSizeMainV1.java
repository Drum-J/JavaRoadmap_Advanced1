package thread.executor.poolsize;

import thread.executor.ExecutorUtils;
import thread.executor.RunnableTask;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.*;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV1 {
    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);

        ExecutorService es = new ThreadPoolExecutor(2, 4, 3000,
                TimeUnit.MILLISECONDS, workQueue);
        printState(es);

        es.execute(new RunnableTask("task1"));
        printState(es,"task1");

        es.execute(new RunnableTask("task2"));
        printState(es,"task2");

        es.execute(new RunnableTask("task3")); //queue 에 들어감
        printState(es,"task3");

        es.execute(new RunnableTask("task4")); //queue 에 들어감
        printState(es,"task4");

        es.execute(new RunnableTask("task5")); //queue 최대 -> 스레드가 하나 더 만들어진다.
        printState(es,"task5");

        es.execute(new RunnableTask("task6")); //maximumPoolSize인 4까지 늘어났다
        printState(es,"task6");

        try {
            es.execute(new RunnableTask("task7")); // RejectedExecutionException 발생!
        } catch (RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생: " + e);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000); // 3초간 일이 없음
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es); // 스레드의 수가 기본갯수(corePoolSize)인 2개로 줄어듬

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
