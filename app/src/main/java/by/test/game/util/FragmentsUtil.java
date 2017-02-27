package by.test.game.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by kirila on 27.2.17.
 */

public class FragmentsUtil {

    public static void replaceFragment(FragmentActivity activity, int containerId, Fragment fragment, boolean addToBackStack) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(containerId, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    static public void replaceAnimFragment(FragmentActivity activity, int containerId, Fragment fragment, boolean addToBackStack, int animStart, int animEnd) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(animStart, animEnd);
        ft.replace(containerId, fragment);
        if (addToBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    public static void clearBackStack(FragmentActivity activity, int count) {
        FragmentManager manager = activity.getSupportFragmentManager();
        for (int i = 0; i < count; i++) {
            manager.popBackStack();
        }
    }

}
