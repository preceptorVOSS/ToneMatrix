package com.example.ToneMatrix;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

/**
 * Created by Dan Voss on 3/24/14.
 * This code is taken and adapted from the following project:
 * http://masterex.github.io/archive/2012/05/28/android-audio-synthesis.html
 */
public class AudioGenerator {

    private int sampleRate;
    private AudioTrack audioTrack;

    public AudioGenerator(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public double[] getSinWave(int samples, int sampleRate, double toneFrequency) {
        double[] sample = new double[samples];
        for (int i = 0; i < samples; i++) {
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/toneFrequency));
        }
        return sample;
    }

    public byte[] get16BitPcm(double[] samples) {
        byte[] generatedSound = new byte[2 * samples.length];
        int index = 0;
        for (double sample : samples) {

            short maxSample = (short) ((sample * Short.MAX_VALUE));

            generatedSound[index++] = (byte) (maxSample & 0x00ff);
            generatedSound[index++] = (byte) ((maxSample & 0xff00) >>> 8);
        }
        return generatedSound;
    }

    public void createPlayer() {
        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate,
                AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
                sampleRate, AudioTrack.MODE_STREAM);
        audioTrack.play();
    }

    public void writeSound(double[] samples) {
        byte[] generatedSound = get16BitPcm(samples);
        audioTrack.write(generatedSound, 0, generatedSound.length);
    }

    public void destroyAudioTrack() {
        audioTrack.stop();
        audioTrack.release();
    }

}
