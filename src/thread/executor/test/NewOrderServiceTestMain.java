package thread.executor.test;

import java.util.concurrent.ExecutionException;

public class NewOrderServiceTestMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String orderNo = "Order#1234"; // 예시 주문 번호
        //NewOrderService orderService = new NewOrderService();
        NewOrderServiceV2 orderService = new NewOrderServiceV2();
        orderService.order(orderNo);
    }
}
