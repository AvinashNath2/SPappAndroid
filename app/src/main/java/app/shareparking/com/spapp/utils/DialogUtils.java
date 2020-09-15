package app.shareparking.com.spapp.utils;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import app.shareparking.com.spapp.R;

public class DialogUtils {

  private DialogUtils() {
  }

  public static void getQuotesDialog(Context context, String title, String okButtonName, String cancleButtonName,
                                     DialogInterface.OnClickListener positiveClickListener,
                                     DialogInterface.OnClickListener negativeClickListener) {
    AlertDialog alertDialog =
        new AlertDialog.Builder(context)
            .setView(R.layout.view_dialog_box)
            .create();
    alertDialog.show();
    alertDialog.setCanceledOnTouchOutside(false);
    alertDialog.setCancelable(false);

    AppCompatTextView titleTv =
        (AppCompatTextView) alertDialog.findViewById(R.id.title_popup);
    AppCompatButton cancelActionBtn =
        (AppCompatButton) alertDialog.findViewById(R.id.action_cancle);
    AppCompatButton okActionBtn =
        (AppCompatButton) alertDialog.findViewById(R.id.action_ok);

    titleTv.setText(title);
    cancelActionBtn.setText(cancleButtonName);
    okActionBtn.setText(okButtonName);

    okActionBtn.setOnClickListener(v -> {
      alertDialog.dismiss();
      positiveClickListener.onClick(alertDialog, 2);
    });
    cancelActionBtn.setOnClickListener(v -> {
      alertDialog.dismiss();
      negativeClickListener.onClick(alertDialog, 1);
    });
  }

}