import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
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

    public Timer() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileReader("storage.txt"));
            String s = null;
            while (scanner.hasNext()) {
                s = scanner.nextLine();
            }
            if (s == null) {
                curTime = 0;
            } else {
                if (s.startsWith("stop")) {
                    curTime = Integer.parseInt(s.substring(4));
                } else {
                    curTime = (int) ((System.currentTimeMillis() - Long.parseLong(s.substring(5))) / 1000);
                }
            }
        } catch (FileNotFoundException e) {
            curTime = 0;
        }

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
