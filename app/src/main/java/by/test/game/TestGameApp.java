package by.test.game;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import by.test.game.util.TestGamePreferences;

/**
 * Created by kirila on 27.2.17.
 */

public class TestGameApp extends MultiDexApplication {

    private static Context sContext;

    public void onCreate() {
        super.onCreate();
        sContext = this;
        TestGamePreferences.init(sContext);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getAppContext() {
        return sContext;
    }

}
