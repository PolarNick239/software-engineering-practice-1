import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Time;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Created by static viacheslav on 07.11.2015.
 */
public class Timer {

    private static int beginTimeSec;
    private static Thread timerThread;

    public static final int SECOND = 1000;
    public static volatile boolean timerRunning;
    public static int curTime;

    private static Consumer<Integer> callback;

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
                if (s.startsWith("pause")) {
                    curTime = Integer.parseInt(s.substring("pause".length()));
                } else if (s.startsWith("start")) {
                    curTime = (int) ((System.currentTimeMillis() - Long.parseLong(s.substring(5))) / 1000);
                    start();
                } else {

                }
            }
        } catch (FileNotFoundException e) {
            curTime = 0;
        }

    }

    public static void setCALLBACK(Consumer<Integer> callback) {
        Timer.callback = callback;
    }

    /**
     * starts timer.
     *
     */
    public static void start() {
        timerRunning = true;
        timerThread = new Thread(() -> {

            while (timerRunning) {
                if (Timer.callback != null) {
                    Timer.callback.accept(curTime);
                }
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
     * pauses Timer.
     */
    public static void pause() {
        timerRunning = false;
    }

    /**
     * stops Timer.
     */
    public static void stop() {
        timerRunning = false;
        curTime = 0;
        if (Timer.callback != null) {
            Timer.callback.accept(curTime);
        }
    }
}
