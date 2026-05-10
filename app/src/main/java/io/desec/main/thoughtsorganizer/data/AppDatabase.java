package io.desec.main.thoughtsorganizer.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import io.desec.main.thoughtsorganizer.data.dao.ThoughtsDao;
import io.desec.main.thoughtsorganizer.data.entities.Thought;

@Database(entities = {Thought.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public ThoughtsDao thoughtsDao;
}
