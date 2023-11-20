package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpi.entidades.Administrador;

public class AdministradorDao implements Dao<Administrador> {

  final private Connection conexao;

  public AdministradorDao(Connection conexao) {
    this.conexao = conexao;
  }

  @Override
  public int cadastrar(Administrador administrador) {
    String SQL_INSERT = "INSERT INTO administrador (nome, email) VALUES (?,?)";

    try {
      PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

      preparedStatement.setString(1, administrador.getNome());
      preparedStatement.setString(2, administrador.getEmail());

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
  public List<Administrador> consultarTodos() throws SQLException {
    String SQL_QUERY = "SELECT * FROM administrador";

    List<Administrador> administradores = new ArrayList<>();

    try (
      Statement statement = conexao.createStatement();
      ResultSet result = statement.executeQuery(SQL_QUERY);
    ) {
      while (result.next()) {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String email = result.getString("email");

        Administrador administrador = new Administrador(id, nome, email);
        administradores.add(administrador);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    conexao.close();

    return administradores;
  }

  @Override
  public int alterar(Administrador administrador) {
    String SQL_UPDATE = "UPDATE administrador SET nome = ?, email = ? WHERE id = ?";

    try {
      PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

      preparedStatement.setString(1, administrador.getNome());
      preparedStatement.setString(2, administrador.getEmail());
      preparedStatement.setInt(3, administrador.getId());

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
  public int remover(Administrador administrador) {
    String SQL_DELETE = "DELETE FROM administrador WHERE id = ?";

    try {
      PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

      preparedStatement.setInt(1, administrador.getId());

      int row = preparedStatement.executeUpdate();

      return row;
    } catch (SQLException e) {
      System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

}