/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author FPTshop
 */
public class Connect {
    public Connection getConnection() throws ClassNotFoundException {
        Connection con = null;
        Statement statement = null;
        String strServer = "DESKTOP-ASPIRE\\SQLEXPRESS";
        String strDatabase = "TodoApp";

        try {
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
            String URL = "jdbc:sqlserver://" + strServer
                    + ":1433;databaseName=" + strDatabase
                    + ";integratedSecuriry = true"
                    + ";user = sa; password = 123";
            con = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return con;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(new Connect().getConnection());
    }
}
