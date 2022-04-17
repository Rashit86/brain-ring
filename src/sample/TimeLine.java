package sample;

import javafx.scene.control.ProgressBar;

import java.util.concurrent.TimeUnit;

public class TimeLine implements Runnable {

    private final ProgressBar progressBar;
    private int seconds;
    private final String style;
    private final Controller controller;


    public TimeLine(ProgressBar progressBar, int seconds, String style, Controller controller) {
        this.progressBar = progressBar;
        this.seconds = seconds;
        this.style = style;
        this.controller = controller;
    }

    @Override
    public void run() {
        double progress = 0d;
        progressBar.setStyle(style);
        int parts = seconds;
        while (seconds-- > 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
                if (controller.isFirstTeamReacted() || controller.isSecondTeamReacted()) {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress += Math.round(100d/parts);
            progressBar.setProgress(progress/100);
        }
        Sound.playSound("src/sample/vremya.wav");
    }
}
