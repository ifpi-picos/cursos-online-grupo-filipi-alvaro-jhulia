package br.edu.ifpi.enums;

public enum StatusNota {
APROVADO ("Aprovado"), REPROVADO("Reprovado");

    private String value;

    @Override
    public String toString(){
        return value;
    }

    public String getValue() {
    return value;
    }
    
    StatusNota(String value) {
    this.value = value;
    }
}


