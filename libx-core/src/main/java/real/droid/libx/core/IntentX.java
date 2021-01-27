package real.droid.libx.core;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import java.util.Set;
import java.util.concurrent.Executor;

public class IntentX {
    private Intent intent;
    private Context context;

    private IntentX(Context context, Intent intent) {
        this.intent = intent;
        this.context = context;
    }

    public static IntentX on(Context context) {
        Intent intent = new Intent();
        return new IntentX(context, intent);
    }

    public static IntentX on(Context context, Intent intent) {
        return new IntentX(context, intent);
    }

    public static IntentX on(Context context, String action) {
        Intent intent = new Intent(action);
        return new IntentX(context, intent);
    }

    public static IntentX on(Context context, String action, Uri uri) {
        Intent intent = new Intent(action, uri);
        return new IntentX(context, intent);
    }

    public static IntentX on(Context context, Class<?> clz) {
        Intent intent = new Intent(context, clz);
        return new IntentX(context, intent);
    }

    public IntentX className(String pkgName, String className) {
        intent.setClassName(pkgName, className);
        return this;
    }

    public IntentX className(String pkgName, Class<?> clz) {
        intent.setClassName(pkgName, clz.getName());
        return this;
    }

    public IntentX action(String action) {
        intent.setAction(action);
        return this;
    }

    public IntentX component(ComponentName componentName) {
        intent.setComponent(componentName);
        return this;
    }

    public IntentX addFlag(int flag) {
        intent.addFlags(flag);
        return this;
    }

    public IntentX flags(int flags) {
        intent.setFlags(flags);
        return this;
    }

    public IntentX addCategory(String category) {
        intent.addCategory(category);
        return this;
    }

    public IntentX category(String... categories) {
        Set<String> sets = intent.getCategories();
        if (sets != null && sets.size() > 0) {
            sets.clear();
        }
        for (String category : categories) {
            intent.addCategory(category);
        }
        return this;
    }

    public IntentX extras(Bundle extras) {
        intent.putExtras(extras);
        return this;
    }

    public IntentX extras(BundleX extras) {
        intent.putExtras(extras.getBundle());
        return this;
    }

    public Intent getIntent() {
        return intent;
    }

    public void startActivity() {
        context.startActivity(intent);
    }

    public void startActivityForResult(int requestCode) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;

            activity.startActivityForResult(intent, requestCode);
        }
    }

    public ComponentName startService() {
        return context.startService(intent);
    }

    @TargetApi(Build.VERSION_CODES.Q)
    public ComponentName startForegroundService() {
        return context.startForegroundService(intent);
    }

    public boolean bindService(ServiceConnection connection, int flags) {
        return context.bindService(intent, connection, flags);
    }

    @TargetApi(Build.VERSION_CODES.Q)
    public boolean bindService(ServiceConnection connection, int flags, Executor executor) {
        return context.bindService(intent, flags, executor, connection);
    }

    public void sendBroadcast() {
        context.sendBroadcast(intent);
    }

    public void sendBroadcast(String receiverPermission) {
        context.sendBroadcast(intent, receiverPermission);
    }

    @TargetApi(Build.VERSION_CODES.R)
    public void sendBroadcast(String[] receiverPermissions) {
        context.sendBroadcastWithMultiplePermissions(intent, receiverPermissions);
    }

    public void sendOrderedBroadcast(String receiverPermission) {
        context.sendOrderedBroadcast(intent, receiverPermission);
    }

    public void sendOrderedBroadcast(String receiverPermission, BroadcastReceiver resultReceiver,
                                     Handler scheduler, int initialCode, String initialData,
                                     Bundle initialExtras) {
        context.sendOrderedBroadcast(intent, receiverPermission, resultReceiver, scheduler, initialCode, initialData, initialExtras);
    }
}
