package br.com.digitalhouse.moviematch.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Filme implements Parcelable {

    //Atributos
    private String nomeFilme;

    //Construtor
    public Filme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    protected Filme(Parcel in) {
        nomeFilme = in.readString();
    }

    public static final Parcelable.Creator<Filme> CREATOR = new Creator<Filme>() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomeFilme);
    }
}
