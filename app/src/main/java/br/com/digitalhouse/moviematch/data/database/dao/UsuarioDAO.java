package br.com.digitalhouse.moviematch.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.digitalhouse.moviematch.model.usuario.Usuario;
import io.reactivex.Flowable;

@Dao
public interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Usuario usuario);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Usuario> usuarios);

    @Update
    void update(Usuario usuario);

    @Query("DELETE FROM usuarios_filme WHERE id_genero = :idGenero AND id_filme = :idFilme")
    void deleteByIdGeneroFilme(long idGenero, long idFilme);

    @Query("DELETE from usuarios_filme")
    void deleteAll();

    @Query("SELECT * FROM usuarios_filme")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuarios_filme")
    Flowable<List<Usuario>> getAllRxJava();

    @Query("SELECT * FROM usuarios_filme WHERE id_genero= :idGenero")
    Usuario getByIdGenero(long idGenero);

    @Query("SELECT COUNT(id_filme) FROM usuarios_filme WHERE id_filme = :idFilme")
    int getByIdFilme(long idFilme);

    @Query("SELECT COUNT(id_filme) FROM usuarios_filme WHERE id_filme = :idFilme AND id_genero= :idGenero")
    int getByIdGeneroFilme(long idGenero, long idFilme);

    @Query("SELECT COUNT(id_filme) FROM usuarios_filme")
    int getByQtdeFilme();
}