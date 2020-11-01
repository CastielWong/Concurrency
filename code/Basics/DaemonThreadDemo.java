
public class DaemonThreadDemo {
    public static class Cleaner extends Thread {
        public void run() {
            while (true) {
                System.out.println("Cleaner\tis doing cleaning");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread cleaner = new Cleaner();
        // set the clean to be a daemon thread, so that the main thread won't wait for it to finish
        cleaner.setDaemon(true);
        // cleaner is working in the background
        cleaner.start();

        System.out.println("Chef\tis cooking...");
        Thread.sleep(500);
        System.out.println("Chef\tis cooking...");
        Thread.sleep(500);
        System.out.println("Chef\tis cooking...");
        Thread.sleep(500);
        System.out.println("Chef\tis cooking...");
        Thread.sleep(500);
        System.out.println("Chef\tis cooking...");
        Thread.sleep(500);

        System.out.println("Chef\tfinished cooking.");
    }

}
