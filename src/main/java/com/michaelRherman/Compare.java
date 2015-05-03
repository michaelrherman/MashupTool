package com.michaelRherman;

public class Compare {

    private static int sway;

    public static boolean compareDoubles(Double double1, Double double2) {
        Double min = double1 * ((100-sway)/100);
        Double max = double1 * ((100+sway)/100);
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

    public static boolean compareLong(Long long1, Long long2) {
        if (long1 == long2) {
            return true;
        } else {
            return false;
        }
    }
/*    public static int getSway() {
        return sway;
    }
*/
    public static void setSway(int sway) {
        Compare.sway = sway;
    }
}
