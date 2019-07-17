package br.com.digitalhouse.moviematch.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.digitalhouse.moviematch.data.database.dao.FilmeDAO;
import br.com.digitalhouse.moviematch.data.database.dao.GeneroDAO;
import br.com.digitalhouse.moviematch.data.database.dao.UsuarioDAO;
import br.com.digitalhouse.moviematch.model.filme.Filme;
import br.com.digitalhouse.moviematch.model.genero.Genero;
import br.com.digitalhouse.moviematch.model.usuario.Usuario;

@androidx.room.Database(
        entities = {Genero.class, Filme.class, Usuario.class}, version = 16, exportSchema = false)
@TypeConverters(Converters.class)  //Adiciona os conversores

public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract GeneroDAO generoDAO();

    public abstract FilmeDAO filmeDAO();

    //Controla os generos e filmes escolhidos pelo usuario
    public abstract UsuarioDAO usuarioDAO();


    public static Database getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "my_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
