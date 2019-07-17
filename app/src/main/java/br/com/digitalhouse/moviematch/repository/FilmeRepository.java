package br.com.digitalhouse.moviematch.repository;

import android.content.Context;

import java.util.List;

import br.com.digitalhouse.moviematch.data.database.Database;
import br.com.digitalhouse.moviematch.data.database.dao.FilmeDAO;
import br.com.digitalhouse.moviematch.data.network.ApiService;
import br.com.digitalhouse.moviematch.model.filme.Filme;
import br.com.digitalhouse.moviematch.model.filme.FilmeResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;

import static br.com.digitalhouse.moviematch.data.network.ApiService.API_KEY;
import static br.com.digitalhouse.moviematch.data.network.ApiService.LANGUAGE;
import static br.com.digitalhouse.moviematch.data.network.ApiService.PAGE;
import static br.com.digitalhouse.moviematch.data.network.ApiService.POPULARITY;

public class FilmeRepository {

    //Pega os dados da base de dados
    public Flowable<List<Filme>> getFilmeLocal(Context context) {

        Database database = Database.getDatabase(context);
        FilmeDAO filmeDAO = database.filmeDAO();

        return filmeDAO.getAllRxJava();
    }

    //Insere uma lista reults na base de dados
    public void insertItems(Context context, List<Filme> filmes) {

        Database database = Database.getDatabase(context);
        FilmeDAO filmeDAO = database.filmeDAO();
        filmeDAO.insertAll(filmes);
    }

    //Pega os items que virão da api de filmes
    public Single<FilmeResponse> getFilmeApi() {

        return ApiService.getApiService().getFilme(API_KEY, LANGUAGE, POPULARITY);//teste
    }
}
