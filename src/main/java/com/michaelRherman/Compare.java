package com.michaelRherman;

public class Compare {

    private static int sway;

    public static boolean compareDoubles(Double double1, Double double2) {
        Double min = double1 * (100-sway);
        Double max = double1 * (100+sway);
        if (sway == 0) {
            if (double1 == double2) {
                return true;
            } else {
                return false;
            }
        } else {
            if (min <= double2 && double2 <= max) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void setSway(int sway) {
        this.sway = sway;
    }
}
