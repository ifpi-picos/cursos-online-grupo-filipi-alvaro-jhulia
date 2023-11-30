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
import br.edu.ifpi.enums.StatusAluno;
import br.edu.ifpi.enums.StatusCurso;

public class AlunoDao implements Dao<Aluno> {
    
    final private Connection conexao;
    
    public AlunoDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    @Override
    public int cadastrar(Aluno aluno) {
        String SQL_INSERT = "INSERT INTO aluno (nome, email, status) VALUES (?,?,?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);
            
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEmail());
            preparedStatement.setString(3, aluno.getStatus());
            
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
    public List<Aluno> consultarTodos() throws SQLException {
        String SQL_QUERY = "SELECT * FROM aluno";
        
        
        List<Aluno> alunos = new ArrayList<>();

        try (
            Statement statement = conexao.createStatement();
            ResultSet result = statement.executeQuery(SQL_QUERY);
        ) {
            while (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String email = result.getString("email");
                StatusAluno status = result.getString("status").equals("ATIVO") ? StatusAluno.ATIVO : StatusAluno.INATIVO;
                
                Aluno aluno = new Aluno(id, nome, email, status);
                alunos.add(aluno);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        
        conexao.close();
        
        return alunos;
    }
    
    @Override
    public int alterar(Aluno aluno) {
        String SQL_UPDATE = "UPDATE aluno SET nome = ?, email = ?, status = ?  WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);
            
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEmail());
            preparedStatement.setString(3, aluno.getStatus());
            preparedStatement.setInt(4, aluno.getId());
            
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
    public int remover(Aluno aluno) {
        String SQL_DELETE = "DELETE FROM aluno WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);
            
            preparedStatement.setInt(1, aluno.getId());
            
            int row = preparedStatement.executeUpdate();
            
            return row;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Aluno consultarPorId(int idAluno) {
        String SQL_QUERY = "SELECT * FROM sistema_academico.aluno WHERE id = ?";
        
        try {
            PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 
            
            System.out.printf("%d",idAluno);
            
            statement.setInt(1,idAluno);
            System.out.printf("%s\n", SQL_QUERY);
            System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());

            ResultSet result = statement.executeQuery();
            
            System.out.println(result);
            if (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String email = result.getString("email");
                StatusAluno status = result.getString("status").equals("ATIVO") ? StatusAluno.ATIVO : StatusAluno.INATIVO;

                System.out.println(id);
                System.out.println(email);
                System.out.println(nome);
                System.out.println(status);
                
                Aluno aluno = new Aluno(id, nome, email, status);
                System.out.println(aluno);

                return aluno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Aluno consultarPorEmail(String p_email) {
        String SQL_QUERY = "SELECT * FROM sistema_academico.aluno WHERE email = ?";

        try {
            PreparedStatement statement = conexao.prepareStatement(SQL_QUERY); 
            
            statement.setString(1, p_email);
            System.out.printf("%s\n", SQL_QUERY);
            System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());

            ResultSet result = statement.executeQuery();
            
            System.out.println(result);
            if (result.next()) {
                int id = result.getInt("id");
                String nome = result.getString("nome");
                String email = result.getString("email");
                StatusAluno status = result.getString("status").equals("ATIVO") ? StatusAluno.ATIVO : StatusAluno.INATIVO;

                System.out.println(id);
                System.out.println(email);
                System.out.println(nome);
                System.out.println(status);
                
                Aluno aluno = new Aluno(id, nome, email, status);
                System.out.println(aluno);

                return aluno;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> CursosMatriculados(Aluno aluno) {
        String SQL_QUERY = "SELECT curso.nome FROM sistema_academico.matricula " +
                        "JOIN sistema_academico.curso ON matricula.curso_id = curso.id " +
                        "WHERE matricula.aluno_id = ?";
    
        List<String> cursosMatriculados = new ArrayList<>();
    
        try (PreparedStatement statement = conexao.prepareStatement(SQL_QUERY)) {
            statement.setInt(1, aluno.getId());
    
            System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());
    
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String nomeCurso = result.getString("nome");
                    cursosMatriculados.add(nomeCurso);
    
                    System.out.println("Curso Matriculado: " + nomeCurso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return cursosMatriculados;
    }
    
    public List<String> getCursosConcluidos(Aluno aluno) {
        String SQL_QUERY = "SELECT DISTINCT curso.nome " +
                        "FROM sistema_academico.matricula " +
                        "JOIN sistema_academico.curso ON matricula.curso_id = curso.id " +
                        "LEFT JOIN sistema_academico.nota ON matricula.id = nota.matricula_id " +
                        "WHERE matricula.aluno_id = ? AND matricula.status = 'Concluído' AND nota.id IS NOT NULL";
    
        List<String> cursosConcluidos = new ArrayList<>();
    
        try (PreparedStatement statement = conexao.prepareStatement(SQL_QUERY)) {
            statement.setInt(1, aluno.getId());
    
            System.out.printf("SQL_QUERY com valores substituídos: %s%n\n", statement.toString());
    
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String nomeCurso = result.getString("nome");
                    cursosConcluidos.add(nomeCurso);
    
                    System.out.println("Curso Concluído: " + nomeCurso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return cursosConcluidos;
    }

}


    

