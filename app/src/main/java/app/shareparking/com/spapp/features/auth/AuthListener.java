package app.shareparking.com.spapp.features.auth;

public interface AuthListener {
    void onStarted();
    void onSuccess();
    void onFailed(String message);
}
