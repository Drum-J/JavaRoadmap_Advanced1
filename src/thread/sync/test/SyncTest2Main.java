package thread.sync.test;

import static util.MyLogger.log;

public class SyncTest2Main {
    public static void main(String[] args) throws InterruptedException {
        MyCounter myCounter = new MyCounter();

        Runnable task = myCounter::count;
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();
    }

    static class MyCounter {
        public void count() {
            //지역 변수는 절대로 다른 스레드와 공유되지 않는다. 각 스레드의 스택 영역에 생성되기 때문
            int localValue = 0; //지역 변수! count() 메서드 안에 있다. MyCounter 의 멤버 변수가 아니다!
            for (int i = 0; i < 1000; i++) {
                localValue = localValue + 1;
            }
            log("결과: " + localValue);
        }
    }
}
