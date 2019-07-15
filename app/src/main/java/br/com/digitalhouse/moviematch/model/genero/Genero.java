
package br.com.digitalhouse.moviematch.model.genero;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "generos")
public class Genero implements Parcelable {

    @Expose
    @PrimaryKey
    private Long id;

    @Expose
    private String name;

    //Construtores
    public Genero() {
    }

    public Genero(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Genero(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
    }

    public static final Creator<Genero> CREATOR = new Creator<Genero>() {
        @Override
        public Genero createFromParcel(Parcel in) {
            return new Genero(in);
        }

        @Override
        public Genero[] newArray(int size) {
            return new Genero[size];
        }
    };

    //Getters Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
    }
}
