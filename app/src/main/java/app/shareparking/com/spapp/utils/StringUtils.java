package app.shareparking.com.spapp.utils;

import java.util.regex.Pattern;

public final class StringUtils {
  private StringUtils() {

  }

  private static final Pattern EMAIL_ADDRESS = Pattern.compile(
      "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}"
          + "\\@"
          + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"
          + "("
          + "\\."
          + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"
          + ")+");

  public static boolean isEmpty(String s) {
    return (s == null || s.isEmpty());
  }

  public static String stringOrZero(String string) {
    if (StringUtils.isEmpty(string)) {
      return "0.0";
    } else {
      return string;
    }
  }

  public static boolean isValidEmail(String string) {
    return !isEmpty(string) && string.matches(EMAIL_ADDRESS.pattern());
  }

  public static String getInitials(String string) {
    if (isEmpty(string)) {
      return null;
    }
    string = string.toUpperCase();
    String[] words = string.split(" ");
    StringBuilder stringBuilder = new StringBuilder();
    for (String word : words) {
      stringBuilder.append(word.charAt(0));
    }
    return stringBuilder.toString();
  }

  public static String getLowerCaseUnderscore(String text) {
    String[] words = text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
    String lowerCaseUnderscore = "";
    for (String word : words) {
      if (!lowerCaseUnderscore.isEmpty()) {
        lowerCaseUnderscore += "_";
      }
      lowerCaseUnderscore += word;
    }
    return lowerCaseUnderscore;
  }
}
