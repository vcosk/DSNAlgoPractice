package in.vikk.dailycoding;

import java.util.Arrays;

// You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this, with the following API:
//  - record(order_id): adds the order_id to the log
//  - get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
// You should be as efficient with time and space as possible.

public class OrderLog {
    private int[] orderQueue;

    private int head = -1;

    private int tail = -1;

    public OrderLog(int size) {
        orderQueue = new int[size];
    }

    public void record(int order_id) {

        if (tail == -1) {
            tail = 0;
        }

        if (head < orderQueue.length - 1) {
            orderQueue[++head] = order_id;
        } else {
            head = 0;
            orderQueue[head] = order_id;
        }

        if (head == tail) {
            if (tail < orderQueue.length - 1) {
                tail += 1;
            } else {
                tail = 0;
            }
        }
    }

    public int get_last(int i) {
        int dataIndex = head - (i - 1);
        if (dataIndex < 0) {
            dataIndex += orderQueue.length;
        }

        return orderQueue[dataIndex];
    }

    @Override
    public String toString() {
        return "Head: " + head + " Tail: " + tail + " Log: " +Arrays.toString(orderQueue);
    }

    public static void main(String[] args) {
        OrderLog log = new OrderLog(5);
        log.record(1);
        log.record(2);
        log.record(3);
        log.record(4);
        log.record(5);
        System.out.println(log);
        log.record(6);
        log.record(7);
        log.record(8);
        System.out.println(log);

        System.out.println(log.get_last(4));
        System.out.println(log.get_last(1));
    }
}
