package com.example.ToneMatrix;

/**
 * Created by Dan Voss on 3/31/14.
 */
public enum UpperPitch {
    A (440) {
        @Override
        public String toString() {
            return "A";
        }
    },
    C (523.25) {
        @Override
        public String toString() {
            return "C";
        }
    },
    D (587.33) {
        @Override
        public String toString() {
            return "D";
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
    a (880) {
        @Override
        public String toString() {
            return "a";
        }
    };

    private final double frequency;

    UpperPitch(double frequency) {
        this.frequency = frequency;
    }

    public abstract String toString();

    public double frequency() {
        return frequency;
    }
}
