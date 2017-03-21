package com.bozo.greendaolearn;

import android.app.Application;
import android.content.Context;

import com.bozo.greendaolearn.db.DbConstants;
import com.bozo.greendaolearn.greendao.DaoMaster;
import com.bozo.greendaolearn.greendao.DaoSession;

/**
 * Created by CJ on 2017/3/20 0020.
 */

public class GreenDaoApplication extends Application {

    private static DaoSession daoSession;
    private static DaoMaster daoMaster;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }

    /**
     * 获取DaoMaster
     * @return
     */
    public static DaoMaster getDaoMaster() {
        if (daoMaster == null){
            synchronized (GreenDaoApplication.class){
                if (daoMaster == null){
                    DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, DbConstants.DB_NAME, null);
                    daoMaster = new DaoMaster(helper.getWritableDb());
                }
            }
        }
        return daoMaster;
    }

    /**
     * 获取DaoSession
     * @return
     */
    public static DaoSession getDaoSession() {
        if (daoSession == null){
            synchronized (GreenDaoApplication.class){
                if (daoSession == null){
                    if (daoMaster == null){
                        daoMaster = getDaoMaster();
                    }
                    daoSession = daoMaster.newSession();
                }
            }
        }
        return daoSession;
    }
}
