package com.example.ToneMatrix;

/**
 * Created by Dan Voss on 4/3/14.
 */
public enum LowerPitch {
    E (164.81) {
        @Override
        public String toString() {
            return "E";
        }
    },
    F (174.61) {
        @Override
        public String toString() {
            return "F";
        }
    },
    G (196) {
        @Override
        public String toString() {
            return "G";
        }
    },
    A (220) {
        @Override
        public String toString() {
            return "A";
        }
    },
    B (246.94) {
        @Override
        public String toString() {
            return "B";
        }
    },
    C (261.63) {
        @Override
        public String toString() {
            return "C";
        }
    },
    D (293.66) {
        @Override
        public String toString() {
            return "D";
        }
    },
    e (329.63) {
        @Override
        public String toString() {
            return "e";
        }
    };

    private final double frequency;

    LowerPitch(double frequency) {
        this.frequency = frequency;
    }

    public abstract String toString();

    public double getFrequency() {
        return frequency;
    }

}
