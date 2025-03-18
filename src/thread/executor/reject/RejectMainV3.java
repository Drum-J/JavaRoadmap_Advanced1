package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
        // 호출한 스레드가 직접 작업을 수행하게 한다

        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2")); // main 스레드가 실행함
        executor.submit(new RunnableTask("task3")); // main 스레드가 실행함
        executor.submit(new RunnableTask("task4")); // task1이 끝나서 스레드 풀의 스레드가 실행함

        executor.close();
    }
}
