import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {
    public static class Shopper extends Thread {
        private static Lock locker = new ReentrantLock();

        private int items = 0;

        public static int itemsOnNotepad = 0;

        public Shopper(String name) {
            this.setName(name);
        }

        private void runWithLock() {
            try {
                locker.lock();
                itemsOnNotepad += items;
                System.out.printf("%s adds %d item(s) to notepad.\n", this.getName(), this.items);
                items = 0;
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }
        }

        private void runWithTryLock() {
            try {
                itemsOnNotepad += items;
                System.out.printf("%s adds %d item(s) to notepad.\n", this.getName(), this.items);
                items = 0;
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                locker.unlock();
            }
        }

        @Override
        public void run() {
            while (itemsOnNotepad <= 20) {
//                // solution: general lock
//                if (items > 0) {
//                    runWithLock();

                // solution: TryLock
                if ((items > 0) && locker.tryLock()) {
                    runWithTryLock();

                } else {
                    try {
                        Thread.sleep(100);
                        items++;
                        System.out.printf("%s finds more to buy.\n", this.getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread aaron = new Shopper("Aaron");
        Thread betty = new Shopper("Betty");

        long startTIme = System.currentTimeMillis();
        aaron.start();
        betty.start();

        aaron.join();
        betty.join();
        long endTime = System.currentTimeMillis();

        System.out.printf("Total Items: %d.\n", Shopper.itemsOnNotepad);
        System.out.printf("Elapsed Time: %f seconds.\n", (float) (endTime - startTIme) / 1000);
    }
}
