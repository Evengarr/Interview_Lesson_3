package Lock;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
            MyLock lock = new MyLock(counter);
            lock.run();
    }
}
