package br.edu.ifpi.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {


  public int cadastrar(T entidade);

  public List<T> consultarTodos() throws SQLException;

  public int alterar(T entidade);

  public int remover(T entidade);

}