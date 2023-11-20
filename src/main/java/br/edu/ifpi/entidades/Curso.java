package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {
  private int id;
  private String nome;
  private int cargaHoraria;
  private StatusCurso status = StatusCurso.ABERTO;

  public Curso(String nome, int cargaHoraria, StatusCurso status) {
    this.nome = nome;
    this.cargaHoraria = cargaHoraria;
    this.status = status;
  }

  public Curso(int id, String nome, int cargaHoraria, StatusCurso status) {
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

}