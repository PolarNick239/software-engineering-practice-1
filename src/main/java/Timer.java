import java.util.function.Consumer;

/**
 * Created by viacheslav on 07.11.2015.
 */
public class Timer {

    private static int beginTimeSec;
    private static Thread t;

    public static void start(final Consumer<Integer> consumer) {
        t = new Thread(new Runnable() {
            public void run() {

                beginTimeSec = (int) System.currentTimeMillis() / 1000;
                while (!t.isInterrupted()) {
                    consumer.accept((int) System.currentTimeMillis() / 1000 - beginTimeSec);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public static void stop() {
        t.interrupt();
    }
}
