package br.com.digitalhouse.moviematch.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.moviematch.R;
import br.com.digitalhouse.moviematch.model.Filme;

public class ListViewDetalheFavoritosAdapter extends BaseAdapter {

    Context context;
    private List<Filme> listaFilmes = new ArrayList<>();

    public ListViewDetalheFavoritosAdapter(Context context, List<Filme> listaFilmes) {
        this.context = context;
        this.listaFilmes = listaFilmes;
    }

    @Override
    public int getCount() {
        return listaFilmes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaFilmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaFilmes.indexOf(getItem(position));
    }

    private class ViewHolder {
        TextView textViewNomeFilme;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            //Referencia tela layout do listview
            convertView = mInflater.inflate(R.layout.activity_list_view_detalhe_favoritos, null);
            holder = new ViewHolder();

            //Campos do ListView
            holder.textViewNomeFilme = (TextView) convertView
                    .findViewById(R.id.textViewDetalheFavoritosNomeFilme);

            //posição atual da linha
            Filme row_pos = listaFilmes.get(position);

            //Atualiza os valores dos campos
            holder.textViewNomeFilme.setText(row_pos.getNomeFilme());

            //Cor de fundo zebrada
            if (position % 2 == 0) {
                //holder.textViewNomeFilme.setBackgroundColor(convertView.getResources().getColor(R.color.corFundoListViewPar));
                convertView.setBackgroundColor(convertView.getResources().getColor(R.color.corFundoListViewPar));
            } else {
                //holder.textViewNomeFilme.setBackgroundColor(convertView.getResources().getColor(R.color.corFundoListViewImpar));
                convertView.setBackgroundColor(convertView.getResources().getColor(R.color.corFundoListViewImpar));
            }

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}

