package br.edu.ifpi.classes;

import br.edu.ifpi.enums.StatusAluno;

public class Aluno {
    private String nome;
    private String id;
    private int email;
    private StatusAluno status;

    public Aluno(String nome, int email, String id, StatusAluno status) {
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.status = status;
    }

    public Aluno(String nome , int email){
        this.nome = nome;
        this.email = email;
    }

   public String getStatusAsString(){
    return this.status == StatusAluno.ATIVO?"ATIVO":"INATIVO";
   }

}
