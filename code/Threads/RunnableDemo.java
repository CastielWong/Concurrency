
public class RunnableDemo {
    public static class Chopper implements Runnable {
        public static boolean chopping = true;

        public String name;
        public int counter = 0;

        public Chopper(String name) {
            this.name = name;
        }

        public void run() {
            while (chopping) {
                System.out.println(this.name + " chopped a vegetable.");
                counter++;
            }
            // counter cannot be accessed when it's converted to a Thread
            System.out.printf("%s chopped %d vegetables.%n", this.name, this.counter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread aaron = new Thread(new Chopper("Aaron"));
        Thread betty = new Thread(new Chopper("Betty"));

        aaron.start();
        betty.start();

        // let the main thread to wait for 10 ms while choppers are chopping
        Thread.sleep(10);
        Chopper.chopping = false;

        // wait choppers to be finished
        aaron.join();
        betty.join();
    }
}
