
package br.com.digitalhouse.moviematch.model.genero;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneroResponse {

    @Expose
    @SerializedName("genres")
    private List<Genero> generos;

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

}