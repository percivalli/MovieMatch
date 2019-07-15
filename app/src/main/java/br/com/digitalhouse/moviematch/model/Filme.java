package br.com.digitalhouse.moviematch.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

@Entity(tableName = "Filme")
public class Filme implements Parcelable {

    //Atributos
    private String nomeFilme;

    private boolean filmeSelecionado;

    //Construtor
    public Filme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    protected Filme(Parcel in) {
        nomeFilme = in.readString();
        filmeSelecionado = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomeFilme);
        dest.writeByte((byte) (filmeSelecionado ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Filme> CREATOR = new Creator<Filme>() {
        @Override
        public Filme createFromParcel(Parcel in) {
            return new Filme(in);
        }

        @Override
        public Filme[] newArray(int size) {
            return new Filme[size];
        }
    };

    //Getter and Setter
    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public boolean isFilmeSelecionado() {
        return filmeSelecionado;
    }

    public void setFilmeSelecionado(boolean filmeSelecionado) {
        this.filmeSelecionado = filmeSelecionado;
    }

}
