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

}