package br.com.digitalhouse.moviematch.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewDetalheFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.Filme;

public class RecyclerViewDetalheFavoritosAdapter
        extends RecyclerView.Adapter<RecyclerViewDetalheFavoritosAdapter.ViewHolder> {

    //Atributos
    private List<Filme> listaFilmes;
    private RecyclerViewDetalheFavoritosClickListener listener;

    //Construtor
    public RecyclerViewDetalheFavoritosAdapter() {
    }

    public RecyclerViewDetalheFavoritosAdapter(List<Filme> listaFilmes, RecyclerViewDetalheFavoritosClickListener listener) {
        this.listaFilmes = listaFilmes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.detalhe_favoritos_recyclerview_item,
                viewGroup,
                false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final Filme filme = listaFilmes.get(position);

        //Cor de fundo zebrada
        if (position % 2 == 0) {
            viewHolder.itemView.setBackgroundResource(R.color.corFundoRecyclerViewOn);
        } else {
            viewHolder.itemView.setBackgroundResource(R.color.corFundoRecyclerViewOff);
        }

        viewHolder.setConteudoNaTela(filme);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(filme, position);

                viewHolder.setConteudoNaTela(filme);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Atributos dos elementos
        TextView textViewNomeFilme;
        ImageView imageViewDetalheFavoritosCheckItem;
        FloatingActionButton floatingActionButtonFavoritos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Inicialização das views
            textViewNomeFilme = itemView.findViewById(R.id.textViewDetalheFavoritosNomeFilme);

            imageViewDetalheFavoritosCheckItem =
                    itemView.findViewById(R.id.imageViewDetalheFavoritosCheckItem);

            floatingActionButtonFavoritos =
                    itemView.findViewById(R.id.floatingActionButtonFavoritos);
        }

        //Atribuição das views os valores da variável GeneroFilme
        public void setConteudoNaTela(Filme filme) {

            textViewNomeFilme.setText(filme.getNomeFilme());

            if (filme.isFilmeSelecionado() == true) {
                imageViewDetalheFavoritosCheckItem.setVisibility(View.VISIBLE);
            } else {
                imageViewDetalheFavoritosCheckItem.setVisibility(View.INVISIBLE);
            }

        }
    }

}

