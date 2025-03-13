package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {
    public static void main(String[] args) throws InterruptedException {
        SimpleList list = new BasicList();
        list.add("A");
        list.add("B");
        System.out.println("list = " + list);

        test(new BasicList());
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        // A를 리스트에 저장하는 코드
        Runnable addA = () -> {
            list.add("A");
            log("Thread-1: list.add(A)");
        };

        // B를 리스트에 저장하는 코드
        Runnable addB = () -> {
            list.add("B");
            log("Thread-2: list.add(B)");
        };

        Thread t1 = new Thread(addA, "Thread-1");
        Thread t2 = new Thread(addB, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log(list);
    }
}
