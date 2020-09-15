package app.shareparking.com.spapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.MailTo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import androidx.appcompat.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by arpitanand on 23/06/17.
 */
public class IntentUtils {

  //public static final String PROVIDER = BuildConfig.AUTHORITY;
  public static final String PROVIDER = "";

  public static void openDialer(Context context, String numberUri) {
    if (!numberUri.startsWith("tel:")) {
      numberUri = "tel:" + numberUri;
    }
    Intent intent = new Intent(Intent.ACTION_DIAL);
    intent.setData(Uri.parse(numberUri));
    context.startActivity(intent);
  }

  public static void openEmail(Context context, String emailUri) {
    if (!emailUri.startsWith("mailto:")) {
      emailUri = "mailto:" + emailUri;
    }
    MailTo mt = MailTo.parse(emailUri);
    Intent mail = new Intent(Intent.ACTION_SEND);
    mail.setType("application/octet-stream");
    mail.putExtra(Intent.EXTRA_EMAIL, new String[] { mt.getTo() });
    mail.putExtra(Intent.EXTRA_SUBJECT, mt.getSubject());
    mail.putExtra(Intent.EXTRA_TEXT, mt.getBody());
    context.startActivity(mail);
  }

  public static void chooseFile(Activity activity, int requestCode) {
    chooseFile(activity, requestCode, true);
  }

