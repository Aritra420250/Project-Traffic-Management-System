package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TrafficSignal extends Signal {
    private Timer timer;

    public TrafficSignal(String direction, int vehicleCount) {
        super(direction, vehicleCount);
    }

    @Override
    public void allowTraffic(int durationInSeconds) {
        setBackground(Color.GREEN);
        label.setText("GO: " + durationInSeconds + "s");
        if (timer != null) timer.cancel();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int count = durationInSeconds;

            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (count > 0) {
                        label.setText("GO: " + count + "s");
                        count--;
                    } else {
                        label.setText("RED");
                        setBackground(Color.RED);
                        timer.cancel();
                    }
                });
            }
        }, 0, 1000);
    }
}
