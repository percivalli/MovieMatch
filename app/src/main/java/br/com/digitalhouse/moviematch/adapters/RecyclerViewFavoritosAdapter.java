package br.com.digitalhouse.moviematch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewFavoritosClickListener;
import br.com.digitalhouse.moviematch.model.genero.Genero;

public class RecyclerViewFavoritosAdapter
        extends RecyclerView.Adapter<RecyclerViewFavoritosAdapter.ViewHolder> {

    //Atributos
    private List<Genero> generos;
    private RecyclerViewFavoritosClickListener listener;

    //Construtor
    public RecyclerViewFavoritosAdapter() {
    }

    public RecyclerViewFavoritosAdapter(
            List<Genero> generos, RecyclerViewFavoritosClickListener listener) {
        this.generos = generos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.favoritos_recyclerview_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Genero genero = generos.get(position);

        viewHolder.setConteudoNaTela(genero);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(genero);
            }
        });

    }

    @Override
    public int getItemCount() {
        return generos.size();
    }

    /*
    //Limpa os generos
    public void clear(){
        this.generos.clear();
        notifyDataSetChanged();
    }
    */

    //Atualiza a lista de generos
    public void update(List<Genero> generosList) {
        this.generos = generosList;
        notifyDataSetChanged();
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
        public void setConteudoNaTela(Genero genero) {

            textViewFavoritosNomeGenero.setText(genero.getName());

            //Primeira letra do Nome do Genero
            textViewFavoritosLetraGenero.setText(genero.getName().substring(0, 1));

        }
    }
}