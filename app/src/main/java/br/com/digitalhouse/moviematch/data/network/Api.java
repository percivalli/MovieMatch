package br.com.digitalhouse.moviematch.data.network;

import br.com.digitalhouse.moviematch.model.filme.FilmeResponse;
import br.com.digitalhouse.moviematch.model.genero.GeneroResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("genre/movie/list")
    Single<GeneroResponse> getGenero(@Query("api_key") String api_key,
                                     @Query("language") String language);

    @GET("discover/movie")
    Single<FilmeResponse> getFilme(@Query("api_key") String api_key,
                                   @Query("language") String language,
                                   @Query("sort_by") String popularity,
                                   @Query("with_genres") long generoId);
}
