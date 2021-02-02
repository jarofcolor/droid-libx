package real.droid.libx.core;

import android.app.Fragment;

public class FragmentX {
    public static FragmentX on(Class<? extends Fragment> clazz) throws InstantiationException, IllegalAccessException {
        return new FragmentX(clazz.newInstance());
    }

    public static FragmentX on(Fragment fragment) {
        return new FragmentX(fragment);
    }

    private final Fragment fragment;
    private final BundleX bundleX = new BundleX();

    private FragmentX(Fragment fragment) {
        this.fragment = fragment;
        this.fragment.setArguments(this.bundleX.getBundle());
    }

    public FragmentX addArg(String key, Object value) {
        bundleX.putObject(key, value);
        return this;
    }

    public FragmentX addAll(BundleX bundleX) {
        bundleX.putAll(bundleX.getBundle());
        return this;
    }

    public Fragment fragment() {
        return fragment;
    }
}
