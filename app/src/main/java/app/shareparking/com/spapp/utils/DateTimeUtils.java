package app.shareparking.com.spapp.utils;

import android.app.DatePickerDialog;
import android.content.Context;
//
//import com.ofbusiness.app.buyer.R;
//
//import org.joda.time.DateTime;
//import org.joda.time.Period;
//import org.joda.time.format.PeriodFormatterBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class DateTimeUtils {

  private DateTimeUtils() {

  }

  public static String dd_MMM(long time) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
    return simpleDateFormat.format(new Date(time));
  }

  public static String dd_MMM_yyyy(long time) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    return simpleDateFormat.format(new Date(time));
  }

  public static String getDateAfterDays(int days) {
    Calendar cal = new GregorianCalendar(Locale.getDefault());
    cal.add(Calendar.DAY_OF_MONTH, days);
    return dd_MMM(cal.getTimeInMillis());
  }

  public static String getDisplayDate(Long timeInMillis) {
    if (timeInMillis == null) {
      return "-";
    }
    if (timeInMillis == 0L) {
      return "-";
    }
    return getDisplayDate((long) timeInMillis);
  }

  public static String getDisplayDate(long timeInMillis) {
    Calendar displayDate = Calendar.getInstance();
    displayDate.setTime(new Date(timeInMillis));
    Calendar now = Calendar.getInstance();
    if (displayDate.get(Calendar.YEAR) != now.get(Calendar.YEAR)) {
      return dd_MMM_yyyy(timeInMillis);
    } else {
      return dd_MMM(timeInMillis);
    }
  }

  public static long getTimeDifferenceInMinutes(Long timeInMillis) {
    return Calendar.getInstance().getTimeInMillis() - timeInMillis / 60000;
  }

  public static String dd_MMM_hh_mm(long timeInMillis) {
    SimpleDateFormat simpleDateFormat =
        new SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault());
    return simpleDateFormat.format(new Date(timeInMillis)).replace("am", "AM").replace("pm", "PM");
  }

  public static long parseDateAndGetMilliseconds(String dateString) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    try {
      return (dateFormat.parse(dateString)).getTime();
    } catch (ParseException e) {
      e.printStackTrace();
      return System.currentTimeMillis();
    }
  }

//  public static String when(long time) {
//    DateTime myBirthDate = new DateTime(time);
//    DateTime now = new DateTime();
//    Period period = new Period(myBirthDate, now);
//
//    PeriodFormatterBuilder formatterBuilder = new PeriodFormatterBuilder();
//    String suffix;
//    if (period.getYears() > 0) {
//      suffix = period.getYears() == 1 ? " year ago" : " years ago";
//      formatterBuilder = formatterBuilder.appendYears();
//    } else if (period.getMonths() > 0) {
//      suffix = period.getMonths() == 1 ? " month ago" : " months ago";
//      formatterBuilder = formatterBuilder.appendMonths();
//    } else if (period.getWeeks() > 0) {
//      suffix = period.getWeeks() == 1 ? " week ago" : " weeks ago";
//      formatterBuilder = formatterBuilder.appendWeeks();
//    } else if (period.getDays() > 0) {
//      suffix = period.getDays() == 1 ? " day ago" : " days ago";
//      formatterBuilder = formatterBuilder.appendDays();
//    } else if (period.getHours() > 0) {
//      suffix = period.getHours() == 1 ? " hour ago" : " hours ago";
//      formatterBuilder = formatterBuilder.appendHours();
//    } else if (period.getMinutes() > 0) {
//      suffix = period.getMinutes() == 1 ? " minute ago" : " minutes ago";
//      formatterBuilder = formatterBuilder.appendMinutes();
//    } else {
//      suffix = period.getSeconds() == 1 ? " second ago" : " seconds ago";
//      formatterBuilder = formatterBuilder.appendSeconds();
//    }
//
//    return formatterBuilder.appendSuffix(suffix).toFormatter().print(period);
//  }

  public static Calendar getCalendarForBeginningOfCurrentFinancialYear() {
    Calendar instance = Calendar.getInstance();
    int year = instance.get(Calendar.YEAR);
    if (instance.get(Calendar.MONTH) < Calendar.APRIL) {
      year -= 1; // use previous year
    }
    return getCalendarForBeginingOf(year, Calendar.APRIL, 1);
  }

  public static Calendar getCalendarForEndOfYesterday() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    return getCalendarForEndOf(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
  }

  public static Calendar getCalendarForEndOf(int year, int month, int date) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_MONTH, -1);
    return getEndOfDay(calendar);
  }

  public static Calendar getCalendarForBeginingOf(int year, int month, int date) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, date);
    return getStartOfDay(calendar);
  }

  public static Calendar getStartOfDay(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar;
  }

  public static Calendar getEndOfDay(Calendar calendar) {
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 999);
    return calendar;
  }

  public static Calendar daysDifferenceFromToday(int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, days);
    return calendar;
  }
}