
public class ThreadInSynchronizedMethod extends Thread {
    public static int counter = 0;

    private static synchronized void addCounter() {
        counter++;
    }

    public void run() {
        for (int i = 0; i < 100_000; i++) {
            addCounter();
        }
    }
}
