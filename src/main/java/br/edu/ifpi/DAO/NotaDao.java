package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Nota;
import br.edu.ifpi.enums.StatusNota;

public class NotaDAO implements DAO<Nota> {

    final private Connection conexao;

    public NotaDAO(Connection conexao) {
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
                ResultSet result = statement.executeQuery(SQL_QUERY);) {
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
        String SQL_UPDATE = "UPDATE nota SET nota = ? SET status = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);
            StatusNota status = nota.getNota() >= 7 ? StatusNota.APROVADO : StatusNota.REPROVADO;

            preparedStatement.setInt(1, nota.getNota());
            preparedStatement.setString(2, String.valueOf(status));
            preparedStatement.setInt(3, nota.getId());

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

    public void estatisticaAlunos(Curso curso) throws SQLException {
        String query = "select aluno.nome AS nome_aluno, nota.nota AS nota_aluno from sistema_academico.nota join sistema_academico.aluno on nota.aluno_id = aluno.id join sistema_academico.curso on nota.curso_id = curso.id where curso.id = ? order by nota.nota desc;";

        PreparedStatement stm = conexao.prepareStatement(query);
        stm.setInt(1, curso.getId());
        ResultSet result = stm.executeQuery();

        if (result.next()) {
            String nomeAluno = result.getString("nome_aluno");
            int notaAluno = result.getInt("nota_aluno");

            System.out.println("Aluno: " + nomeAluno + " | Nota: " + notaAluno);
        }
    }

    // public Estatistica getEstatisticaAluno(Aluno aluno) {
    // String SQL_QUERY = "SELECT MAX(nota) AS maior_nota, aluno_id FROM
    // sistema_academico.nota WHERE aluno_id = ? GROUP BY nota, aluno_id ORDER BY
    // MAX(nota) DESC";

    // try {
    // PreparedStatement statement = conexao.prepareStatement(SQL_QUERY);
    // statement.setInt(1, aluno.getId());

    // ResultSet result = statement.executeQuery();

    // if (result.next()) {
    // int maiorNota = result.getInt("maior_nota");
    // int alunoId = result.getInt("aluno_id");

    // Estatistica estatistica = new Estatistica(maiorNota, alunoId);
    // System.out.println(estatistica);

    // return estatistica;
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    public void getEstatisticaAluno(Aluno aluno) {
        String sql = "SELECT curso.nome as nome_curso, nota.nota as nota_aluno from sistema_academico.nota join sistema_academico.aluno on nota.aluno_id = aluno.id join sistema_academico.curso on nota.curso_id = curso.id where aluno.id = ? order by nota.nota desc";
        
        try {
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setInt(1, aluno.getId());

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String nomeCurso = result.getString("nome_curso");
                int notaAluno = result.getInt("nota_aluno");

                System.out.println("Curso: " + nomeCurso + " | Nota: " + notaAluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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