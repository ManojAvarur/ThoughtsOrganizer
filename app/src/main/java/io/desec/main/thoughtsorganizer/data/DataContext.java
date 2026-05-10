package io.desec.main.thoughtsorganizer.data;

import android.content.Context;

import androidx.room.Room;


public class DataContext {
    private static AppDatabase _db;
    public static AppDatabase getDbInstance(Context context){
        if (_db != null) {
            return  _db;
        }

        _db = Room.databaseBuilder(context, AppDatabase.class, "thoughts-organizer").build();

        return  _db;
    }

    public static AppDatabase getDbInstance(){
        return _db;
    }
}
