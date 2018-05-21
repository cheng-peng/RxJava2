package com.cxp.rxjava2.utils;


import com.cxp.rxjava2.model.FoodList;

/**
 * 缓存管理器
 */

public class CacheManager {
    private static CacheManager instance;
    private FoodList footListJson;

    private CacheManager(){}

    public static CacheManager getInstance(){
        if (instance == null){
            instance = new CacheManager();
        }
        return instance;
    }

    public FoodList getFoodListData(){
        return this.footListJson;
    }

    public void setFoodListData(FoodList data){
        this.footListJson = data;
    }
}
