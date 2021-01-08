package a.droid.libx.core;


import android.content.Context;
import android.widget.Toast;

public class ToastX {
    public static void toast(Context context, String msg) {
        toast(context, msg, false);
    }

    public static void toast(Context context, String msg, boolean isLong) {
        Toast.makeText(context, msg, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
