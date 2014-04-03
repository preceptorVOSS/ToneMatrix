package com.example.ToneMatrix;

/**
 * Created by veritoff on 3/31/14.
 */
public enum Pitch {
    C (523.25) {
        @Override
        public String toString() {
            return "C";
        }
    },
    E (659.26) {
        @Override
        public String toString() {
            return "E";
        }
    },
    G (783.991) {
        @Override
        public String toString() {
            return "G";
        }
    },
    A (880) {
        @Override
        public String toString() {
            return "A";
        }
    };

    private final double frequency;

    Pitch(double frequency) {
        this.frequency = frequency;
    }

    public abstract String toString();

    public double frequency() {
        return frequency;
    }
}
