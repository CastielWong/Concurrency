
public class ThreadInDataRace extends Thread {
    public static int counter = 0;

    @Override
    public void run() {
        for (int i = 0; i < 100_000; i++) {
            counter++;
        }
    }
}
