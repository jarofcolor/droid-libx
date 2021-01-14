package a.droid.libx.core;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadX {
    private enum Type {
        UI, IO, NEW
    }

    private final Type type;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final ScheduledExecutorService ioThread = Executors.newSingleThreadScheduledExecutor();

    private ThreadX(Type type) {
        this.type = type;
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
            new Thread(runnable).start();
        }
    }

    public void postDelayed(Runnable runnable, long delayMillis) {
        if (type == Type.UI) {
            handler.postDelayed(runnable, delayMillis);
        } else if (type == Type.IO) {
            ioThread.schedule(runnable, delayMillis, TimeUnit.MILLISECONDS);
        } else if (type == Type.NEW) {
            new Thread(() -> {
                try {
                    Thread.sleep(delayMillis);
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
