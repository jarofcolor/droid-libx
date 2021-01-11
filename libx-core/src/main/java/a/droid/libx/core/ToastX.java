package a.droid.libx.core;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

public class ToastX {
    private final Toast toast;

    private ToastX(Toast toast) {
        this.toast = toast;
    }

    public static ToastX on(Context context, View view) {
        Toast toast = new Toast(context);
        toast.setView(view);
        return new ToastX(toast);
    }

    public static ToastX on(Context context, String msg) {
        return on(context, msg, false);
    }

    public static ToastX on(Context context, int resId) {
        return on(context, resId, false);
    }

    public static ToastX on(Context context, int resId, boolean isLong) {
        return on(context, context.getString(resId), isLong);
    }

    public static ToastX on(Context context, String msg, boolean isLong) {
        @SuppressLint("ShowToast") Toast toast = Toast.makeText(context, msg, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        return new ToastX(toast);
    }

    public ToastX gravity(int gravity, int offsetX, int offsetY) {
        toast.setGravity(gravity, offsetX, offsetY);
        return this;
    }

    public ToastX margin(float horizontalMargin, float verticalMargin) {
        toast.setMargin(horizontalMargin, verticalMargin);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.R)
    public ToastX addCallback(Toast.Callback callback) {
        toast.addCallback(callback);
        return this;
    }

    public ToastX show() {
        toast.show();
        return this;
    }

    public ToastX cancel() {
        toast.cancel();
        return this;
    }

}
