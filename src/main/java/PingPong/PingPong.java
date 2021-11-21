/**
 * 1. Реализовать программу, в которой два потока поочередно пишут ping и pong.
 */

package PingPong;

public class PingPong {
    public static void main(String[] args) throws InterruptedException {
        final int STOP = 10;
        Object monitor = new Object();
        Counter counter = new Counter();

        for (int i = 0; i < STOP; i++) {
            System.out.println("Round " + (i + 1) + ": ");
            new Thread(() -> {
                try {
                    synchronized (monitor) {
                        while (counter.getCounter() != 0) {
                            monitor.wait();
                        }
                        new Game().ping();
                        counter.setCounter(1);
                        monitor.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(1000);

            new Thread(() -> {
                try {
                    synchronized (monitor) {
                        while (counter.getCounter() != 1) {
                            monitor.wait();
                        }
                        new Game().pong();
                        counter.setCounter(0);
                        monitor.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            Thread.sleep(1000);
            if (i == 9) {
                System.out.println("GAME OVER");
            }
        }
    }
}
