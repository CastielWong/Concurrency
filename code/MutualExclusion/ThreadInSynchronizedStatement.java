
public class ThreadInSynchronizedStatement extends Thread {
    public static int counter = 0;

    public void run() {
        for (int i = 0; i < 100_000; i++) {
            synchronized (ThreadInSynchronizedStatement.class) {
                counter++;
            }
        }
    }
}
