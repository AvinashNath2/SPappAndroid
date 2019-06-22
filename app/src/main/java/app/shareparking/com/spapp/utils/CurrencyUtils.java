package app.shareparking.com.spapp.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by arpitanand on 14/06/17.
 */
public final class CurrencyUtils {

  public static final Locale LOCALE_EN_IN = new Locale("en", "IN");

  private static final long ONE = 1L;
  private static final long ONE_THOUSAND = 1000L * ONE;
  private static final long ONE_LAKH = 100L * ONE_THOUSAND;
  private static final long ONE_CRORE = 100L * ONE_LAKH;

  private static final String UNIT_ONE = "";
  private static final String UNIT_CRORE = "crore";
  private static final String UNIT_LAKH = "lakh";
  private static final String UNIT_THOUSAND = "thousand";

  private static final String NEAREST_UNIT_STRING = "%s %s";

  public static String format(BigDecimal amount) {
    DecimalFormat twoPlaces = new DecimalFormat("₹ ##,##,##,##,##,##,###.##");
    return twoPlaces.format(amount);
  }

  public static String nearestUnit(BigDecimal bigDecimal) {
    long l = bigDecimal.longValue();
    long divisor;
    String unit;
    if (l >= ONE_CRORE) {
      divisor = ONE_CRORE;
      unit = UNIT_CRORE;
    } else if (l >= ONE_LAKH) {
      divisor = ONE_LAKH;
      unit = UNIT_LAKH;
      //} else if (l >= ONE_THOUSAND) {
      //  divisor = ONE_THOUSAND;
      //  unit = UNIT_THOUSAND;
    } else {
      divisor = ONE;
      unit = UNIT_ONE;
    }
    try {
      bigDecimal = bigDecimal.divide(new BigDecimal(divisor));
    } catch (ArithmeticException ae) {
      // use rounding
      bigDecimal = bigDecimal.divide(new BigDecimal(divisor), BigDecimal.ROUND_HALF_EVEN);
    }

    return String.format(LOCALE_EN_IN, NEAREST_UNIT_STRING, format(bigDecimal), unit);
  }


  public static Double formattedAmountStringToDouble(String amount) throws NumberFormatException {
    if (amount == null) {
      return null;
    }

    //Remove 'Rs.'. On older devices number formatter shows Rs. instead of rupee symbol
    amount = amount.replace("Rs.", "");

    //Remove all non-numeric characters
    String normalisedAmount = amount.replaceAll("[^\\d.]", "");
    return Double.valueOf(normalisedAmount);
  }

}
