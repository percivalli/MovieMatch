package br.com.digitalhouse.moviematch.model;

public class MeusMatchs {

    //Atributos
    private String nome;
    private String email;

    //Construtor
    public MeusMatchs() {
    }

    public MeusMatchs(String nome) {
        this.nome = nome;
    }

    public MeusMatchs(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    //Getter and Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
