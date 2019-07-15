package br.com.digitalhouse.moviematch.model.usuario;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "usuarios_filme", primaryKeys = {"login_usuario", "id_genero", "id_filme"})
public class Usuario {

    @NonNull
    @ColumnInfo(name = "login_usuario")
    private String loginUsuario;

    @NonNull
    @ColumnInfo(name = "id_genero")
    private Long idGenero;

    @NonNull
    @ColumnInfo(name = "id_filme")
    private Long idFilme;

    //Construtor vazio
    public Usuario() {
    }

    //Construtor especifico
    public Usuario(@NonNull String loginUsuario, Long idGenero, Long idFilme) {
        this.loginUsuario = loginUsuario;
        this.idGenero = idGenero;
        this.idFilme = idFilme;
    }

    //Getters e Setters
    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public Long getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Long idGenero) {
        this.idGenero = idGenero;
    }

    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }
}