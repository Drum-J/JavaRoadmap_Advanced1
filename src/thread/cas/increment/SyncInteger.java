package thread.cas.increment;

public class SyncInteger implements IncrementInteger {

    private int value;

    @Override
    public synchronized void increment() {
        // 이렇게 연산 자체가 나누어진 경우 안전한 임계 영역을 만들어야 한다
        // synchronized, lock 사용한다.
        value++;
    }

    @Override
    public synchronized int get() {
        return value;
    }
}
