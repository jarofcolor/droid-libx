package real.droid.libx.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

public class SharedPreferencesX {

    public interface ISession {
        void onSession(SharedPreferences.Editor editor);
    }


    private final SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private SharedPreferencesX(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public static SharedPreferencesX on(Context context, String name) {
        SharedPreferences spf = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return new SharedPreferencesX(spf);
    }

    public static SharedPreferencesX on(Context context, String name, int mode) {
        SharedPreferences spf = context.getSharedPreferences(name, mode);
        return new SharedPreferencesX(spf);
    }

    public static SharedPreferencesX on(SharedPreferences spf) {
        return new SharedPreferencesX(spf);
    }

    public SharedPreferencesX commit(ISession session) {
        return commit(false, session);
    }

    public SharedPreferencesX commit(boolean isApply, ISession session) {
        if (editor == null)
            editor = preferences.edit();
        session.onSession(editor);
        if (isApply)
            return apply();
        else
            return this;
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesX clear() {
        if (editor == null) {
            editor = preferences.edit();
        }
        editor.clear();
        return this;
    }

    public SharedPreferencesX apply() {
        if (editor != null) {
            editor.apply();
            editor = null;
        }
        return this;
    }

    @SuppressLint("CommitPrefEdits")
    public SharedPreferencesX put(String key, Object value) {
        if (editor == null)
            editor = preferences.edit();
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Set) {
            Type genType = value.getClass().getGenericSuperclass();
            if (genType instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
                if (types.length > 0 && types[0] == String.class) {
                    editor.putStringSet(key, (Set<String>) value);
                }
            }

        }
        return this;
    }

    public <T> T get(String key) {
        return (T) preferences.getAll().get(key);
    }

}
