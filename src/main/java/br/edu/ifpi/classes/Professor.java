package br.edu.ifpi.classes;


public class Professor {
    private String nome;
    private int id;
    private String email;
    

    public Professor(String nome, int id, String email){
        this.nome = nome;
        this.id = id;
        this.email = email;
    }

    public Professor(String nome, String email){
        this.nome= nome;
        this.email = email;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return id;
    }

    public void setCodigo(int id) {
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
