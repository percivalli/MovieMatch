package br.com.digitalhouse.moviematch.repository;

import android.content.Context;

import java.util.List;

import br.com.digitalhouse.moviematch.data.database.Database;
import br.com.digitalhouse.moviematch.data.database.dao.GeneroDAO;
import br.com.digitalhouse.moviematch.data.network.ApiService;
import br.com.digitalhouse.moviematch.model.genero.Genero;
import br.com.digitalhouse.moviematch.model.genero.GeneroResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static br.com.digitalhouse.moviematch.data.network.ApiService.API_KEY;
import static br.com.digitalhouse.moviematch.data.network.ApiService.LANGUAGE;

public class GeneroRepository {

    //Pega os dados da base de dados
    public Flowable<List<Genero>> getGeneroLocal(Context context) {

        Database database = Database.getDatabase(context);
        GeneroDAO generoDAO = database.generoDAO();

        return generoDAO.getAllRxJava();
    }

    //Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Genero> generos) {

        Database database = Database.getDatabase(context);
        GeneroDAO generoDAO = database.generoDAO();

        generoDAO.deleteAll();
        generoDAO.insertAll(generos);
    }

    //Pega os items que virão da api de filmes
    public Single<GeneroResponse> getGeneroApi() {

        return ApiService.getApiService().getGenero(API_KEY, LANGUAGE);
    }
}
