package common.solutions.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializator {
    public static void writeObject(Object object, File outputFile) throws IOException {
        try (FileOutputStream file = new FileOutputStream(outputFile);
             ObjectOutputStream out = new ObjectOutputStream(file)) {

            out.writeObject(object);
        }
    }

    public static void writeListObjects(List<Object> objects, File outputFile) throws IOException {
        try (FileOutputStream file = new FileOutputStream(outputFile);
             ObjectOutputStream out = new ObjectOutputStream(file)) {

            for (Object object : objects) {
                out.writeObject(object);
            }
        }
    }

    public static Object readObjectFromFile(File inputFile) throws IOException, ClassNotFoundException {

        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
             ObjectInputStream in = new ObjectInputStream(fileInputStream)) {

            return in.readObject();
        }
    }

    public static List<Object> readListObjects(File inputFile) throws IOException, ClassNotFoundException {

        try (FileInputStream fileInputStream = new FileInputStream(inputFile);
             ObjectInputStream in = new ObjectInputStream(fileInputStream)) {
            List<Object> objectList = new ArrayList<>();
            while (in.available() != -1) {
                objectList.add(in.readObject());
            }

            return objectList;
        }
    }

}
