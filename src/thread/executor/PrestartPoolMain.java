package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.executor.ExecutorUtils.*;

public class PrestartPoolMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        printState(es);

        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) es;
        poolExecutor.prestartAllCoreThreads(); // coreThread 갯수만큼 미리 스레드를 만들어둔다.
        printState(es);

        es.close();
    }
}
