import javax.swing.*;
import java.awt.*;

/**
 * Created by igushs on 11/7/15.
 */
public class Ui {
    private final JFrame frame = new JFrame();

    private final JPanel contentPanel = new JPanel();

    private final JLabel timeLabel = new JLabel("00:00:00");
    private final JButton startButton = new JButton("Start");
    private final JButton stopButton = new JButton("Stop");

    public Ui() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300, 40));

        frame.setContentPane(contentPanel);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));

        frame.add(timeLabel);
        frame.add(startButton);
        frame.add(stopButton);
    }

    public void show() {
        frame.setVisible(true);
    }
}
