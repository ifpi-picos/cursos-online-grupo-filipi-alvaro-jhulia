package br.edu.ifpi.classes;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private int numeroMatricula;
    private String email;
    private List<Curso> cursosMatriculados;

    public Aluno(String nome, int numeroMatricula, String email) {
        this.nome = nome;
        this.numeroMatricula = numeroMatricula;
        this.email = email;
        this.cursosMatriculados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public List<Curso> getCursosMatriculados() {
        return cursosMatriculados;
    }

    public void setCursosMatriculados(List<Curso> cursosMatriculados) {
        this.cursosMatriculados = cursosMatriculados;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
