/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author aluno
 */
public class DBConnection {

    Connection connection;
    Statement statement;
    private static DBConnection instance = null;

    private DBConnection() {
        abrirConexao();
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    private void abrirConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Biblioteca",
                    "user",
                    "password");
            statement = connection.createStatement();
        } catch (Exception e) {
        }
    }

    @Override
    protected void finalize() throws Throwable {
        statement.close();
        connection.close();
    }

}
