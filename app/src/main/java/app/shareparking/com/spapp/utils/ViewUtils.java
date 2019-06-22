package app.shareparking.com.spapp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Arpit Anand
 */
public final class ViewUtils {

  private static Toast sToast;

  private ViewUtils() {

  }

  public static void showShortToast(final Context context, final String message) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      toast(context, message, Toast.LENGTH_SHORT);
    } else {
      new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {
          toast(context, message, Toast.LENGTH_SHORT);
        }
      });
    }
  }

  private static void toast(Context context, String msg, int length) {
    if (sToast != null) {
      sToast.cancel();
    }
    sToast = Toast.makeText(context.getApplicationContext(), msg, length);
    sToast.show();
  }

  public static void showLongToast(final Context context, final String message) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      toast(context, message, Toast.LENGTH_LONG);
    } else {
      new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {
          toast(context, message, Toast.LENGTH_LONG);
        }
      });
    }
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static void setElevation(View view, float pixels) {
    if (Utils.isLollipop()) {
      view.setElevation(pixels);
    }
  }

  public static void removeView(View view) {
    ViewGroup parent = (ViewGroup) view.getParent();
    if (parent != null) {
      parent.removeView(view);
    }
  }

  public static void replaceView(View current, View replacement) {
    replaceView(current, replacement, false);
  }

  public static void replaceView(View current, View replacement, boolean retainLayoutParams) {
    ViewGroup parent = (ViewGroup) current.getParent();
    if (parent != null) {
      int index = parent.indexOfChild(current);
      if (retainLayoutParams) {
        replacement.setLayoutParams(current.getLayoutParams());
      }
      parent.removeView(current);
      parent.addView(replacement, index);
    }
  }

  public static void setEnabled(boolean enabled, View... views) {
    if (views != null) {
      for (View view : views) {
        view.setEnabled(enabled);
      }
    }
  }

  public static void setVisible(int visibility, View... views) {
    if (views != null) {
      for (View view : views) {
        view.setVisibility(visibility);
      }
    }
  }

  /*
   * If the data is empty, the view is hidden
   */
  public static void setupTextView(TextView tv, CharSequence chars) {
    if (TextUtils.isEmpty(chars)) {
      tv.setVisibility(View.GONE);
    } else {
      tv.setVisibility(View.VISIBLE);
      tv.setText(chars);
    }
  }

  public static int getColor(Context context, int colorResource) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      return context.getColor(colorResource);
    } else {
      return context.getResources().getColor(colorResource);
    }
  }

  public static void clearBackStack(FragmentManager fm) {
    int backStackCount = fm.getBackStackEntryCount();
    for (int i = 0; i < backStackCount; i++) {

      // Get the back stack fragment id.
      int backStackId = fm.getBackStackEntryAt(i).getId();

      fm.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  public static void hideKeyboard(Context context, IBinder viewWindowToken) {
    InputMethodManager imm =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(viewWindowToken, 0);
  }

  public static void showKeyboard(Context context, IBinder viewWindowToken) {
    InputMethodManager imm =
        (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.showSoftInputFromInputMethod(viewWindowToken, 0);
  }

  public static int getActionBarHeightInPx(Context context) {
    final TypedArray styledAttributes =
        context.getTheme().obtainStyledAttributes(new int[] {android.R.attr.actionBarSize});
    int actionBarSize = (int) styledAttributes.getDimension(0, 0);
    styledAttributes.recycle();
    return actionBarSize;
  }

  public static void showSnackbar(View v, String message, int color) {
    Snackbar snackbar = Snackbar.make(v, message, Snackbar.LENGTH_LONG);
    View snackbarView = snackbar.getView();
    snackbarView.setBackgroundColor(color);
    TextView textView= (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
    textView.setMaxLines(4);
    snackbar.show();
  }

  public static String getStringWithThumbsUp(String string) {
    return String.format("%s %s", string, getThumbsUpEmoji());
  }

  public static String getThumbsUpEmoji() {
    return new String(Character.toChars(0x1F44D));
  }

  public static void setupTextObservers(EditText editText, OnChangeListener onChangeListener) {
    editText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        onChangeListener.onChange();
      }
    });
  }

  public static void setupChooseObservers(AppCompatSpinner spinner, OnSowDropdownListener onSowDropdownListener) {
    spinner.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        onSowDropdownListener.onShowDropdown();
        return false;
      }
    });
  }

  public interface OnChangeListener {
    void onChange();
  }

  public interface OnSowDropdownListener{
    void onShowDropdown();
  }
}
