package br.com.digitalhouse.moviematch.interfaces;

import java.util.List;

import br.com.digitalhouse.moviematch.model.Filme;

public interface RecyclerViewDetalheFavoritosClickListener {

    void onClick(Filme filme, int position);

}
