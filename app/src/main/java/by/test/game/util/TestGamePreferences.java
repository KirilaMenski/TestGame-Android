package by.test.game.util;

import static android.content.Context.MODE_PRIVATE;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kirila on 27.2.17.
 */
public class TestGamePreferences {


    private static SharedPreferences mPreference;
    private static TestGamePreferences mInstance;

    WeakReference<Context> mContext;

    public TestGamePreferences(Context context) {
        this.mContext = new WeakReference<>(context);
        this.mPreference = getPrefs(mContext.get());
    }

    public static SharedPreferences getPrefs(Context ctx) {
        if (mPreference == null) {
            return mPreference = ctx.getSharedPreferences("Prefs", MODE_PRIVATE);
        } else {
            return mPreference;
        }
    }

    public static void init(Context context) {
        if (mInstance == null) {
            synchronized (TestGamePreferences.class) {
                if (mInstance == null) {
                    mInstance = new TestGamePreferences(context);
                }
            }
        }
    }
}
