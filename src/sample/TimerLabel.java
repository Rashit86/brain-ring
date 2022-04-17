package sample;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

class TimerLabel extends TimerTask {

    private Integer timeInt;
    private final Label labelTimer;
    private final Controller controller;

    public TimerLabel(Integer timer, Label labelTimer, Controller controller){
        timeInt = timer;
        this.labelTimer = labelTimer;
        this.controller = controller;
    }


    @Override
    public void run() {
        try {
            timer();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void timer() throws InterruptedException {


        while (timeInt >= 0) {
            Platform.runLater(() ->{
                labelTimer.setText(Integer.toString((timeInt % 3600) % 60));
                timeInt = timeInt - 1;
            });
            TimeUnit.SECONDS.sleep(1);
            if (controller.isFirstTeamReacted() || controller.isSecondTeamReacted()) {
                break;
            }
        }


    }
}
