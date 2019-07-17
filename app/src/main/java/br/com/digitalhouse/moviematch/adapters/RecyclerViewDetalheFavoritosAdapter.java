package br.com.digitalhouse.moviematch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewDetalheFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.filme.Filme;

public class RecyclerViewDetalheFavoritosAdapter
        extends RecyclerView.Adapter<RecyclerViewDetalheFavoritosAdapter.ViewHolder> {

    //Atributos
    private List<Filme> filmes;
    private RecyclerViewDetalheFavoritosClickListener listener;

    //Construtor
    public RecyclerViewDetalheFavoritosAdapter() {
    }

    public RecyclerViewDetalheFavoritosAdapter(
            List<Filme> filmes, RecyclerViewDetalheFavoritosClickListener listener) {
        this.filmes = filmes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.detalhe_favoritos_recyclerview_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        final Filme filme = filmes.get(position);

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
        return filmes.size();
    }

    /*
    //Limpa os generos
    public void clear(){
        this.filmes.clear();
        notifyDataSetChanged();
    }
    */

    //Atualiza a lista de filmes
    public void update(List<Filme> filmesList) {
        this.filmes = filmesList;
        notifyDataSetChanged();
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

        //Atribuição das views os valores da variável Filme
        public void setConteudoNaTela(Filme filme) {

            textViewNomeFilme.setText(filme.getTitle());

            if (filme.isFilmeSelecionado() == true) {
                imageViewDetalheFavoritosCheckItem.setVisibility(View.VISIBLE);
            } else {
                imageViewDetalheFavoritosCheckItem.setVisibility(View.INVISIBLE);
            }

        }
    }
}