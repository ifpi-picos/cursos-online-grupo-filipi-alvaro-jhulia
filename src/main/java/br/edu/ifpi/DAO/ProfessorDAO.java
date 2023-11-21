package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.enums.StatusCurso;

public class ProfessorDao implements Dao<Professor> {

  private Connection conexao;

  public ProfessorDao(Connection conexao) {
    this.conexao = conexao;
  }

  @Override
  public int cadastrar(Professor professor) {
    String SQL_INSERT = "INSERT INTO professor (nome, email) VALUES (?,?)";

    try {
      PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

      preparedStatement.setString(1, professor.getNome());
      preparedStatement.setString(2, professor.getEmail());

      int row = preparedStatement.executeUpdate();

      
      System.out.println(row); // 1
      return row;
    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  @Override
  public List<Professor> consultarTodos() {
    return null;
  }

  @Override
  public int alterar(Professor professor) {
    String SQL_UPDATE = "UPDATE professor SET nome = ?, email = ? WHERE id = ?";
        
    try {
        PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);
        
        preparedStatement.setString(1, professor.getNome());
        preparedStatement.setString(2, professor.getEmail());
        preparedStatement.setInt(3, professor.getId());
        
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
  public int remover(Professor professor) {
    String SQL_DELETE = "DELETE FROM professor WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);
            
            preparedStatement.setInt(1, professor.getId());
            
            int row = preparedStatement.executeUpdate();
            
            return row;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
  }

  public Professor consultarPorId(int idProfessor) {
    String SQL_QUERY = "SELECT * FROM sistema_academico.professor WHERE id = ?";
    
    try {
      PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 

      System.out.printf("%d",idProfessor);

      statement.setInt(1,idProfessor);
      System.out.printf("%s\n", SQL_QUERY);
      System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());

    
      ResultSet result = statement.executeQuery();

      System.out.println(result);
      if (result.next()) {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String email = result.getString("email");
        System.out.println(id);
        System.out.println(email);
        System.out.println(nome);

        Professor professor = new Professor(id, nome, email);
        System.out.println(professor);
        return professor;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }


}