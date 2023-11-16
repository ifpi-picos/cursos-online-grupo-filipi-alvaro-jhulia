package br.edu.ifpi.classes;

public class Curso {
    private int id;
    private String nome;
    private String status;
    private int cargaHoraria;
    private int idProfessor; 

    public Curso(int id,String nome, String status, int cargaHoraria, int idProfessor) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.cargaHoraria = cargaHoraria;
        this.idProfessor = idProfessor;
    }

    public Curso(String nome, String status, int cargaHoraria, int idProfessor){
      this.nome = nome;
      this. status = status;
      this. cargaHoraria = cargaHoraria;
      this.idProfessor = idProfessor;
    }

    public int getid(){
        return id;
    }

    public void setid(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    public int getIdProfessor(){
        return idProfessor;
    }
    public void setIdProfessor(int idProfessor){
        this.idProfessor = idProfessor;
    }
}