package br.edu.ifpi.dao;

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

public class MatriculaDao implements Dao<Matricula> {

    final private Connection conexao;

    public MatriculaDao(Connection conexao) {
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

        CursoDao cursoDao = new CursoDao(conexao);
        AlunoDao alunoDao = new AlunoDao(conexao);

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

                Curso cursoItem = cursoDao.consultarPorId(curso);
                Aluno alunoItem = alunoDao.consultarPorId(aluno);

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

        CursoDao cursoDao = new CursoDao(conexao);
        AlunoDao alunoDao = new AlunoDao(conexao);

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
                
                Curso cursoItem = cursoDao.consultarPorId(cursoId);
                Aluno alunoItem = alunoDao.consultarPorId(alunoId);

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

        CursoDao cursoDao = new CursoDao(conexao);
        AlunoDao alunoDao = new AlunoDao(conexao);

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

                Curso cursoItem = cursoDao.consultarPorId(curso);
                Aluno alunoItem = alunoDao.consultarPorId(aluno);

                Matricula matricula = new Matricula(id, alunoItem, cursoItem, status);

                return matricula;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
