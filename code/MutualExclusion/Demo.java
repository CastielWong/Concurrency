
public class Demo {

    private static void showing(Thread firstThread, Thread secondThread) {
        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String printFormat = "%s\t%25s: %,d\n";
        String prefix = "Counter when in";

        Thread competitor_0 = new ThreadInDataRace();
        Thread competitor_1 = new ThreadInDataRace();
        showing(competitor_0, competitor_1);
        System.out.printf(printFormat, prefix, "Data Race", ThreadInDataRace.counter);

        Thread competitor_2 = new ThreadWithLock();
        Thread competitor_3 = new ThreadWithLock();
        showing(competitor_2, competitor_3);
        System.out.printf(printFormat, prefix, "Lock", ThreadWithLock.counter);


        Thread competitor_4 = new ThreadWithAtomicVariable();
        Thread competitor_5 = new ThreadWithAtomicVariable();
        showing(competitor_4, competitor_5);
        System.out.printf(printFormat,
                prefix,
                "Atomic Variable",
                ThreadWithAtomicVariable.counter.intValue());

        Thread competitor_6 = new ThreadInSynchronizedMethod();
        Thread competitor_7 = new ThreadInSynchronizedMethod();
        showing(competitor_6, competitor_7);
        System.out.printf(printFormat,
                prefix,
                "Synchronized Method",
                ThreadInSynchronizedMethod.counter);

        Thread competitor_8 = new ThreadInSynchronizedStatement();
        Thread competitor_9 = new ThreadInSynchronizedStatement();
        showing(competitor_8, competitor_9);
        System.out.printf(printFormat,
                prefix,
                "Synchronized Statement",
                ThreadInSynchronizedStatement.counter);
    }
}
