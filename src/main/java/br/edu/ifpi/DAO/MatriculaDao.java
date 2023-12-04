package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Matricula;

public class MatriculaDAO implements DAO<Matricula> {

    final private Connection conexao;

    public MatriculaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Matricula matricula) {
        String SQL_INSERT = "INSERT INTO matricula (curso_id, aluno_id, status) VALUES (?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, matricula.getCurso().getId());
            preparedStatement.setInt(2, matricula.getAluno().getId());
            preparedStatement.setString(3, matricula.getStatus());

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
    public List<Matricula> consultarTodos() throws SQLException {
        String SQL_QUERY = "SELECT * FROM matricula";

        CursoDAO cursoDAO = new CursoDAO(conexao);
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        List<Matricula> matriculas = new ArrayList<>();

        try (
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(SQL_QUERY);
        ) {
            while (result.next()) {
                int id = result.getInt("id");
                int curso = result.getInt("curso_id");
                int aluno = result.getInt("aluno");
                String status = result.getString("status");

                Curso cursoItem = cursoDAO.consultarPorId(curso);
                Aluno alunoItem = alunoDAO.consultarPorId(aluno);

                Matricula item = new Matricula(id, alunoItem, cursoItem, status);
                matriculas.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conexao.close();

        return matriculas;
    }

    @Override
    public int alterar(Matricula matricula) {
        String SQL_UPDATE = "UPDATE matricula SET status = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, matricula.getStatus());
            preparedStatement.setInt(2, matricula.getId());

            System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", preparedStatement.toString());

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
    public int remover(Matricula matricula) {
        String SQL_DELETE = "DELETE FROM matricula WHERE id = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, matricula.getId());

            int row = preparedStatement.executeUpdate();

            return row;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Matricula consultarPorAluno(Aluno aluno) {
        String SQL_QUERY = "SELECT * FROM matricula WHERE aluno_id = ?";

        CursoDAO cursoDAO = new CursoDAO(conexao);
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        try {
            PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 
            
            statement.setInt(1, aluno.getId());
            System.out.printf("%s\n", SQL_QUERY);
            System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());

            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                int id = result.getInt("id");
                int cursoId = result.getInt("curso_id");
                int alunoId = result.getInt("aluno_id");
                String status = result.getString("status");
                
                Curso cursoItem = cursoDAO.consultarPorId(cursoId);
                Aluno alunoItem = alunoDAO.consultarPorId(alunoId);

                Matricula matricula = new Matricula(id, alunoItem, cursoItem, status);

                return matricula;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int consultarQuantidadeAlunos(int curso_id) {
        String SQL_QUERY = "SELECT COUNT(*) AS quantidade FROM matricula WHERE curso_id = ? AND status = 'Ativa'";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_QUERY);
            preparedStatement.setInt(1, curso_id);
    
            ResultSet result = preparedStatement.executeQuery();
    
            if (result.next()) {
                int quantidade = result.getInt("quantidade");
                return quantidade;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    
        return 0;
    }

    public Matricula consultarPorId(int p_id) {
        String SQL_QUERY = "SELECT * FROM matricula WHERE id = ?";

        CursoDAO cursoDAO = new CursoDAO(conexao);
        AlunoDAO alunoDAO = new AlunoDAO(conexao);

        try {
            PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 
            
            statement.setInt(1, p_id);
            System.out.printf("%s\n", SQL_QUERY);
            System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());

            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                int id = result.getInt("id");
                int curso = result.getInt("curso_id");
                int aluno = result.getInt("aluno_id");
                String status = result.getString("status");

                
                System.out.printf("ID: %d%n\n", id);

                Curso cursoItem = cursoDAO.consultarPorId(curso);
                Aluno alunoItem = alunoDAO.consultarPorId(aluno);

                Matricula matricula = new Matricula(id, alunoItem, cursoItem, status);

                return matricula;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int contarAlunosComNotaMaiorQueSete(int cursoId) {
        String SQL_QUERY = "SELECT COUNT(DISTINCT m.aluno_id) AS quantidade " +
                        "FROM matricula m " +
                        "JOIN nota n ON m.aluno_id = n.aluno_id AND m.curso_id = n.curso_id " +
                        "WHERE m.curso_id = ? AND m.status = 'Ativa' AND n.nota > 7";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_QUERY);
            preparedStatement.setInt(1, cursoId);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getInt("quantidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int contarAlunosComNotaMenorQueSete(int cursoId) {
        String SQL_QUERY = "SELECT COUNT(DISTINCT m.aluno_id) AS quantidade " +
                           "FROM matricula m " +
                           "JOIN nota n ON m.aluno_id = n.aluno_id AND m.curso_id = n.curso_id " +
                           "WHERE m.curso_id = ? AND m.status = 'Ativa' AND (n.nota IS NULL OR n.nota <= 7)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_QUERY);
            preparedStatement.setInt(1, cursoId);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getInt("quantidade");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public String calcularPorcentagemAprovacao(int cursoId) {
        String SQL_QUERY = "SELECT " +
                           "  (SUM(CASE WHEN n.nota >= 7 THEN 1 ELSE 0 END) / COUNT(DISTINCT m.aluno_id)) * 100 AS porcentagem_aprovados, " +
                           "  (SUM(CASE WHEN n.nota < 7 OR n.nota IS NULL THEN 1 ELSE 0 END) / COUNT(DISTINCT m.aluno_id)) * 100 AS porcentagem_reprovados " +
                           "FROM matricula m " +
                           "JOIN nota n ON m.aluno_id = n.aluno_id AND m.curso_id = n.curso_id " +
                           "WHERE m.curso_id = ? AND m.status = 'Ativa'";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_QUERY);
            preparedStatement.setInt(1, cursoId);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                double porcentagemAprovados = result.getDouble("porcentagem_aprovados");
                double porcentagemReprovados = result.getDouble("porcentagem_reprovados");

                return String.format("Porcentagem de aprovados: %.2f%% | Porcentagem de reprovados: %.2f%%",
                                     porcentagemAprovados, porcentagemReprovados);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Não foi possível calcular a porcentagem de alunos aprovados e reprovados.";
    }

}
