package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static util.MyLogger.log;

public class RejectMainV2 {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2")); // 아무런 예외도 발생시키지 않고 조용히 처리됨
        executor.submit(new RunnableTask("task3"));

        executor.close();
    }
}
