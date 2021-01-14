package a.droid.libx.core;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadX {
    private enum Type {
        UI, IO, NEW
    }

    private final Type type;

    private static final Handler handler = new Handler(Looper.getMainLooper());
    private static final ScheduledExecutorService ioThread = Executors.newSingleThreadScheduledExecutor();
    private ScheduledExecutorService newThread;

    private ThreadX(Type type) {
        this.type = type;
        if (type == Type.NEW) {
            newThread = Executors.newSingleThreadScheduledExecutor();
        }
    }

    public static ThreadX ui() {
        return new ThreadX(Type.UI);
    }

    public static ThreadX io() {
        return new ThreadX(Type.IO);
    }

    public static ThreadX create() {
        return new ThreadX(Type.NEW);
    }

    public void post(Runnable runnable) {
        if (type == Type.UI) {
            handler.post(runnable);
        } else if (type == Type.IO) {
            ioThread.execute(runnable);
        } else if (type == Type.NEW) {
            newThread.execute(runnable);
        }
    }

    public void postDelayed(Runnable runnable, long delayMillis) {
        if (type == Type.UI) {
            handler.postDelayed(runnable, delayMillis);
        } else if (type == Type.IO) {
            ioThread.schedule(runnable, delayMillis, TimeUnit.MILLISECONDS);
        } else if (type == Type.NEW) {
            newThread.schedule(runnable, delayMillis, TimeUnit.MILLISECONDS);
        }
    }
}
