import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWithLock extends Thread {
    public static int counter = 0;
    public static Lock locker = new ReentrantLock();

    public void run() {
        for (int i = 0; i < 100_000; i++) {
            locker.lock();
            counter++;
            locker.unlock();
        }
    }
}