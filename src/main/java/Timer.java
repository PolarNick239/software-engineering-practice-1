import java.util.function.Consumer;

/**
 * Created by viacheslav on 07.11.2015.
 */
public class Timer {

    private static int beginTimeSec;
    private static Thread timerThread;

    public static final int SECOND = 1000;

    public Timer(int i) {
        this.beginTimeSec = i;
    }

    /**
     * starts timer.
     *
     * @param callback
     */
    public static void start(final Consumer<Integer> callback) {
        timerThread = new Thread(() -> {

            beginTimeSec = (int) System.currentTimeMillis() / 1000;
            while (!timerThread.isInterrupted()) {
                callback.accept((int) System.currentTimeMillis() / 1000 - beginTimeSec);
                try {
                    Thread.sleep(SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }

    /**
     * stops timer.
     */
    public static void stop() {
        timerThread.interrupt();
    }
}
