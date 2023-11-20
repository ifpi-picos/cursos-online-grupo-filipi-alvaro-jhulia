package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Nota;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {


  public int cadastrar(T entidade);

  public List<T> consultarTodos() throws SQLException;

  public int alterar(T entidade);

  public int remover(T entidade);

}