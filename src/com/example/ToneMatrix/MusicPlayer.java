package com.example.ToneMatrix;

import java.util.List;

/**
 * Created by veritoff on 3/27/14.
 */
public class MusicPlayer {

    private AudioGenerator topAudio;
    private AudioGenerator bottomAudio;
    private boolean play = true;
    private List<double[]> lowerTrack;
    private List<double[]> upperTrack;

    public MusicPlayer() {
        //this.track = track;
        topAudio = new AudioGenerator(8000);
        bottomAudio = new AudioGenerator(8000);
        topAudio.createPlayer();
        bottomAudio.createPlayer();
    }

    public void setTracks(List<double[]> lowerTrack, List<double[]> upperTrack) {
        this.lowerTrack = lowerTrack;
        this.upperTrack = upperTrack;
    }

    public void playTop() {
        while (play) {
            for (double[] phrase : upperTrack) {
                topAudio.writeSound(phrase);
                if (play == false)
                    break;
            }
        }
    }

    public void playBottom() {
        while (play) {
            for (double[] phrase : lowerTrack) {
                bottomAudio.writeSound(phrase);
                if (play == false)
                    break;
            }
        }
    }

    public void stop() {
        play = false;
        topAudio.destroyAudioTrack();
        bottomAudio.destroyAudioTrack();
    }

}
