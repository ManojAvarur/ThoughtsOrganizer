package io.desec.main.thoughtsorganizer.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.desec.main.thoughtsorganizer.data.entities.Thought;

@Dao
public interface ThoughtsDao {
    @Insert
    void insert(Thought thought);

    @Query("SELECT * FROM thoughts")
    List<Thought> getAllThoughts();

    @Query("SELECT * FROM thoughts WHERE id = :thoughtId")
    Thought getThoughtById(int thoughtId);
}
