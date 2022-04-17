package sample;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static void playSound(String pathname) {
        File soundFile = new File(pathname); //Звуковой файл

        {
            try {
                //Получаем AudioInputStream
                //Вот тут могут полететь IOException и UnsupportedAudioFileException
                AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(ais);

                clip.setFramePosition(0); //устанавливаем указатель на старт
                clip.start(); //Поехали!!!
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

}
