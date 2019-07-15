package br.com.digitalhouse.moviematch.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.moviematch.model.genero.Genero;
import io.reactivex.Flowable;

@Dao
public interface GeneroDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Genero genero);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Genero> generos);

    @Update
    void update(Genero genero);

    @Delete
    void delete(Genero genero);

    @Query("DELETE FROM generos")
    void deleteAll();

    @Query("SELECT * FROM generos")
    List<Genero> getAll();

    @Query("SELECT * FROM generos")
    Flowable<List<Genero>> getAllRxJava();

    @Query("SELECT * FROM generos WHERE id = :id ORDER BY name")
    Genero getById(long id);

    @Query("SELECT * FROM generos WHERE name = :name")
    Genero getByName(String name);

}
