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
import java.sql.SQLException;
import java.util.UUID;
import models.User;

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
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return false;
    }

    public boolean checkValidPassword(String idUser, String currentPass) {
        String query = "select * from tbl_USER where password = ? and id = ? ";
        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, currentPass);
            ps.setString(2, idUser);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return false;
    }

    public boolean updatePassword(String idUser, String currentPass, String newPass) {
        String query = "update tbl_USER set password = ? where id = ?";
        try {
            if (checkValidPassword(idUser, currentPass) == true) {
                con = new Connect().getConnection();
                ps = con.prepareStatement(query);
                ps.setString(1, newPass);
                ps.setString(2, idUser);
                ps.executeUpdate();
                return true;
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return false;
    }

    public static void main(String[] args) {
        UserDAO u = new UserDAO();
        System.out.println(u.updatePassword("22EFAF8B", "password11", "password1"));
    }
}
