package com.haminhtu;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.haminhtu.CustomerDao;
import com.haminhtu.CustomerEntity;

@Database(entities = {CustomerEntity.class},version = 1)
public  abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract CustomerDao bookmarkDao();

    public static AppDatabase getAppDatabase(Context context){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class,"News").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
