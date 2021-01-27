package real.droid.libx.core;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

public class BundleX {
    private final Bundle bundle;

    public BundleX() {
        bundle = new Bundle();
    }

    public BundleX putByte(String key, byte value) {
        bundle.putByte(key, value);
        return this;
    }


    public BundleX putChar(String key, char value) {
        bundle.putChar(key, value);
        return this;
    }


    public BundleX putShort(String key, short value) {
        bundle.putShort(key, value);
        return this;
    }


    public BundleX putFloat(String key, float value) {
        bundle.putFloat(key, value);
        return this;
    }


    public BundleX putCharSequence(String key, CharSequence value) {
        bundle.putCharSequence(key, value);
        return this;
    }


    public BundleX putParcelable(String key, Parcelable value) {
        bundle.putParcelable(key, value);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BundleX putSize(String key, Size value) {
        bundle.putSize(key, value);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BundleX putSizeF(String key, SizeF value) {
        bundle.putSizeF(key, value);
        return this;
    }


    public BundleX putParcelableArray(String key, Parcelable[] value) {
        bundle.putParcelableArray(key, value);
        return this;
    }


    public BundleX putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
        bundle.putParcelableArrayList(key, value);
        return this;
    }


    public BundleX putSparseParcelableArray(String key, SparseArray<? extends Parcelable> value) {
        bundle.putSparseParcelableArray(key, value);
        return this;
    }


    public BundleX putIntegerArrayList(String key, ArrayList<Integer> value) {
        bundle.putIntegerArrayList(key, value);
        return this;
    }


    public BundleX putStringArrayList(String key, ArrayList<String> value) {
        bundle.putStringArrayList(key, value);
        return this;
    }


    public BundleX putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
        bundle.putCharSequenceArrayList(key, value);
        return this;
    }


    public BundleX putSerializable(String key, Serializable value) {
        bundle.putSerializable(key, value);
        return this;
    }


    public BundleX putByteArray(String key, byte[] value) {
        bundle.putByteArray(key, value);
        return this;
    }


    public BundleX putShortArray(String key, short[] value) {
        bundle.putShortArray(key, value);
        return this;
    }


    public BundleX putCharArray(String key, char[] value) {
        bundle.putCharArray(key, value);
        return this;
    }


    public BundleX putFloatArray(String key, float[] value) {
        bundle.putFloatArray(key, value);
        return this;
    }


    public BundleX putCharSequenceArray(String key, CharSequence[] value) {
        bundle.putCharSequenceArray(key, value);
        return this;
    }


    public BundleX putBundle(String key, Bundle value) {
        bundle.putBundle(key, value);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public BundleX putBinder(String key, IBinder value) {
        bundle.putBinder(key, value);
        return this;
    }


    public BundleX putBoolean(String key, boolean value) {
        bundle.putBoolean(key, value);
        return this;
    }


    public BundleX putInt(String key, int value) {
        bundle.putInt(key, value);
        return this;
    }


    public BundleX putLong(String key, long value) {
        bundle.putLong(key, value);
        return this;
    }


    public BundleX putDouble(String key, double value) {
        bundle.putDouble(key, value);
        return this;
    }


    public BundleX putString(String key, String value) {
        bundle.putString(key, value);
        return this;
    }


    public BundleX putBooleanArray(String key, boolean[] value) {
        bundle.putBooleanArray(key, value);
        return this;
    }


    public BundleX putIntArray(String key, int[] value) {
        bundle.putIntArray(key, value);
        return this;
    }


    public BundleX putLongArray(String key, long[] value) {
        bundle.putLongArray(key, value);
        return this;
    }


    public BundleX putDoubleArray(String key, double[] value) {
        bundle.putDoubleArray(key, value);
        return this;
    }


    public BundleX putStringArray(String key, String[] value) {
        bundle.putStringArray(key, value);
        return this;
    }

    public Bundle getBundle() {
        return bundle;
    }
}
