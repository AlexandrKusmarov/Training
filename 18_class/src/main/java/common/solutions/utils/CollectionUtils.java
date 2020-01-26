package common.solutions.utils;

import java.util.Collection;

public final class CollectionUtils {

  private CollectionUtils() {

  }

  public static void printCollection(Collection<?> collection) {
    collection.forEach(System.out::println);

//    for (Object obj : collection) {
//      System.out.println(obj.toString());
//    }
  }

  public static boolean isNotEmpty(Collection<?> collection) {
    return collection != null && !collection.isEmpty();
  }

}
