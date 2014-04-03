package com.example.ToneMatrix;

import java.util.HashMap;

/**
 * Created by veritoff on 3/25/14.
 */
public class Notes {

    int sampleRate;
    static final int SAMPLES = 2600;

    double[] silence;

    AudioGenerator generateNotes;

    HashMap<String, double[]> oneBeat;
    HashMap<String, double[]> twoBeats;
    HashMap<String, double[]> threeBeats;
    HashMap<String, double[]> fourBeats;

    public Notes(int sampleRate) {
        this.sampleRate = sampleRate;
        generateNotes = new AudioGenerator(sampleRate);

        silence = generateSilence();
        oneBeat = generateOneBeat();
        twoBeats = generateTwoBeats();
        threeBeats = generateThreeBeats();
        fourBeats = generateFourBeats();
    }

    private double[] generateSilence() {
        double[] phrase;
        phrase = generateNotes.getSinWave(SAMPLES, sampleRate, 0);
        return phrase;
    }

    private HashMap<String, double[]> generateOneBeat() {
        double[] phrase;
        int pointer = 0;
        double[] tempSilence, temp;

        tempSilence = generateNotes.getSinWave(200, sampleRate, 0);
        HashMap<String, double[]> oneBeat = new HashMap<String, double[]>();

        for (Pitch p : Pitch.values()) {
            temp = generateNotes.getSinWave(2400, sampleRate, p.frequency());
            phrase = new double[SAMPLES];

            for (int x = 0; x < temp.length; x++)
                phrase[x] = temp[x];
            pointer = temp.length;
            for (int x = 0; x < tempSilence.length; x++)
                phrase[x + pointer] = tempSilence[x];

            oneBeat.put(p.toString(), phrase);
        }

        return oneBeat;
    }

    private HashMap<String, double[]> generateTwoBeats() {
        double[] phrase;
        int pointer = 0;
        double[] tempSilence, temp1, temp2;

        HashMap<String, double[]> twoBeats = new HashMap<String, double[]>();
        tempSilence = generateNotes.getSinWave(200, sampleRate, 0);

        for (Pitch p1 : Pitch.values()) {
            for (Pitch p2 : Pitch.values()) {
                if (p1 == p2)
                    continue;

                temp1 = generateNotes.getSinWave(1100, sampleRate, p1.frequency());
                temp2 = generateNotes.getSinWave(1100, sampleRate, p2.frequency());
                phrase = new double[SAMPLES];

                for (int x = 0; x < temp1.length; x++)
                    phrase[x] = temp1[x];
                pointer = temp1.length;
                for (int x = 0; x < tempSilence.length; x++)
                    phrase[x + pointer] = tempSilence[x];
                pointer += tempSilence.length;
                for (int x = 0; x < temp2.length; x++)
                    phrase[x + pointer] = temp2[x];
                pointer += temp2.length;
                for (int x = 0; x < tempSilence.length; x++)
                    phrase[x + pointer] = tempSilence[x];

                twoBeats.put((p1.toString() + p2.toString()), phrase);
            }
        }

        return twoBeats;
    }

