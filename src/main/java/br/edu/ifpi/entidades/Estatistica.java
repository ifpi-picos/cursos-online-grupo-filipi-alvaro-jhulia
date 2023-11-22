package br.edu.ifpi.entidades;

public class Estatistica {
    private int maiorNota;
    private int alunoId;

    public Estatistica(int maiorNota, int alunoId) {
        this.maiorNota = maiorNota;
        this.alunoId = alunoId;
    }

    public int getMaiorNota() {
        return maiorNota;
    }

    public int getAlunoId() {
        return alunoId;
    }
}
