import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockDemo {

    public static class Philosopher extends Thread {
        private final Lock firstChopstick, secondChopstick;
        private static int sushiCount = 100_000;
        private Random priority = new Random();

        public Philosopher(String name, Lock firstChopstick, Lock secondChopstick) {
            this.setName(name);
            this.firstChopstick = firstChopstick;
            this.secondChopstick = secondChopstick;
        }

        private void fixLiveLock() {
            try {
                Thread.sleep(priority.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            while (sushiCount > 0) {
                this.firstChopstick.lock();

                if (!secondChopstick.tryLock()) {
                    // problematic code
                    System.out.println(this.getName() + " released first chopstick.");
                    firstChopstick.unlock();

//                    // workable code
//                    fixLiveLock();

                    continue;
                }

                // eat the sushi only both locks are acquired
                try {
                    if (sushiCount > 0) {
                        sushiCount--;
                        System.out.printf(
                                "%s took a piece! Sushi remaining: %d\n",
                                this.getName(),
                                sushiCount
                        );
                    }
                } catch (ArithmeticException ex) {
                    System.out.println("-----Catch the divided by zero exception!-----");
                } finally {
                    secondChopstick.unlock();
                    firstChopstick.unlock();
                }


            }
        }
    }

    public static void main(String[] args) {
        Lock chopstickA = new ReentrantLock();
        Lock chopstickB = new ReentrantLock();
        Lock chopstickC = new ReentrantLock();

        Philosopher aaron = new Philosopher("Aaron", chopstickA, chopstickB);
        Philosopher betty = new Philosopher("Betty", chopstickB, chopstickC);
        Philosopher calla = new Philosopher("Calla", chopstickC, chopstickA);

        aaron.start();
        betty.start();
        calla.start();
    }
}
