package br.edu.ifpi.entidades;

public class Matricula {
    private int id;
    private int aluno;
    private int curso;
    private String status;

    public Matricula(int aluno, int curso, String status) {
        this.aluno = aluno;
        this.curso = curso;
        this.status = status;
    }

    public Matricula(int id, int aluno, int curso, String status) {
        this.aluno = aluno;
        this.curso = curso;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getAluno() {
        return aluno;
    }

    public int getCurso() {
        return curso;
    }

    public String getStatus() {
        return status;
    }
}