package main.java.common.solutions.parser;

import java.io.*;

public class EntityReader {
    public static StringBuilder stringBuilderFile = new StringBuilder();
    private EntityReader() {
    }

    public static StringBuilder readFromFile(File file) throws IOException {
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file))))
        {
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
                stringBuilderFile.append(line).append(System.lineSeparator());
            }
        }
        return stringBuilderFile;
    }
}
