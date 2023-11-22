package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusAluno;

public class Aluno {
  private int id;
  private String nome;
  private String email;
  private StatusAluno status;
  

  public Aluno(String nome, String email, StatusAluno status) {
    this.nome = nome;
    this.email = email;
    this.status = status;
  }

  public Aluno(int id, String nome, String email, StatusAluno status) {
    this.id = id;
    this.nome = nome;
    this.email = email;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStatus() {
    return this.status == StatusAluno.ATIVO ? "ATIVO" : "INATIVO";
  }

  public void setStatus(StatusAluno status) {
    this.status = status;
  }

}