import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StarvationDemo {

    public static class Philosopher extends Thread {
        private final Lock firstChopstick, secondChopstick;
        private static int sushiCount = 100_000;

        public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
            this.setName(name);
            this.firstChopstick = firstChopstick;
            this.secondChopstick = secondChopstick;
        }

        public void run() {
            int sushiEaten = 0;

            while (sushiCount > 0) {
                this.firstChopstick.lock();
                this.secondChopstick.lock();

                if (sushiCount > 0) {
                    sushiCount--;
                    sushiEaten++;
                }

                secondChopstick.unlock();
                firstChopstick.unlock();
            }

            System.out.printf(
                    "%s took %d pieces of sushi\n",
                    this.getName(),
                    sushiEaten
            );
        }
    }

    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();

        for (int i = 0; i < 500; i++) {
            new Philosopher("Philosopher " + i, chopstickA, chopstickB).start();
        }
    }
}
