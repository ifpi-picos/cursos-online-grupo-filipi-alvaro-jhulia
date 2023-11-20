package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.edu.ifpi.entidades.Aluno;

public class AlunoDao implements Dao<Aluno> {
    
    final private Connection conexao;
    
    public AlunoDao(Connection conexao) {
        this.conexao = conexao;
    }
    
    @Override
    public int cadastrar(Aluno aluno) {
        String SQL_INSERT = "INSERT INTO aluno (nome, email) VALUES (?,?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);
            
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEmail());
            
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
                
                Aluno aluno = new Aluno(id, nome, email);
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
        String SQL_UPDATE = "UPDATE aluno SET nome = ?, email = ? WHERE id = ?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);
            
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getEmail());
            preparedStatement.setInt(3, aluno.getId());
            
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
    
}