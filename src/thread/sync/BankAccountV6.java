package thread.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BankAccountV6 implements BankAccount {

    private int balance;

    private final Lock lock = new ReentrantLock();

    public BankAccountV6(int initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public boolean withdraw(int amount) {
        log("거래 시작: " + getClass().getSimpleName());

        try {
            if (!lock.tryLock(500, TimeUnit.MILLISECONDS)) { // 0.5초 동안 락 획득을 시도, 획득하지 못하면 false 반환
                log("[진입 실패] 이미 처리중인 작업이 있습니다.");
                return false; //lock.lock() 을 하지 않기 때문에 unlock()을 할 필요도 없다.
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // tryLock()을 통해 lock 을 획득했다.
        try {
            // == 임계 영역 시작 ==
            log("[검증 시작] 출금액: " + amount + ", 잔액: " + balance);
            if (balance < amount) {
                log("[검증 실패] 출금액: " + amount + ", 잔액: " + balance);

                return false;
            }

            log("[검증 완료] 출금액: " + amount + ", 잔액: " + balance);
            sleep(1000); // 출금에 걸리는 시간으로 가정

            balance -= amount;
            log("[출금 완료] 출금액: " + amount + ", 잔액: " + balance);
            // == 임계 영역 종료 ==
        } finally {
            lock.unlock(); //반드시 unlock()을 해줘야 한다.
        }

        log("거래 종료");

        return true;
    }

    @Override
    public int getBalance() {
        lock.lock(); //ReentrantLock 이용하여 lock 걸기
        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
