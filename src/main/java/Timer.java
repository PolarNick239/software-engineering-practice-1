import java.util.function.Consumer;

/**
 * Created by viacheslav on 07.11.2015.
 */
public class Timer {

    private static int beginTimeSec;
    private static Thread timerThread;

    public static final int SECOND = 1000;
    public static volatile boolean timerRunning;
    public static int curTime;

    public Timer(int i) {
        this.curTime = i;
    }

    /**
     * starts timer.
     *
     * @param callback
     */
    public static void start(final Consumer<Integer> callback) {
        timerRunning = true;
        timerThread = new Thread(() -> {

            while (timerRunning) {
                callback.accept(curTime);
                try {
                    Thread.sleep(SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                curTime += 1;
            }
        });
        timerThread.start();
    }

    /**
     * stops timer.
     */
    public static void stop() {
        timerRunning = false;
    }
}
