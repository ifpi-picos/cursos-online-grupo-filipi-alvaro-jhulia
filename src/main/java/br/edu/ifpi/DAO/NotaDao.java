package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Nota;

public class NotaDao implements Dao<Nota> {

    final private Connection conexao;

    public NotaDao(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Nota nota) {
        String SQL_INSERT = "INSERT INTO nota (nota, curso_id, aluno_id) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, nota.getNota());
            preparedStatement.setInt(2, nota.getCurso());
            preparedStatement.setInt(3, nota.getAluno());

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
    public List<Nota> consultarTodos() throws SQLException {
        String SQL_QUERY = "SELECT * FROM nota";

        List<Nota> notas = new ArrayList<>();

        try (
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(SQL_QUERY);
        ) {
            while (result.next()) {
                int id = result.getInt("id");
                int nota = result.getInt("nota");
                int curso = result.getInt("curso_id");
                int aluno = result.getInt("aluno");

                Nota item = new Nota(id, nota, curso, aluno);
                notas.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conexao.close();

        return notas;
    }

    @Override
    public int alterar(Nota nota) {
        String SQL_UPDATE = "UPDATE nota SET nota = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

            preparedStatement.setInt(1, nota.getNota());
            preparedStatement.setInt(2, nota.getId());

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
    public int remover(Nota nota) {
        String SQL_DELETE = "DELETE FROM nota WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, nota.getId());

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