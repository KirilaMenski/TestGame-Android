package by.test.game.util;

import static android.content.Context.MODE_PRIVATE;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kirila on 27.2.17.
 */
public class TestGamePreferences {


    private static SharedPreferences mPreference;
    private static TestGamePreferences mInstance;

    private static final String CURRENT_LEVEL = "current_level";
    private static final String SCORES_LIST = "scores_list";
    private static final int DEFAULT_LVL = 1;

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

    public static void saveLevel(int lvl) {
        SharedPreferences.Editor ed = mPreference.edit();
        ed.putInt(CURRENT_LEVEL, lvl);
        ed.commit();
    }

    public static void removeLvl() {
        SharedPreferences.Editor ed = mPreference.edit();
        ed.putInt(CURRENT_LEVEL, DEFAULT_LVL);
        ed.commit();
    }

    public static int getLvl() {
        return mPreference.getInt(CURRENT_LEVEL, DEFAULT_LVL);
    }

    public static void saveList(Set<String> list){
        SharedPreferences.Editor ed = mPreference.edit();
        ed.putStringSet(SCORES_LIST, list);
        ed.commit();
    }

    public static void removeList(){
        SharedPreferences.Editor ed = mPreference.edit();
        ed.putStringSet(SCORES_LIST, new HashSet<String>());
        ed.commit();
    }

    public static Set<String> getList(){
        return mPreference.getStringSet(SCORES_LIST, new HashSet<String>());
    }

}