  public static void chooseFile(Activity activity, int requestCode, boolean allowMultiple) {
    Intent intent = new Intent();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      // For Android versions of KitKat or later, we use a different intent to ensure we can get the file path from the returned intent URI
      intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
      intent.setType("*/*");
    } else {
      intent.setAction(Intent.ACTION_GET_CONTENT);
      intent.setType("file/*");
    }
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, allowMultiple);
    activity.startActivityForResult(intent, requestCode);
  }

  public static void chooseImage(Activity activity, int requestCode) {
    chooseImage(activity, requestCode, true);
  }

  public static void chooseImage(Activity activity, int requestCode, boolean allowMultiple) {
    Intent intent = new Intent();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      // For Android versions of KitKat or later, we use a different intent to ensure we can get the file path from the returned intent URI
      intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
      intent.addCategory(Intent.CATEGORY_OPENABLE);
      intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
    } else {
      intent.setAction(Intent.ACTION_GET_CONTENT);
    }

    intent.setType("image/*");
    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, allowMultiple);
    activity.startActivityForResult(intent, requestCode);
  }

  public static String captureImage(Activity baseActivity, int requestCode) {
    String path = null;
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(baseActivity.getPackageManager()) != null) {
      // Create the File where the photo should go
      File photoFile = null;
      try {
        Pair<File, String> imageFile = createImageFile(baseActivity);
        photoFile = imageFile.first;
        path = imageFile.second;
      } catch (IOException ex) {
        // Error occurred while creating the File
        //Timber.w("Failed to create iamge file.", ex);
      }
      // Continue only if the File was successfully created
      if (photoFile != null) {
        Uri photoURI = FileProvider.getUriForFile(baseActivity, PROVIDER, photoFile);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        baseActivity.startActivityForResult(takePictureIntent, requestCode);
      }
    }
    return path;
  }

  private static Pair<File, String> createImageFile(Activity baseActivity) throws IOException {
    // Create an image file name
    String timeStamp =
        new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String imageFileName = "OFB_" + timeStamp + "_";
    File storageDir = baseActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    File image = File.createTempFile(imageFileName,  /* prefix */
        ".jpg",         /* suffix */
        storageDir      /* directory */);

    // Save a file: path for use with ACTION_VIEW intents
    //String path = "file:" + image.getAbsolutePath();
    String path = image.getAbsolutePath();
    return new Pair<>(image, path);
  }

  public static boolean isExternalStorageDocument(Uri uri) {
    return "com.android.externalstorage.documents".equals(uri.getAuthority());
  }

  public static boolean isDownloadsDocument(Uri uri) {
    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
  }

  public static boolean isMediaDocument(Uri uri) {
    return "com.android.providers.media.documents".equals(uri.getAuthority());
  }

  /**
   * Gets the file path of the given Uri.
   */
  @SuppressLint("NewApi") public static String getFilePath(Context context, Uri uri)
      throws URISyntaxException {
    final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
    String selection = null;
    String[] selectionArgs = null;
    // Uri is different in versions after KITKAT (Android 4.4), we need to
    // deal with different Uris.
    if (needToCheckUri && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
      if (isExternalStorageDocument(uri)) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        return Environment.getExternalStorageDirectory() + "/" + split[1];
      } else if (isDownloadsDocument(uri)) {
        final String id = DocumentsContract.getDocumentId(uri);
        uri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
            Long.parseLong(id));
      } else if (isMediaDocument(uri)) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type = split[0];
        if ("image".equals(type)) {
          uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if ("video".equals(type)) {
          uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if ("audio".equals(type)) {
          uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        selection = "_id=?";
        selectionArgs = new String[] {
            split[1]
        };
      }
    }
    if ("content".equalsIgnoreCase(uri.getScheme())) {
      String[] projection = {
          MediaStore.Images.Media.DATA
      };
      Cursor cursor = null;
      try {
        cursor =
            context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
        if (cursor != null) {
          int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
          if (cursor.moveToFirst()) {
            if (!TextUtils.isEmpty(cursor.getString(column_index))) {
              return cursor.getString(column_index);
            } else {
              return getImageUrlWithAuthority(context, uri);
            }
          }
        }
      } catch (Exception e) {
        // Ignore
      } finally {
        if (cursor != null) {
          cursor.close();
        }
      }
    } else if ("file".equalsIgnoreCase(uri.getScheme())) {
      return uri.getPath();
    }
    return null;
  }

  public static String getImageUrlWithAuthority(Context context, Uri uri) {
    InputStream is = null;
    String path = context.getCacheDir().toString();
    String timeStamp =
        new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
    String imageFileName = "OFB_" + timeStamp;
    File file = new File(path, imageFileName + ".jpg");
    OutputStream fOut = null;
    if (uri.getAuthority() != null) {
      try {
        is = context.getContentResolver().openInputStream(uri);
        Bitmap bmp = BitmapFactory.decodeStream(is);
        fOut = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, fOut);
        return file.getPath();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        try {
          is.close();
          fOut.flush();
          fOut.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  public static void shareFile(Context context, Uri uri, String subject, String text) {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_SEND);
    intent.setType(getFileExtension(uri));
    intent.putExtra(Intent.EXTRA_STREAM, uri);
    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    intent.putExtra(Intent.EXTRA_TEXT, text);
    context.startActivity(Intent.createChooser(intent, "Share File Using"));
  }

  public static void shareText(Context context, String subject, String text) {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
    sendIntent.putExtra(Intent.EXTRA_TEXT, text);
    sendIntent.setType("text/plain");
    context.startActivity(
        Intent.createChooser(sendIntent, "Share Template"));
  }

  public static void openFile(Context context, String filePath) {
    Uri uri = FileProvider.getUriForFile(context, PROVIDER, new File(filePath));
    String extension = getFileExtension(uri);
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setDataAndTypeAndNormalize(uri, extension);
    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
    try {
      context.startActivity(intent);
    } catch (ActivityNotFoundException ex) {
      if (StringUtils.isEmpty(extension)) {
        ViewUtils.showLongToast(context, "No application found to open file");
      } else {
        ViewUtils.showLongToast(context, "No application found to open file of type " + extension);
      }
    }
  }

  /**
   * Should not be used with image files (.png , .jpg etc)
   */
  private static String getFileExtension(Uri uri) {
    String fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString());
    fileExtension = fileExtension.toLowerCase();
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension);
  }

  public static void openFiles(Context context, View anchorView, List<String> filePaths) {
    List<String> displayFileName = new ArrayList<>();
    for (String filePath : filePaths) {
      File file = new File(filePath);
      displayFileName.add(file.getName());
    }

    ListPopupWindow popupWindow = new ListPopupWindow(context);
    popupWindow.setAdapter(
        new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, displayFileName));
    popupWindow.setOnItemClickListener(
        (parent, view, position, id) -> openFile(context, filePaths.get(position)));
    popupWindow.setModal(true);
    popupWindow.setAnchorView(anchorView);
    popupWindow.show();
  }

  public static okhttp3.MediaType getMediaType(Context context, Uri uri) {
    String mimeType;
    if (uri.getScheme() != null && uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
      ContentResolver cr = context.getContentResolver();
      mimeType = cr.getType(uri);
    } else {
      mimeType = getFileExtension(uri);
    }
    return okhttp3.MediaType.parse(mimeType);
  }
}
