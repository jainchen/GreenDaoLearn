package com.bozo.greendaolearn.db;

import com.bozo.greendaolearn.entities.MaterialEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Author: ChenJing
 * Date: 2017-03-20 下午 3:33
 * Version: 1.0
 */

public interface IMaterial {

    /**
     * 添加一条材料购买记录
     * @param material
     * @return
     */
    Observable<Long> addRecord(MaterialEntity material);

    /**
     * 获取购买的材料
     * @return
     */
    Observable<List<MaterialEntity>> getMaterials();

    /**
     * 清空记录
     */
    Observable<Boolean> clearRecord();
}