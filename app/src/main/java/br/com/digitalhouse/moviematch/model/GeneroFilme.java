package br.com.digitalhouse.moviematch.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class GeneroFilme implements Parcelable {

    //Atributos
    private String nomeGenero;
    private List<Filme> listaFilmes = new ArrayList<Filme>();

    //Construtor
    public GeneroFilme() {
    }

    public GeneroFilme(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    public GeneroFilme(String nomeGenero, List<Filme> listaFilmes) {
        this.nomeGenero = nomeGenero;
        this.listaFilmes = listaFilmes;
    }

    protected GeneroFilme(Parcel in) {
        nomeGenero = in.readString();

        try{
            listaFilmes = in.readArrayList(GeneroFilme.class.getClassLoader());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static final Creator<GeneroFilme> CREATOR = new Parcelable.Creator<GeneroFilme>() {
        @Override
        public GeneroFilme createFromParcel(Parcel in) {
            return new GeneroFilme(in);
        }

        @Override
        public GeneroFilme[] newArray(int size) {
            return new GeneroFilme[size];
        }
    };

    //Getter and Setter
    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    public List<Filme> getListaFilmes() {
        return listaFilmes;
    }

    public void setListaFilmes(List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomeGenero);

        try{
            dest.writeList(listaFilmes);

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
