package com.bozo.greendaolearn.db.impl;

import com.bozo.greendaolearn.GreenDaoApplication;
import com.bozo.greendaolearn.db.IMaterial;
import com.bozo.greendaolearn.entities.MaterialEntity;
import com.bozo.greendaolearn.greendao.DaoSession;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

/**
 * Author: ChenJing
 * Date: 2017-03-20 下午 3:36
 * Version: 1.0
 */
//
public class MaterialImpl implements IMaterial {

    DaoSession daoSession;

    public MaterialImpl() {
        daoSession = GreenDaoApplication.getDaoSession();
    }

    @Override
    public Observable<Long> addRecord(final MaterialEntity material) {
        return Observable.fromCallable(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return daoSession.getMaterialEntityDao().insert(material);
            }
        });
    }

    @Override
    public Observable<List<MaterialEntity>> getMaterials() {
        return Observable.fromCallable(new Callable<List<MaterialEntity>>() {
            @Override
            public List<MaterialEntity> call() throws Exception {
                return daoSession.getMaterialEntityDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> clearRecord() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                daoSession.getMaterialEntityDao().deleteAll();
                return true;
            }
        });
    }
}
