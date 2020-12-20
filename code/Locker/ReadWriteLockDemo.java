import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static class CalendarUser extends Thread {
        private static final String[] DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        private static int today = 0;

        private static ReentrantLock generalLocker = new ReentrantLock();

        private static ReentrantReadWriteLock locker = new ReentrantReadWriteLock();
        private static Lock readLocker = locker.readLock();
        private static Lock writeLocker = locker.writeLock();

        // 0 for reading, while 1 for writing
        private final int marker;

        public CalendarUser(String name, int marker) {
            this.setName(name);
            this.marker = marker;
        }

        private void applyGeneralLock() {
            try {
                generalLocker.lock();

                switch (this.marker) {
                    case 0: {
                        System.out.printf("%s saw that today is %s\n", this.getName(), DAYS[today]);
                        break;
                    }
                    case 1: {
                        today = (today + 1) % 7;
                        System.out.printf("%s updated date to %s\n", this.getName(), DAYS[today]);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                generalLocker.unlock();
            }
        }

        private void applyReadWriteLock() {
            switch (this.marker) {
                case 0: {
                    readLocker.lock();
                    try {
                        System.out.printf(
                                "%s saw that today is %s, total readers: %d\n",
                                this.getName(),
                                DAYS[today],
                                locker.getReadLockCount()
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        readLocker.unlock();
                    }

                    try {
                        sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case 1: {
                    writeLocker.lock();
                    try {
                        today = (today + 1) % 7;
                        System.out.printf("%s updated date to %s\n", this.getName(), DAYS[today]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        writeLocker.unlock();
                    }

                    try {
                        sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        public void run() {
            while (today < DAYS.length - 1) {
//                applyGeneralLock();

                applyReadWriteLock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new CalendarUser("Reader-" + i, 0).start();
        }

        for (int i = 0; i < 2; i++) {
            new CalendarUser("Writer-" + i, 1).start();
        }
    }
}
