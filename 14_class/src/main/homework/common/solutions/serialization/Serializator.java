package main.homework.common.solutions.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializator {
    public static void writeObjectToFile(Object object, File outputFile) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)){

            out.writeObject(object);
        }
    }

    public static void writeListObjectsToFile(List<Object> objectList, File outputFile ) throws IOException {
        try(FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)){
            for (Object o : objectList) {
                out.writeObject(o);
            }
        }
    }

    public static Object readObjectFromFile(File inputFile) throws IOException, ClassNotFoundException {
        try(FileInputStream fileInputStream = new FileInputStream(inputFile);
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){

            return in.readObject();
        }
    }

    public static List<Object> readObjectsListFromFile(File inputFile) throws IOException, ClassNotFoundException {
        try(FileInputStream fileInputStream = new FileInputStream(inputFile);
            ObjectInputStream in = new ObjectInputStream(fileInputStream)){

            List<Object> objectList = new ArrayList<>();

            while (in.available() != -1){
                objectList.add(in.readObject());
            }
            return objectList;
        }
    }
}
