 package br.edu.ifpi.DAO;

import java.util.List;

public interface IDAO <T>{
    public int cadastrar(T entidade);

  public List<T> consultarTodos();

  public int alterar(T entidade);

  public int remover(T entidade);

    
}