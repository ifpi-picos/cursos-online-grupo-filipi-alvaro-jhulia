package br.edu.ifpi;

import java.sql.SQLException;

public class ConnectionFactory {
    public Connection recuperarConexao() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/criar_tabela_aqui?user=root&password=root");
        } catch (SQLException e) {
            e.printStackTrace();
            //throw new RuntimeErrorException(null);
        }
    }
}
