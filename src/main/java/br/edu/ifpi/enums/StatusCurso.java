package br.edu.ifpi.enums;

public enum StatusCurso {
  ABERTO("Aberto"), FECHADO("Fechado");

  private String value;

  @Override
  public String toString(){
      return value;
  }

  public String getValue() {
    return value;
  }
  
  StatusCurso(String value) {
    this.value = value;
  }
}
