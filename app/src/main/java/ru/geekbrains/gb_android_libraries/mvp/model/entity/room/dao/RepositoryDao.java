package ru.geekbrains.gb_android_libraries.mvp.model.entity.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.room.RoomRepository;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RepositoryDao {
    @Insert(onConflict = REPLACE)
    void insert(RoomRepository repository);

    @Insert(onConflict = REPLACE)
    void insert(RoomRepository... repository);

    @Insert(onConflict = REPLACE)
    void insert(List<RoomRepository> repository);

    @Insert
    void update(RoomRepository repository);

    @Insert
    void update(RoomRepository... repository);

    @Insert
    void update(List<RoomRepository> repository);

    @Delete
    void delete(RoomRepository repository);

    @Delete
    void delete(RoomRepository... repository);

    @Delete
    void delete(List<RoomRepository> repository);

    @Query("SELECT * FROM roomrepository")
    List<RoomRepository> getAll();

    @Query("SELECT * FROM roomrepository WHERE userLogin = :login")
    List<RoomRepository> findForUser(String login);
}
