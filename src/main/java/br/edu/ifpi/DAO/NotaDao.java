package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Estatistica;
import br.edu.ifpi.entidades.Nota;
import br.edu.ifpi.enums.StatusAluno;
import br.edu.ifpi.enums.StatusNota;

public class NotaDao implements Dao<Nota> {

    final private Connection conexao;

    public NotaDao(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Nota nota) {
        String SQL_INSERT = "INSERT INTO nota (nota, curso_id, aluno_id, status) VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, nota.getNota());
            preparedStatement.setInt(2, nota.getCurso());
            preparedStatement.setInt(3, nota.getAluno());
            System.err.println("status da nota: " + nota.getStatus());
            preparedStatement.setString(4, nota.getStatus());

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
            StatusNota status = nota.getNota() >= 7 ? StatusNota.APROVADO : StatusNota.REPROVADO;

            preparedStatement.setInt(1, nota.getNota());
            preparedStatement.setInt(2, nota.getId());
            preparedStatement.setString(3, String.valueOf(status));
            
                            

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

    public Estatistica estatisticaAlunos() {
        String SQL_QUERY = "SELECT MAX(nota) AS maior_nota, aluno_id FROM sistema_academico.nota GROUP BY nota, aluno_id ORDER BY MAX(nota) DESC";

        try {
            PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 

            ResultSet result = statement.executeQuery();

            System.out.println(result);

            if (result.next()) {
                int maiorNota = result.getInt("maior_nota");
                int alunoId = result.getInt("aluno_id");
                StatusNota status = result.getString("status").equals("APROVADO") ? StatusNota.APROVADO : StatusNota.REPROVADO;
                
                Estatistica estatistica = new Estatistica(maiorNota, alunoId);
                System.out.println(estatistica);

                return estatistica;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Estatistica getEstatisticaAluno(Aluno aluno) {
        String SQL_QUERY = "SELECT MAX(nota) AS maior_nota, aluno_id FROM sistema_academico.nota WHERE aluno_id = ? GROUP BY nota, aluno_id ORDER BY MAX(nota) DESC";

        try {
            PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 
            statement.setInt(1, aluno.getId());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int maiorNota = result.getInt("maior_nota");
                int alunoId = result.getInt("aluno_id");
                
                Estatistica estatistica = new Estatistica(maiorNota, alunoId);
                System.out.println(estatistica);

                return estatistica;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void exibirMediaCurso(int cursoId) {
        String SQL_QUERY = "SELECT AVG(nota) AS media FROM nota WHERE curso_id = ?";
    
        try {
            PreparedStatement statement = conexao.prepareStatement(SQL_QUERY);
            statement.setInt(1, cursoId);
    
            ResultSet result = statement.executeQuery();
    
            if (result.next()) {
                double media = result.getDouble("media");
                DecimalFormat df = new DecimalFormat("#.##");
                System.out.println("A média geral do curso " + cursoId + " é: " + df.format(media));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Não foi possível obter a média geral do curso " + cursoId);
        }
    }

}