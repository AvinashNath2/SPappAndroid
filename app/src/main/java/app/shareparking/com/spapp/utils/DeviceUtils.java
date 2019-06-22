package app.shareparking.com.spapp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.Locale;
import java.util.UUID;

public final class DeviceUtils {

  private static final String LOG_TAG = "DEVICE_UTILS";

  private static float displayDensity = -1;

  private DeviceUtils() {
  }

  public static String getAndroidId(Context context) {
    String deviceId =
        Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    if (TextUtils.isEmpty(deviceId)) {
      // TODO
    }
    return deviceId;
  }

  private static String getNewUDID(Context context) {

    // Android ID
    // Issues on 2.2, some phones have same Android ID due to manufacturer
    // error
    String androidId =
        Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    // @see
    // http://stackoverflow.com/questions/6106681/android-how-are-you-dealing-with-9774d56d682e549c-android-id
    if (!(TextUtils.isEmpty(androidId) || androidId.equals("9774d56d682e549c"))) {
      return androidId;
    }

    // Serial number
    // Guaranteed to be on all non phones in 2.3+
    try {
      String serialNumber = (String) Build.class.getField("SERIAL").get(null);
      if (!TextUtils.isEmpty(serialNumber)) {
        return serialNumber;
      }
    } catch (Exception e) {
    }

    // Telephony ID
    // Guaranteed to be on all phones, requires READ_PHONE_STATE permission
    if (Utils.permissionGranted(context, Permission.READ_PHONE_STATE) && context.getPackageManager()
        .hasSystemFeature("android.hardware.telephony")) {
      TelephonyManager telephone =
          ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
      @SuppressLint("MissingPermission") String telephonyId = telephone.getDeviceId();
      if (!TextUtils.isEmpty(telephonyId)) {
        return telephonyId;
      }
    }

    // Telephony ID
    // Guaranteed to be on all phones, requires READ_PHONE_STATE permission
    String telephonyId = getTelephonyId(context);
    if (!TextUtils.isEmpty(telephonyId)) {
      return telephonyId;
    }

    // If this still fails, generate random identifier that does not persist
    // across installations
    String randomId = UUID.randomUUID().toString();
    return randomId;
  }

  /**
   * Get the IMEI for GSM and the MEID or ESN for CDMA phones. Return empty
   * string if device ID is not available.
   * <p/>
   * <p/>
   * Requires Permission: {@link android.Manifest.permission#READ_PHONE_STATE
   * READ_PHONE_STATE}
   */
  @SuppressLint("MissingPermission")
  public static String getTelephonyId(Context context) {
    String telephonyId = null;
    if (Utils.permissionGranted(context, Permission.READ_PHONE_STATE) && context.getPackageManager()
        .hasSystemFeature("android.hardware.telephony")) {
      TelephonyManager telephone =
          ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
      telephonyId = telephone.getDeviceId();
    }
    if (telephonyId == null) {
      telephonyId = "";
    }
    return telephonyId;
  }

  public static String getOS() {
    return "Android";
  }

  public static String getOSVersionString() {
    return Build.VERSION.RELEASE;
  }

  public static int getOSVersionInt() {
    return Build.VERSION.SDK_INT;
  }

  public static String getDevice() {
    return Build.MODEL;
  }

  public static String getDeviceManufacturer() {
    return Build.MANUFACTURER;
  }

  public static String getResolution(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    DisplayMetrics metrics = new DisplayMetrics();
    display.getMetrics(metrics);

    return metrics.widthPixels + "x" + metrics.heightPixels;
  }

  public static String getCarrier(Context context) {
    try {
      TelephonyManager manager =
          (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
      return manager.getNetworkOperatorName();
    } catch (NullPointerException npe) {
//      Timber.w(LOG_TAG, "No carrier found");
    }
    return "";
  }

  public static String getLocale() {
    Locale locale = Locale.getDefault();
    return locale.getLanguage() + "_" + locale.getCountry();
  }

  public static String getAppVersionName(Context context) {
    String result = "1.0";
    try {
      result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    } catch (NameNotFoundException ex) {
//      Timber.w(LOG_TAG, "App version not found", ex);
    }

    return result;
  }

  public static int getAppVersion(Context context) {
    int version = Integer.MIN_VALUE;
    try {
      version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
    } catch (NameNotFoundException ex) {
//      Timber.w(LOG_TAG, "App version not found", ex);
    }

    return version;
  }

  public static float getDisplayDensity(Context context) {
    if (displayDensity < 0) {
      displayDensity = context.getResources().getDisplayMetrics().density;
    }
    return displayDensity;
  }

  private class Permission {
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
  }
}