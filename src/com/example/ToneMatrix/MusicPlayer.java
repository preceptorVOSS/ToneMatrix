package com.example.ToneMatrix;

import java.util.List;

/**
 * Created by veritoff on 3/27/14.
 */
public class MusicPlayer {

    private AudioGenerator audio;
    private boolean play = true;
    private List<double[]> track;

    public MusicPlayer(List<double[]> track) {
        this.track = track;
        audio = new AudioGenerator(8000);
        audio.createPlayer();
    }

    public void play() {
        while (play) {
            for (double[] phrase : track) {
                audio.writeSound(phrase);
                if (play == false)
                    break;
            }
        }
    }

    public void stop() {
        play = false;
        audio.destroyAudioTrack();
    }

}
