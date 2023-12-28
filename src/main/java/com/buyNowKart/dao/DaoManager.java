package com.buyNowKart.dao;

import com.buyNowKart.entities.Category;

public class DaoManager {
    private static final CategoryDao categoryDao = new CategoryDao();

    static {
        Category.initialize();
    }
    public static CategoryDao categoryDao(){
        return categoryDao;
    }

}
