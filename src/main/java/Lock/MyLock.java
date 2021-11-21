/**
 * 2. Реализовать потокобезопасный счетчик с помощью интерфейса Lock.
 */
package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock implements Runnable {
    Counter counter;
    private final Lock lock;
    private final int STOP;

    {
        STOP = 100;
    }

    public MyLock(Counter counter) {
        this.counter = counter;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        while (counter.getCount() < STOP) {
            try {
                Thread.sleep(1000);
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    counter.setCount(counter.increase());
                }
                System.out.println(counter.getCount());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
