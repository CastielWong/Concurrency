import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {

    public static class Philosopher extends Thread {
        private final Lock firstChopstick, secondChopstick;
        private static int sushiCount = 1_000;

        public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
            this.setName(name);
            this.firstChopstick = firstChopstick;
            this.secondChopstick = secondChopstick;
        }

        public void run() {
            while (sushiCount > 0) {
                this.firstChopstick.lock();
                this.secondChopstick.lock();

                if (sushiCount > 0) {
                    sushiCount--;
                    System.out.printf(
                            "%s took a piece! Sushi remaining: %d\n",
                            this.getName(),
                            sushiCount
                    );
                }

                secondChopstick.unlock();
                firstChopstick.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();

//        // problematic code
//        Philosopher aaron = new Philosopher("Aaron", chopstickA, chopstickB);
//        Philosopher betty = new Philosopher("Betty", chopstickB, chopstickC);
//        Philosopher calla = new Philosopher("Calla", chopstickC, chopstickA);

        // workable code
        Philosopher aaron = new Philosopher("Aaron", chopstickA, chopstickB);
        Philosopher betty = new Philosopher("Betty", chopstickB, chopstickC);
        Philosopher calla = new Philosopher("Calla", chopstickA, chopstickC);

        aaron.start();
        betty.start();
        calla.start();
    }
}
