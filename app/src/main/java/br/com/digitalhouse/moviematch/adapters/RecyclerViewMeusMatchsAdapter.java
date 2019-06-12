package br.com.digitalhouse.moviematch.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.interfaces.RecyclerViewMeusMatchsClickListener;
import br.com.digitalhouse.moviematch.model.MeusMatchs;

public class RecyclerViewMeusMatchsAdapter
        extends RecyclerView.Adapter<RecyclerViewMeusMatchsAdapter.ViewHolder> {

    //Atributos
    private List<MeusMatchs> listaMeusMatchs;
    private RecyclerViewMeusMatchsClickListener listener;

    //Construtor
    public RecyclerViewMeusMatchsAdapter() {
    }

    public RecyclerViewMeusMatchsAdapter(List<MeusMatchs> listaMeusMatchs, RecyclerViewMeusMatchsClickListener listener) {
        this.listaMeusMatchs = listaMeusMatchs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.meusmatchs_recyclerview_item,
                viewGroup,
                false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final MeusMatchs meusMatchs = listaMeusMatchs.get(position);
        viewHolder.setConteudoNaTela(meusMatchs);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(meusMatchs);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaMeusMatchs.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //Atributos dos elementos
        private TextView textViewMeusMatchsNome;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Inicialização das views
            textViewMeusMatchsNome = itemView.findViewById(R.id.textViewMeusMatchsNome);
        }

        //Atribuição das views os valores da variável GeneroFilme
        public void setConteudoNaTela(MeusMatchs meusMatchs) {

            textViewMeusMatchsNome.setText(meusMatchs.getNome());

        }

    }
}
