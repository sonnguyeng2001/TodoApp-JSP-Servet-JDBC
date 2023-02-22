/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import model.User;

/**
 *
 * @author FPTshop
 */
public class UserDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs;

    public User login(String username, String password) {
        String query = "select * from tbl_USER where username = ? and password = ?";
        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public User checkUserExists(String username) {
        String query = "select * from tbl_USER where username = ?";
        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception ex) {

        }
        return null;
    }

    public boolean AddUser(String username, String password) {
        String query = "insert into tbl_USER values(?,?,?)";
        String IDUser = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        try {
            if (checkUserExists(username) == null) {
                //INSERT USER
                con = new Connect().getConnection();
                ps = con.prepareStatement(query);
                ps.setString(1, IDUser);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.executeUpdate();
                return true;
            } else {
                //Deny INSERT USER
                return false;
            }
        } catch (Exception ex) {

        }
        return false;
    }
}
