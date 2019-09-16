package com.example.exemplolistview.dao.Helper;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class DaoHelper<T> {
    protected Dao<T, Integer> dao;
    protected Class className;
    public static MyOrmLiteHelper mInstance = null;

    public DaoHelper (Context c, Class className) {
        this.className = className;
        if (mInstance == null) mInstance = new MyOrmLiteHelper(c.getApplicationContext())
    }

    public Dao<T, Integer> getDao() {
        try {
            return mInstance.getDao(className);
        } catch (SQLException e){
            return null;
        }
    }
}
