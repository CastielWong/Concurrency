import java.util.concurrent.atomic.AtomicInteger;

public class ThreadWithAtomicVariable extends Thread {
    public static AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            counter.incrementAndGet();
        }
    }
}