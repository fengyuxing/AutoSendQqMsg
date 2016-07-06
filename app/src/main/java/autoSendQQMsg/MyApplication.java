package autoSendQQMsg;

import android.app.Application;


/**
 * Application类，负责保存一些全局常量以及做一些初始化
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();

    }

    private void init() {
        CrashHandler.getInstance().init(this);
    }


}
