
package br.edu.ifpi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    public static Connection getConexao() {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_academico",
            "root", "filipi000"); 
            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
    
        return null;
    }
}    
}
