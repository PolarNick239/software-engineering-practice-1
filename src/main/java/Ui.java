import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by igushs on 11/7/15.
 */
public class Ui {
    private final JFrame frame = new JFrame();

    private final JPanel contentPanel = new JPanel();

    private final JLabel timeLabel = new JLabel("00:00:00");
    private final JButton startButton = new JButton("Start");
    private final JButton stopButton = new JButton("Stop");

    private final Timer timer;

    public Ui(Timer timer) {
        this.timer = timer;

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 100));

        frame.setContentPane(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));

        frame.add(startButton);
        frame.add(stopButton);
        frame.add(timeLabel);

        startButton.addActionListener((actionEvent) -> {
            timer.start((i) -> {
                try (PrintWriter printWriter = new PrintWriter(new FileWriter("storage.txt"))) {
                    printWriter.println(System.currentTimeMillis() - (i * 1000));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                timeLabel.setText(String.valueOf(i));
            });
        });

        stopButton.addActionListener((actionEvent) -> {
            try (PrintWriter printWriter = new PrintWriter(new FileWriter("storage.txt"))) {
                printWriter.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
            timer.stop();
        });
    }

    public void show() {
        frame.setVisible(true);
    }
}
