package br.com.digitalhouse.moviematch.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.digitalhouse.moviematch.R;
import java.util.List;

import br.com.digitalhouse.moviematch.interfaces.RecyclerViewFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.GeneroFilme;

public class RecyclerViewFavoritosAdapter
        extends RecyclerView.Adapter<RecyclerViewFavoritosAdapter.ViewHolder> {

    //Atributos
    private List<GeneroFilme> listaGenerosFilmes;
    private RecyclerViewFavoritosClickListener listener;

    //Construtor
    public RecyclerViewFavoritosAdapter() {
    }

    public RecyclerViewFavoritosAdapter(
            List<GeneroFilme> listaGenerosFilmes,
            RecyclerViewFavoritosClickListener listener) {

        this.listaGenerosFilmes = listaGenerosFilmes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.favoritos_recyclerview_item,
                viewGroup,
                false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final GeneroFilme generoFilme = listaGenerosFilmes.get(position);
        viewHolder.setConteudoNaTela(generoFilme);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(generoFilme);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaGenerosFilmes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //Atributos dos elementos
        private TextView textViewFavoritosNomeGenero;
        private TextView textViewFavoritosLetraGenero;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Inicialização das views
            textViewFavoritosNomeGenero = itemView.findViewById(R.id.textViewFavoritosNomeGenero);
            textViewFavoritosLetraGenero = itemView.findViewById(R.id.textViewFavoritosLetraGenero);
        }

        //Atribuição das views os valores da variável GeneroFilme
        public void setConteudoNaTela(GeneroFilme generoFilme) {

            textViewFavoritosNomeGenero.setText(generoFilme.getNomeGenero());

            //Primeira letra do Nome do Genero
            textViewFavoritosLetraGenero.setText(generoFilme.getNomeGenero().substring(0, 1));

        }

    }
}
