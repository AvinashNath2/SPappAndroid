package app.shareparking.com.spapp.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public final class Utils {
  private static String TAG = "UTILS";
  private static String sUserAgent;

  private Utils() {

  }

  public static String encodeQuery(String query) {
    try {
      return URLEncoder.encode(query, "utf-8");
    } catch (UnsupportedEncodingException e) {
//      Timber.e(TAG, "Failed to encode query", e);
    }
    return query;
  }

  public static String getRealPathFromURI(Context context, Uri contentURI) {
    String result;
    Cursor cursor = null;
    try {
      cursor = context.getContentResolver().query(contentURI, null, null, null, null);
      if (cursor == null) {
        // Source is Dropbox or other similar local file path
        result = contentURI.getPath();
      } else {
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        result = cursor.getString(idx);
      }
    } catch (Exception e) {
      result = "";
    } finally {
      if (cursor != null) cursor.close();
    }
    return result;
  }

  public static void dbDump(Context context, String currentDBPath) throws Exception {
    File sd = Environment.getExternalStorageDirectory();
    String dbPath;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      dbPath =
          context.getFilesDir().getAbsolutePath().replace("files", "databases") + File.separator;
    } else {
      dbPath = context.getFilesDir().getPath() + context.getPackageName() + "/databases/";
    }

    if (sd.canWrite()) {
      //            String currentDBPath = DatabaseHelper.DATABASE_NAME;
      String backupDBPath = "AgentX.db";
      File currentDB = new File(dbPath, currentDBPath);
      File backupDB = new File(sd, backupDBPath);

      if (currentDB.exists()) {
        FileChannel src = new FileInputStream(currentDB).getChannel();
        FileChannel dst = new FileOutputStream(backupDB).getChannel();
        dst.transferFrom(src, 0, src.size());
        src.close();
        dst.close();
      }
    }
  }

  public static boolean equals(Object o1, Object o2) {
    if (o1 != null && o2 != null) {
      return o1.equals(o2);
    } else {
      return o1 == null && o2 == null;
    }
  }

  public static int getDeviceWidth(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point point = new Point();
    display.getSize(point);
    return point.x;
  }

  public static int getDeviceHeight(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    Point point = new Point();
    display.getSize(point);
    return point.y;
  }

  public static float pixelsToDp(Context context, int px) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px,
        context.getResources().getDisplayMetrics());
  }

  public static int dpToPixels(Context context, float dps) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dps * scale + 0.5f);
  }

  public static float pixelsToSp(Context context, float px) {
    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
    return px / scaledDensity;
  }

  public static float spToPixels(Context context, float sp) {
    float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
    return sp * scaledDensity;
  }

  /**
   * Function to convert milliseconds time to Timer Format
   * Hours:Minutes:Seconds
   */
  public static String milliSecondsToTimer(long milliseconds) {
    String finalTimerString = "";
    String secondsString = "";

    // Convert total duration into time
    int hoursDivider = 1000 * 60 * 60;
    int minutesDivider = 1000 * 60;
    int hours = (int) milliseconds / hoursDivider;
    milliseconds %= hoursDivider;
    int minutes = (int) milliseconds / minutesDivider;
    milliseconds %= minutesDivider;
    int seconds = (int) milliseconds / 1000;
    // Add hours if there
    if (hours > 0) {
      finalTimerString = hours + ":";
    }

    // Prepending 0 to seconds if it is one digit
    if (seconds < 10) {
      secondsString = "0" + seconds;
    } else {
      secondsString = "" + seconds;
    }

    finalTimerString = finalTimerString + minutes + ":" + secondsString;

    // return timer string
    return finalTimerString;
  }

    /*public static SpannableString getSpannableString(Context context, String text) {
        SpannableString span = new SpannableString(text);
        span.setSpan(new TypefaceSpan(context, FontStyle.REGULAR), 0, span.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }
*/
    /*public static SpannableString getScaledSpannableString(Context context, String text, float scaleFactor) {
        SpannableString span = getSpannableString(context, text);
        span.setSpan(new RelativeSizeSpan(scaleFactor), 0, span.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }*/

  public static String inputStreamToString(ByteArrayInputStream bais) throws IOException {

    GZIPInputStream gzis = new GZIPInputStream(bais);
    InputStreamReader reader = new InputStreamReader(gzis);
    BufferedReader in = new BufferedReader(reader);

    String outString = "", readed;
    while ((readed = in.readLine()) != null) {
      outString += readed;
    }
    return outString;
  }

  public static boolean permissionGranted(Context context, String[] permissions) {
    for (String permission : permissions) {
      if (!permissionGranted(context, permission)) return false;
    }
    return true;
  }

  public static boolean permissionGranted(Context context, String permission) {
    return context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
  }

  public static boolean isAppInBackground(Context context) {
    try {
      if (!context.getPackageName()
          .equalsIgnoreCase(((ActivityManager) context.getSystemService(
              Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity.getPackageName())) {
        return true;
      }
    } catch (IndexOutOfBoundsException e) {
//      Timber.e(TAG, "Running tasks empty! ", e);
    }
    return false;
  }

  public static boolean isICS() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
  }

  public static boolean isJellybean() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
  }

  public static boolean isKitkat() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
  }

  public static boolean isLollipop() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
  }

  public static boolean isEmpty(Collection collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean isEmpty(Map map) {
    return map == null || map.isEmpty();
  }

  public static boolean isEmpty(JSONArray array) {
    return array == null || array.length() == 0;
  }

  public static <T> List<T> mergeLists(List<T> list1, List<T> list2) {
    List<T> mergedList = Collections.emptyList();
    if (list1 != null || list2 != null) {
      mergedList = new ArrayList<>();
      if (list1 != null) {
        mergedList.addAll(list1);
      }
      if (list2 != null) {
        mergedList.addAll(list2);
      }
    }
    return mergedList;
  }

  public static float roundToNearestHalf(float f) {
    long intPart = (long) f;
    float fractionPart = f - (float) intPart;
    if (fractionPart <= 0.25f) {

    } else if (fractionPart > 0.25f && fractionPart <= 0.75f) {
      fractionPart = 0.5f;
    } else {
      fractionPart = 1f;
    }
    return intPart + fractionPart;
  }

  public static void restartApp(Context context) {
    ViewUtils.showShortToast(context, "Logging out...");
//    ProcessPhoenix.triggerRebirth(context);
  }

  public static String mapToString(Map map) {
    if (Utils.isEmpty(map)) {
      return null;
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Object key : map.keySet()) {
      stringBuilder.append(key);
      stringBuilder.append(":");
      stringBuilder.append(map.get(key));
      stringBuilder.append("\\|");
    }
    return stringBuilder.toString();
  }

  public static int parseHexColor(String hexCode, int defaultColorCode) {
    if (hexCode == null) {
      return defaultColorCode;
    }
    try {
      return Color.parseColor(hexCode);
    } catch (IllegalArgumentException ex) {
//      Timber.e("Invalid color hex code: " + hexCode);
      return defaultColorCode;
    }
  }

  public static String getPathFormUri(Context context, Uri uri) {
    String path = null;
    if (uri != null) {
      try {
        path = IntentUtils.getFilePath(context, uri);
      } catch (URISyntaxException e) {
//        Timber.e(e);
      }
    }
    return path;
  }
}
