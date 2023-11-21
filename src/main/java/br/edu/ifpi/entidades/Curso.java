package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {
  private int id;
  private String nome;
  private int cargaHoraria;
  private StatusCurso status = StatusCurso.ABERTO;
  private Professor professor;

  public Curso(String nome, int cargaHoraria2, StatusCurso status, Professor professor) {
    this.nome = nome;
    this.cargaHoraria = cargaHoraria2;
    this.status = status;
    this.professor = professor;
  }

  public Curso(int id, String nome, int cargaHoraria, StatusCurso status, int professor2) {
    this.id = id;
    this.nome = nome;
    this.cargaHoraria = cargaHoraria;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCargaHoraria() {
    return cargaHoraria;
  }

  public void setCargaHoraria(int cargaHoraria) {
    this.cargaHoraria = cargaHoraria;
  }

  public StatusCurso getStatus() {
    return status;
  }

  public void setStatus(StatusCurso status) {
    this.status = status;
  }

  public int getProfessorId() {
    return professor.getId();
  }

}