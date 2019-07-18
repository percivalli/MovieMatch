package br.com.digitalhouse.moviematch.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.moviematch.model.filme.Filme;
import io.reactivex.Flowable;

@Dao
public interface FilmeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Filme filme);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Filme> filmes);

    @Update
    void update(Filme filme);

    @Delete
    void delete(Filme filme);

    @Query("DELETE from filmes")
    void deleteAll();

    @Query("SELECT * FROM filmes")
    List<Filme> getAll();

    @Query("SELECT * FROM filmes")
    Flowable<List<Filme>> getAllRxJava();

    @Query("SELECT * FROM filmes WHERE id = :id")
    Filme getById(long id);

    @Query("SELECT * FROM filmes WHERE title = :title")
    Filme getByTitle(String title);

    @Query("SELECT * FROM filmes " +
            "WHERE genreIds LIKE '[' || :genreId || ']' " +
            "OR genreIds LIKE '[' || :genreId || ',%' " +
            "OR genreIds LIKE '%,' || :genreId || ']' " +
            "OR genreIds LIKE '%,' || :genreId || ',%' ")
    List<Filme> getByGenreId(long genreId);
}