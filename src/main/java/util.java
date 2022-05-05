package src.main.java;

public class util {
    public static class statusCodes {
        public static enum dbStatus {
            OK,
            FAILED,
            UNAUTHORISED,
            INVALID_ARGUMENTS
        }
    }

    public static boolean[] U(boolean[] a1, boolean[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                a1[i] = false;
            }
        }

        return a1;
    }
}
