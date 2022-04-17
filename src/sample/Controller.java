package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Circle firstBulb;

    @FXML
    private Label firstTeamWarn;

    @FXML
    private Label labelTimer;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Circle secondBulb;

    @FXML
    private Label secondTeamWarn;

    private volatile boolean isGameStarted;
    private boolean firstTeamReacted;
    private boolean secondTeamReacted;

    public boolean isFirstTeamReacted() {
        return firstTeamReacted;
    }

    public boolean isSecondTeamReacted() {
        return secondTeamReacted;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void startGame() {
        isGameStarted = true;
        Sound.playSound("src/sample/hlopok.wav");

        TimeLine startTime = new TimeLine(progressBar, 20, "-fx-accent: #00FF00;", this);
        Thread startThread = new Thread(startTime);
        startThread.setDaemon(true);
        startThread.start();

        TimerLabel timerLabel = new TimerLabel(20, labelTimer, this);
        Thread timerThread = new Thread(timerLabel);
        timerThread.setDaemon(true);
        timerThread.start();
    }

    public void firstTeamHandle() {
        if (secondTeamReacted) return;

        if (!isGameStarted) {
            Sound.playSound("src/sample/falsestart.wav");
            firstBulb.setFill(Color.RED);
            firstTeamWarn.setText("Фальтстарт!");
            firstTeamReacted = true;
        } else {
            Sound.playSound("src/sample/win.wav");
            firstBulb.setFill(Color.GREEN);
            firstTeamReacted = true;
        }
    }

    public void secondTeamHandle() {
        if (firstTeamReacted) return;

        if (!isGameStarted) {
            Sound.playSound("src/sample/falsestart.wav");
            secondBulb.setFill(Color.RED);
            secondTeamWarn.setText("Фальтстарт!");
            secondTeamReacted = true;
        } else {
            Sound.playSound("src/sample/win.wav");
            secondBulb.setFill(Color.GREEN);
            secondTeamReacted = true;
        }
    }

    public void toDefault() {
        isGameStarted = false;
        progressBar.setProgress(0d);

        firstTeamReacted = false;
        secondTeamReacted = false;

        firstTeamWarn.setText(null);
        secondTeamWarn.setText(null);
        labelTimer.setText(null);

        firstBulb.setFill(null);
        secondBulb.setFill(null);

        Thread.currentThread().interrupt();
    }


}
