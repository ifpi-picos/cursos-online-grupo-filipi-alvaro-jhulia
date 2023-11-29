package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.enums.StatusCurso;

public class CursoDao implements Dao<Curso> {

  final private Connection conexao;

  public CursoDao(Connection conexao) {
    this.conexao = conexao;
  }

  @Override
  public int cadastrar(Curso curso) {
    String SQL_INSERT = "INSERT INTO curso (nome, carga_horaria, status, professor_id) VALUES (?,?, ?, ?)";

    try {
      PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

      preparedStatement.setString(1, curso.getNome());
      preparedStatement.setInt(2, curso.getCargaHoraria());
      preparedStatement.setString(3, curso.getStatus().getValue());
      preparedStatement.setInt(4, curso.getProfessorId());

      int row = preparedStatement.executeUpdate();

      return row;
    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public List<Curso> consultarTodos() {
    String SQL_QUERY = "SELECT * FROM curso";

    List<Curso> cursos = new ArrayList<>();

    try (
      Statement statement = conexao.createStatement();
      ResultSet result = statement.executeQuery(SQL_QUERY);
    ) {
      while (result.next()) {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        int cargaHoraria = result.getInt("carga_horaria");
        StatusCurso status = result.getString("status").equals("Aberto") ? StatusCurso.ABERTO : StatusCurso.FECHADO;
        int professor = result.getInt("professor_id");

        Curso curso = new Curso(id, nome, cargaHoraria, status, professor);
        cursos.add(curso);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return cursos;
  }

  @Override
  public int alterar(Curso curso) {
    String SQL_UPDATE = "UPDATE curso SET nome = ?, carga_horaria = ?, status = ?, professor_id = ? WHERE id = ?";

    try {
      PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

      preparedStatement.setString(1, curso.getNome());
      preparedStatement.setInt(2, curso.getCargaHoraria());
      preparedStatement.setString(3, curso.getStatus().toString());
      preparedStatement.setInt(4, curso.getProfessorId());
      preparedStatement.setInt(5, curso.getId());

      int row = preparedStatement.executeUpdate();

      return row;
    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
      System.out.println("Erro ao atualizar curso");
      e.printStackTrace();
    }
    return 0;
  }



  @Override
  public int remover(Curso curso) {
    String SQL_DELETE = "DELETE FROM curso WHERE id = ?";

    try {
      PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

      preparedStatement.setInt(1, curso.getId());

      int row = preparedStatement.executeUpdate();

      return row;
    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  public Curso consultarPorId(int idCurso) {
    String SQL_QUERY = "SELECT * FROM sistema_academico.curso WHERE id = ?";
    
    try {
      PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 

      System.out.printf("%d",idCurso);
      statement.setInt(1,idCurso);
      System.out.printf("%s\n", SQL_QUERY);
      System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());

      ResultSet result = statement.executeQuery();

      System.out.println(result);
      if (result.next()) {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        int cargaHoraria = result.getInt("carga_horaria");
        StatusCurso status = result.getString("status").equals("Aberto") ? StatusCurso.ABERTO : StatusCurso.FECHADO;
        int professorId = result.getInt("professor_id");

        Curso curso = new Curso(id, nome, cargaHoraria, status, professorId);

        return curso;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

}