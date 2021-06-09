package real.droid.libx.core;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NavigatorX {
    public interface INavigation {
        <T> void onResult(T result);
    }

    public interface ICanPop {
        boolean canPop();
    }

    public static class Pair<F, S> {
        public F first;
        public S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

    private static HashMap<WeakReference<FragmentManager>, NavigatorX> map = new HashMap<>();

    private NavigatorX(WeakReference<FragmentManager> managerRef) {
        this.managerRef = managerRef;
    }

    private final WeakReference<FragmentManager> managerRef;
    private int viewId;
    private ArrayList<Pair<Fragment, INavigation>> array = new ArrayList<>();

    public static NavigatorX on(Fragment fragment) {
        return on(fragment.getChildFragmentManager());
    }

    public static NavigatorX on(Activity activity) {
        return on(activity.getFragmentManager());
    }

    private synchronized static NavigatorX on(FragmentManager manager) {
        NavigatorX navigatorX = null;
        for (Map.Entry<WeakReference<FragmentManager>, NavigatorX> entry : map.entrySet()) {
            if (entry.getKey().get() == manager) {
                navigatorX = entry.getValue();
                break;
            }
        }
        if (navigatorX == null) {
            WeakReference<FragmentManager> ref = new WeakReference<>(manager);
            navigatorX = new NavigatorX(ref);

            map.put(ref, navigatorX);
        }

        return navigatorX;
    }

    public NavigatorX container(int resId) {
        if (viewId <= 0) {
            viewId = resId;
        }
        return this;
    }

    public void push(Fragment fragment) {
        this.push(fragment, null);
    }

    public synchronized void push(Fragment fragment, INavigation result) {
        FragmentManager manager = managerRef.get();
        if (manager != null) {
            FragmentTransaction transaction = manager.
                    beginTransaction();
            boolean isContains = false;
            int index = 0;
            for (Pair<Fragment, INavigation> pair : array) {
                if (pair.first == fragment) {
                    isContains = true;
                    if (result != null) {
                        pair.second = result;
                    }
                    break;
                }
                index++;
            }
            if (!isContains) {
                transaction.add(viewId, fragment);
            }
            ArrayList<Pair<Fragment, INavigation>> temp = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                if (i <= index) {
                    temp.add(array.get(i));
                } else {
                    transaction.remove(array.get(i).first);
                }
            }
            array = temp;
            if (!isContains) {
                array.add(new Pair<>(fragment, result));
            }
            transaction.show(fragment).commit();
        }
    }

    public boolean pop() {
        return this.pop(null);
    }

    public synchronized <T> boolean pop(T result) {
        FragmentManager manager = managerRef.get();
        if (manager != null) {
            synchronized (this) {
                if (array.size() > 1) {
                    Pair<Fragment, INavigation> pair = array.remove(array.size() - 1);
                    if (pair.first instanceof ICanPop) {
                        ICanPop canPop = (ICanPop) pair.first;
                        if (canPop.canPop()) {
                            return true;
                        }
                    }
                    NavigatorX.INavigation navigation = pair.second;
                    if (navigation != null && result != null) {
                        navigation.onResult(result);
                    }
                    manager.beginTransaction()
                            .remove(pair.first)
                            .show(array.get(array.size() - 1).first)
                            .commit();
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized void destroy() {
        synchronized (NavigatorX.class) {
            map.remove(managerRef);
        }
        managerRef.clear();
    }
}
