package br.edu.ifpi.entidades;

public class ProfessorCurso {
    private String nome;
    private String curso;

    public ProfessorCurso(String nome, String curso) {
        this.nome = nome;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }
    public String getCurso() {
        return curso;
    }
}
