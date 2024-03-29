package maze.core;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sql.rowset.serial.SerialBlob;

public class util {
    public static class statusCodes {
        public static enum dbStatus {
            OK,
            FAILED,
            UNAUTHORISED,
            INVALID_ARGUMENTS
        }

        public static enum readFile {
            FileNotFound,
            InvalidContents,
            FileToBig
        }

        public static enum universal {
            OK,
            FAILED
        }
    }

    /**
     * Finds the union between two sets
     * @author Hudson
     * @param a1 array 1
     * @param a2 array 2
     * @return a union of the two sets
     */
    public static boolean[] U(boolean[] a1, boolean[] a2) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i]) {
                a1[i] = false;
            }
        }

        return a1;
    }

    // grabbed from
    // https://stackoverflow.com/questions/3736058/java-object-to-byte-and-byte-to-object-converter-for-tokyo-cabinet
    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Maze deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return (Maze) is.readObject();
    }
}
