package main.java.util;

import java.util.Arrays;

public class ArrayCapacityChanger {

    private ArrayCapacityChanger() {
    }

    public static Object[] expandArrCapacity(Object[] array) {
        Object[] tempArr;
        tempArr = new Object[array.length * 2];
        tempArr = Arrays.copyOf(array, tempArr.length);
        return tempArr;
    }

    public static Object[] expandArrCapacityByOne(Object[] array) {
        Object[] tempArr;
        tempArr = new Object[array.length + 1];
        tempArr = Arrays.copyOf(array, tempArr.length);
        return tempArr;
    }

    public static Object[] constrictionArrCapacityByOne(Object[] array){
        Object[] tempArr;
        tempArr = new Object[array.length - 1];
        tempArr = Arrays.copyOf(array, tempArr.length);
        return tempArr;
    }
}
