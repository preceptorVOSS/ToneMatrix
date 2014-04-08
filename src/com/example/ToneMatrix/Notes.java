package com.example.ToneMatrix;

import java.util.HashMap;

/**
 * Created by Dan Voss on 3/25/14.
 */
public class Notes {

    int sampleRate;
    static final int SAMPLES = 4000;

    double[] silence;

    AudioGenerator generateNotes;

    HashMap<String, double[]> oneBeat;
    HashMap<String, double[]> twoBeats;
    HashMap<String, double[]> threeBeats;
    HashMap<String, double[]> fourBeats;

    HashMap<String, double[]> longNotes;

    public Notes(int sampleRate) {
        this.sampleRate = sampleRate;
        generateNotes = new AudioGenerator(sampleRate);

        silence = generateSilence();
        oneBeat = generateOneBeat();
        twoBeats = generateTwoBeats();
        threeBeats = generateThreeBeats();
        fourBeats = generateFourBeats();

        longNotes = generateLongNotes();
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

        for (UpperPitch p : UpperPitch.values()) {
            temp = generateNotes.getSinWave(3800, sampleRate, p.frequency());
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

        for (UpperPitch p1 : UpperPitch.values()) {
            for (UpperPitch p2 : UpperPitch.values()) {
                if (p1 == p2)
                    continue;

                temp1 = generateNotes.getSinWave(1800, sampleRate, p1.frequency());
                temp2 = generateNotes.getSinWave(1800, sampleRate, p2.frequency());
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
        tempSilence1 = generateNotes.getSinWave(200, sampleRate, 0);
        tempSilence2 = generateNotes.getSinWave(150, sampleRate, 0);

        for (UpperPitch p1 : UpperPitch.values()) {
            for (UpperPitch p2 : UpperPitch.values()) {
                if (p1 == p2)
                    continue;

                for (UpperPitch p3 : UpperPitch.values()) {
                    if ((p1 == p3) | (p2 == p3))
                        continue;

                    temp1 = generateNotes.getSinWave(1150, sampleRate, p1.frequency());
                    temp2 = generateNotes.getSinWave(1150, sampleRate, p2.frequency());
                    temp3 = generateNotes.getSinWave(1150, sampleRate, p3.frequency());
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
        tempSilence = generateNotes.getSinWave(150, sampleRate, 0);

        for (UpperPitch p1 : UpperPitch.values()) {
            for (UpperPitch p2 : UpperPitch.values()) {
                if (p1 == p2)
                    continue;

                for (UpperPitch p3 : UpperPitch.values()) {
                    if ((p1 == p3) | (p2 == p3))
                        continue;

                    for (UpperPitch p4 : UpperPitch.values()) {
                        if ((p1 == p4) | (p2 == p4) | (p3 == p4))
                            continue;

                        temp1 = generateNotes.getSinWave(850, sampleRate, p1.frequency());
                        temp2 = generateNotes.getSinWave(850, sampleRate, p2.frequency());
                        temp3 = generateNotes.getSinWave(850, sampleRate, p3.frequency());
                        temp4 = generateNotes.getSinWave(850, sampleRate, p4.frequency());
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

    private HashMap<String, double[]> generateLongNotes() {
        double[] phrase;
        HashMap<String, double[]> longNotes = new HashMap<String, double[]>();

        for (LowerPitch p : LowerPitch.values()) {
            phrase = generateNotes.getSinWave(4000, sampleRate, p.getFrequency());
            longNotes.put(p.toString(), phrase);
        }

        return longNotes;
    }

    public double[] getUpperPhrase(String type) {
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

    public double[] getLowerPhrase(String note) {
        if (note.length() < 1)
            return silence;
        else
            return longNotes.get(note);
    }

}
