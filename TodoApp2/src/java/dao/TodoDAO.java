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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import models.Todo;

/**
 *
 * @author FPTshop
 */
public class TodoDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs;

    public List<Todo> getTodosByUser(String id_User) {
        List<Todo> todos = new ArrayList<>();
        String query = "select * from tbl_TODO where id_USER = ? order by status_TODO";

        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id_User);
            rs = ps.executeQuery();
            while (rs.next()) {
                todos.add(new Todo(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getTimestamp(7).toLocalDateTime()));
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return todos;
    }

    public List<Todo> getTodosByStatus(String id_User, String status) {
        List<Todo> todos = new ArrayList<>();
        try {
            if (status.equals("0")) {
                todos = getTodosByUser(id_User);
            } else {
                String query = "select * from tbl_TODO where id_USER = ? and status_TODO = ? order by status_TODO";
                con = new Connect().getConnection();
                ps = con.prepareStatement(query);
                ps.setString(1, id_User);
                ps.setString(2, status);
                rs = ps.executeQuery();
                while (rs.next()) {
                    todos.add(new Todo(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getTimestamp(6).toLocalDateTime(),
                            rs.getTimestamp(7).toLocalDateTime()));
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return todos;
    }

    public Todo getDetailsTodo(String idTodo) {
        String query = "select * from tbl_TODO where id_TODO = ?";
        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, idTodo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Todo todo = new Todo(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getTimestamp(7).toLocalDateTime());
                return todo;
            }
        } catch (ClassNotFoundException | SQLException e) {
        }
        return null;
    }

    public Todo addTodo(Todo todo) {
        String query = "insert into tbl_TODO(id_TODO,id_USER,title,note,status_TODO,createAt,updateAt) values(?,?,?,?,?,CAST(? as datetime),CAST(? as datetime))";
        String ID_TODO = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        try {
            //INSERT TODO
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, ID_TODO);
            ps.setString(2, todo.getId_USER());
            ps.setString(3, todo.getTitle());
            ps.setString(4, todo.getNote());
            ps.setInt(5, -1);
            ps.setObject(6, todo.getCreateAt());
            ps.setObject(7, todo.getUpdateAt());
            ps.executeUpdate();
            return new Todo(ID_TODO, todo.getId_USER(), todo.getTitle(), todo.getNote(), -1, todo.getCreateAt(), todo.getUpdateAt());
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public boolean removeTodo(String idTodo) {
        String query = "delete from tbl_TODO where id_TODO = ?";
        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, idTodo.trim());
            ps.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public List<Todo> searchByKey(String id_User, String searchKey) {
        List<Todo> todos = new ArrayList<>();
        String query = "select  * from tbl_TODO where id_USER = ? and title like ? order by status_TODO";

        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id_User);
            ps.setString(2, "%" + searchKey + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                todos.add(new Todo(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getTimestamp(6).toLocalDateTime(),
                        rs.getTimestamp(7).toLocalDateTime()));
            }
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return todos;
    }

    public boolean updateTodo(String title, String note, LocalDateTime updateAt, String idTodo) {
        String query = "update tbl_TODO\n"
                + "set title = ?, note = ?, updateAt = CAST(? as datetime)\n"
                + "where id_TODO = ?";
        try {
            //INSERT TODO
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, note);
            ps.setObject(3, updateAt);
            ps.setString(4, idTodo);
            ps.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public boolean setCompleteTodo(String idUser, String idTodo) {
        String query = "update tbl_TODO\n"
                + "set status_TODO = 1 "
                + "where id_USER = ? and id_TODO = ?";
        try {
            //UPDATE STATUS TODO
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, idUser);
            ps.setString(2, idTodo);
            ps.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    public static void main(String[] args) {
        TodoDAO dao = new TodoDAO();
        System.out.println(dao.setCompleteTodo("A91620CB", "06E15CCA"));

    }
}
