package main.java;

public class IdGenerator {

    private static Long id = 0L;

    private IdGenerator() {
    }

    public static Long generateId() {
        return ++id;
    }

}
