package br.edu.ifpi.entidades;

public class Matricula {
    private int id;
    private Aluno aluno;
    private Curso curso;
    private String status;

    public Matricula(Aluno aluno, Curso curso, String status) {
        this.aluno = aluno;
        this.curso = curso;
        this.status = status;
    }

    public Matricula(int id, Aluno aluno, Curso curso, String status) {
        this.id = id;
        this.aluno = aluno;
        this.curso = curso;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}