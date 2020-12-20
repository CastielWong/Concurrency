import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static class Shopper extends Thread {
        private static ReentrantLock locker = new ReentrantLock();

        public static int tomatoCounter = 0;
        public static int eggCounter = 0;

        public void buyTomato() {
            locker.lock();
            tomatoCounter++;
            System.out.printf("Locks hold count: %,d\n", locker.getHoldCount());
            locker.unlock();
        }

        public void buyEgg() {
            locker.lock();
            eggCounter++;
            buyTomato();
            locker.unlock();
        }

        @Override
        public void run() {
            for (int i = 0; i < 10_000; i++) {
                buyTomato();
                buyEgg();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread aaron = new Shopper();
        Thread betty = new Shopper();

        aaron.start();
        betty.start();

        aaron.join();
        betty.join();

        System.out.printf("The tomato bought is: \t%,d\n", Shopper.tomatoCounter);
        System.out.printf("The egg bought is: \t%,d", Shopper.eggCounter);
    }
}
