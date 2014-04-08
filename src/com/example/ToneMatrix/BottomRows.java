package com.example.ToneMatrix;

/**
 * Created by Dan Voss on 4/3/14.
 */
public class BottomRows {

    private int totalBottom;
    private int[] quadrants;
    private int[] rows;

    public BottomRows() {
        totalBottom = 0;
        quadrants = new int[4];
        rows = new int[4];
        for(int i = 0; i < 4; i++) {
            rows[i] = 0;
            quadrants[i] = 0;
        }
    }

    public void iterateTotal() {
        totalBottom++;
    }

    public void iterateQuadrants(int quadrant) {
        quadrants[quadrant]++;
    }

    public void iterateRows(int row) {
        rows[row]++;
    }

    public String[] getStringArray() {
        String[] stringArray = new String[16];
        String notes = calculateNotes();

        switch (notes.length()) {
            case 0:
                for (int x = 0; x < stringArray.length; x++) {
                    stringArray[x] = "";
                }
                break;
            case 1:
                for (int x = 0; x < stringArray.length; x++) {
                    stringArray[x] = notes;
                }
                break;
            case 2:
                for (int x = 0; x < stringArray.length; x++) {
                    if (x < 8)
                        stringArray[x] = String.valueOf(notes.charAt(0));
                    else
                        stringArray[x] = String.valueOf(notes.charAt(1));
                }
                break;
            case 3:
                for (int x = 0; x < stringArray.length; x++) {
                    if (x < 8)
                        stringArray[x] = String.valueOf(notes.charAt(0));
                    else if (x < 12)
                        stringArray[x] = String.valueOf(notes.charAt(1));
                    else
                        stringArray[x] = String.valueOf(notes.charAt(2));
                }
                break;
            default:
                for (int x = 0; x < stringArray.length; x++) {
                    if (x < 4)
                        stringArray[x] = String.valueOf(notes.charAt(0));
                    else if (x < 8)
                        stringArray[x] = String.valueOf(notes.charAt(1));
                    else if (x < 12)
                        stringArray[x] = String.valueOf(notes.charAt(2));
                    else
                        stringArray[x] = String.valueOf(notes.charAt(3));
                }
                break;
        }

        return stringArray;
    }

    private String calculateNotes() {
        String notes;

        if (totalBottom == 0) {
            notes = "";
        } else if (totalBottom <= 16) {
            notes = greatestRow(1);
        } else if (totalBottom <= 32) {
            notes = greatestRow(2);
        } else if (totalBottom <= 48) {
            notes = greatestRow(3);
        } else {
            notes = greatestQuadrant();
        }

        return notes;
    }

    private String greatestRow(int notes) {
        int best = 0;
        int row = 4;
        String key;

        for (int x = 0; x < rows.length; x++) {
            if (rows[x] > best) {
                row = x;
                best = rows[x];
            } else if (rows[x] == best) {
                row = 4;
            }
        }

        switch (row) {
            case 0:
                if (notes < 2)
                    key = "G";
                else if (notes < 3)
                    key = "GD";
                else
                    key = "GBD";
                break;
            case 1:
                if (notes < 2)
                    key = "F";
                else if (notes < 3)
                    key = "FC";
                else
                    key = "FAC";
                break;
            case 2:
                if (notes < 2)
                    key = "E";
                else if (notes < 3)
                    key = "EB";
                else
                    key = "EGB";
                break;
            case 3:
                if (notes < 2)
                    key = "A";
                else if (notes < 3)
                    key = "Ae";
                else
                    key = "ACe";
                break;
            default:
                if (notes < 2)
                    key = "C";
                else if (notes < 3)
                    key = "CG";
                else
                    key = "CeG";
                break;
        }

        return key;
    }

    private String greatestQuadrant() {
        int best = 0;
        int quadrant = 4;
        String key;

        for (int x = 0; x < quadrants.length; x++) {
            if (quadrants[x] > best) {
                quadrant = x;
                best = quadrants[x];
            } else if (quadrants[x] == best) {
                quadrant = 4;
            }
        }

        switch (quadrant) {
            case 0:
                key = "FCAe";
                break;
            case 1:
                key = "EGDF";
                break;
            case 2:
                key = "CeGB";
                break;
            case 3:
                key = "CBeF";
                break;
            default:
                key = "CeGB";
                break;
        }

        return key;
    }

}
