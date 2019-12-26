package main.java.common.solutions.parser;

import java.io.*;

public class EntityReader {
    public static StringBuilder stringBuilderFile;
    private EntityReader() {
    }

    public static StringBuilder readFromFile(File file) throws IOException {
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file))))
        {
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilderFile.append(line).append("\n");
            }
        }
        return stringBuilderFile;
    }
}
