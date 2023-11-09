package br.edu.ifpi.enums;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
    private int id;
    private String informacaoContato;
    private List<Curso> cursosMinistrados;


    public Professor(String nome, int id, String informacaoContato) {
        this.nome = nome;
        this.id = id;
        this.informacaoContato = informacaoContato;
        this.cursosMinistrados = new ArrayList<>();
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

    public void setCodigo(int codigo) {
        this.id = id;
    }

    public String getInformacoesContato() {
        return informacaoContato;
    }

    public void setInformacoesContato(String informacoesContato) {
        this.informacaoContato = informacoesContato;
    }

    public List<Curso> getCursosMinistrados() {
        return cursosMinistrados;
    }

    public void setCursosMinistrados(List<Curso> cursosMinistrados) {
        this.cursosMinistrados = cursosMinistrados;
    }
}
