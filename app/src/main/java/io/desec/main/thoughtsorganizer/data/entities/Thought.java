package io.desec.main.thoughtsorganizer.data.entities;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.time.Instant;

@RequiresApi(api = Build.VERSION_CODES.O)
@Entity(tableName = "thoughts")
public class Thought {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String thought = "";

    public long createdAt = Instant.now().toEpochMilli();

    public long updatedAt = Instant.now().toEpochMilli();

    public String remoteStoreId;
}
