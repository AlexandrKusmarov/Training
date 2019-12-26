package main.java.common.solutions.parser;

import java.io.*;

public class EntityReader {
    private EntityReader() {
    }

    public static StringBuilder readFromFile(File file) throws IOException {
        StringBuilder stringBuilder;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file))))
        {
            String line;
            stringBuilder = new StringBuilder();
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder;
    }
}
