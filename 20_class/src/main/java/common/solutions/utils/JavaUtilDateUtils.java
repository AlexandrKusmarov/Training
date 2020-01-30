package common.solutions.utils;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class JavaUtilDateUtils {

  private static final String PATTERN = "dd.MM.yyyy";

  private JavaUtilDateUtils(){

  }

  public static LocalDateTime valueOf(String dateStr, String pattern) throws ParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return LocalDateTime.parse(dateStr, formatter);
  }

  public static LocalDateTime valueOf(String dateStr) throws ParseException {
    return valueOf(dateStr, PATTERN);
  }

}
