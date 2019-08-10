package br.com.digitalhouse.moviematch.model.filme;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "filmes")
public class Filme {

    @Ignore
    @Expose
    private Boolean adult;

    @Ignore
    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("genre_ids")
    private List<Long> genreIds;

    @ColumnInfo(name = "genreId")
    private long genreId;

    @Expose
    @PrimaryKey
    private Long id;

    @Ignore
    @SerializedName("original_language")
    private String originalLanguage;

    @Ignore
    @SerializedName("original_title")
    private String originalTitle;

    @Ignore
    @Expose
    private String overview;

    @Ignore
    @Expose
    private Double popularity;

    @Ignore
    @SerializedName("poster_path")
    private String posterPath;

    @Ignore
    @SerializedName("release_date")
    private String releaseDate;

    @Expose
    private String title;

    @Ignore
    @Expose
    private Boolean video;

    @Ignore
    @SerializedName("vote_average")
    private Double voteAverage;

    @Ignore
    @SerializedName("vote_count")
    private Long voteCount;

    //Atributo para controlar os filmes que foram selecionados
    private boolean filmeSelecionado;

    //Construtor Vazio
    public Filme() {
    }

    //Construtor especifico
    public Filme(List<Long> genreIds, Long id, String title) {
        this.genreIds = genreIds;
        this.id = id;
        this.title = title;

        if (genreIds.size() != 0) {
            this.genreId = genreIds.get(0);
        }
    }

    //Getters e Setters
    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {

        //if (genreIds.size() != 0){
        //    this.genreId = genreIds.get(0);
        //}

        this.genreIds = genreIds;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFilmeSelecionado() {
        return filmeSelecionado;
    }

    public void setFilmeSelecionado(boolean filmeSelecionado) {
        this.filmeSelecionado = filmeSelecionado;
    }
}
