
public class ThreadDemo {
    public static class Chopper extends Thread {
        public static boolean chopping = true;

        public int counter = 0;

        public Chopper(String name) {
            // set the Thread name
            this.setName(name);
        }

        public void run() {
            while (chopping) {
                System.out.println(this.getName() + " chopped a vegetable.");
                counter++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Chopper aaron = new Chopper("Aaron");
        Chopper betty = new Chopper("Betty");

        aaron.start();
        betty.start();

        // let the main thread to wait for 10 ms while choppers are chopping
        Thread.sleep(10);
        Chopper.chopping = false;

        // wait choppers to be finished
        aaron.join();
        betty.join();

        System.out.printf("Aaron chopped %d vegetables.%n", aaron.counter);
        System.out.printf("Betty chopped %d vegetables.%n", betty.counter);
    }
}
