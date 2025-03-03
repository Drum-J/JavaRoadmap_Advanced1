package thread.control;

import util.ThreadUtils;

public class CheckedExceptionMain {
    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {

        @Override
        // 예외를 던질 수 없다. Runnable 인터페이스에서 체크 예외를 던지지 않기 때문
        public void run() /*throws Exception*/ {
            // throw new Exception();

            ThreadUtils.sleep(1000); //ThreadUtils 를 사용해서 sleep 예외 처리
        }
    }
}
