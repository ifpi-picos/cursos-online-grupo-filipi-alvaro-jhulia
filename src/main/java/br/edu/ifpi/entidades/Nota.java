package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusNota;

public class Nota {
  private int id;
  private int nota;
  private int aluno;
  private int curso;
  private StatusNota status;

  public Nota(int nota, int aluno, int curso, StatusNota status) {
    this.nota = nota;
    this.aluno = aluno;
    this.curso = curso;
    this.status = status;
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
public String getStatus() {
    return this.status == StatusNota.APROVADO ? "APROVADO" : "REPROVADO";
  }
  
  public void setStatus(StatusNota status) {
    this.status = status;
    
  }
}