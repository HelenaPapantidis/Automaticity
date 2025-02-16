
package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

    public class CSVReader {
        public static String[] readCSV(String filePath) {
            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                if ((line = br.readLine()) != null) {
                    return line.split(","); // Podaci su odvojeni zarezima
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new String[0];
        }
    }


