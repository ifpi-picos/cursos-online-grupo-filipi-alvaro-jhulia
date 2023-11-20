package br.edu.ifpi.entidades;

public class Nota {
  private int id;
  private int nota;
  private int aluno;
  private int curso;

  public Nota(int nota, int aluno, int curso) {
    this.nota = nota;
    this.aluno = aluno;
    this.curso = curso;
  }

  public Nota(int id, int nota, int aluno, int curso) {
    this.nota = nota;
    this.aluno = aluno;
    this.curso = curso;
  }

  public int getId() {
    return id;
  }

  public int getNota() {
    return nota;
  }

  public void setNota(int nota) {
    this.nota = nota;
  }

  public int getAluno() {
    return aluno;
  }

  public void setAluno(int aluno) {
    this.aluno = aluno;
  }

  public int getCurso() {
    return curso;
  }

  public void setCurso(int curso) {
    this.curso = curso;
  }
}