    private HashMap<String, double[]> generateThreeBeats() {
        double[] phrase;
        int pointer = 0;
        double[] tempSilence1, tempSilence2, temp1, temp2, temp3;

        HashMap<String, double[]> threeBeats = new HashMap<String, double[]>();
        tempSilence1 = generateNotes.getSinWave(115, sampleRate, 0);
        tempSilence2 = generateNotes.getSinWave(120, sampleRate, 0);

        for (Pitch p1 : Pitch.values()) {
            for (Pitch p2 : Pitch.values()) {
                if (p1 == p2)
                    continue;

                for (Pitch p3 : Pitch.values()) {
                    if ((p1 == p3) | (p2 == p3))
                        continue;

                    temp1 = generateNotes.getSinWave(750, sampleRate, p1.frequency());
                    temp2 = generateNotes.getSinWave(750, sampleRate, p2.frequency());
                    temp3 = generateNotes.getSinWave(750, sampleRate, p3.frequency());
                    phrase = new double[SAMPLES];

                    for (int x = 0; x < temp1.length; x++)
                        phrase[x] = temp1[x];
                    pointer = temp1.length;
                    for (int x = 0; x < tempSilence1.length; x++)
                        phrase[x + pointer] = tempSilence1[x];
                    pointer += tempSilence1.length;
                    for (int x = 0; x < temp2.length; x++)
                        phrase[x + pointer] = temp2[x];
                    pointer += temp2.length;
                    for (int x = 0; x < tempSilence1.length; x++)
                        phrase[x + pointer] = tempSilence1[x] ;
                    pointer += tempSilence1.length;
                    for (int x = 0; x < temp3.length; x++)
                        phrase[x + pointer] = temp3[x];
                    pointer += temp3.length;
                    for (int x = 0; x < tempSilence2.length; x++)
                        phrase[x + pointer] = tempSilence2[x];

                    threeBeats.put((p1.toString() + p2.toString() + p3.toString()), phrase);
                }
            }
        }
        return threeBeats;
    }

    private HashMap<String, double[]> generateFourBeats() {
        double[] phrase;
        int pointer = 0;
        double[] tempSilence, temp1, temp2, temp3, temp4;

        HashMap<String, double[]> fourBeats = new HashMap<String, double[]>();
        tempSilence = generateNotes.getSinWave(100, sampleRate, 0);

        for (Pitch p1 : Pitch.values()) {
            for (Pitch p2 : Pitch.values()) {
                if (p1 == p2)
                    continue;

                for (Pitch p3 : Pitch.values()) {
                    if ((p1 == p3) | (p2 == p3))
                        continue;

                    for (Pitch p4 : Pitch.values()) {
                        if ((p1 == p4) | (p2 == p4) | (p3 == p4))
                            continue;

                        temp1 = generateNotes.getSinWave(550, sampleRate, p1.frequency());
                        temp2 = generateNotes.getSinWave(550, sampleRate, p2.frequency());
                        temp3 = generateNotes.getSinWave(550, sampleRate, p3.frequency());
                        temp4 = generateNotes.getSinWave(550, sampleRate, p4.frequency());
                        phrase = new double[SAMPLES];

                        for (int x = 0; x < temp1.length; x++)
                            phrase[x] = temp1[x];
                        pointer = temp1.length;
                        for (int x = 0; x < tempSilence.length; x++)
                            phrase[x + pointer] = tempSilence[x];
                        pointer += tempSilence.length;
                        for (int x = 0; x < temp2.length; x++)
                            phrase[x + pointer] = temp2[x];
                        pointer += temp2.length;
                        for (int x = 0; x < tempSilence.length; x++)
                            phrase[x + pointer] = tempSilence[x];
                        pointer += tempSilence.length;
                        for (int x = 0; x < temp3.length; x++)
                            phrase[x + pointer] = temp3[x];
                        pointer += temp3.length;
                        for (int x = 0; x < tempSilence.length; x++)
                            phrase[x + pointer] = tempSilence[x];
                        pointer += tempSilence.length;
                        for (int x = 0; x < temp4.length; x++)
                            phrase[x + pointer] = temp4[x];
                        pointer += temp4.length;
                        for (int x = 0; x < tempSilence.length; x++)
                            phrase[x + pointer] = tempSilence[x];

                        fourBeats.put((p1.toString() + p2.toString() + p3.toString() + p4.toString()),
                                phrase);
                    }
                }
            }
        }
        return fourBeats;
    }

    public double[] getPhrase(String type) {
        double[] phrase;

        switch(type.length()) {
            case 1:
                phrase = oneBeat.get(type);
                break;
            case 2:
                phrase = twoBeats.get(type);
                break;
            case 3:
                phrase = threeBeats.get(type);
                break;
            case 4:
                phrase = fourBeats.get(type);
                break;
            default:
                phrase = silence;
                break;
        }
        return phrase;

    }

}
