package common.solutions.utils;

import java.util.Arrays;
import java.util.Objects;

public final class ArrayUtils {

  private ArrayUtils() {
  }

  public static void copyArray(Object[] src, Object[] dest) {
    System.arraycopy(src, 0, dest, 0, src.length);
  }

  public static void printArray(Object[] arr) {
    Arrays.stream(arr).forEach(System.out::println);
    
//    for (Object obj : arr) {
//      if (obj != null) {
//        System.out.println(obj);
//      }
//    }
  }

  public static void removeElement(Object[] arr, int index) {
    System.arraycopy(arr, index + 1, arr, index, arr.length - 1 - index);
  }

}