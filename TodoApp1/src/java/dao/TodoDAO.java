/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.Connect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Todo;
import model.User;

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
        String query = "select * from tbl_TODO where id_USER = ?";
        try {
            con = new Connect().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id_User);
            rs = ps.executeQuery();
            while (rs.next()) {
                todos.add(new Todo(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7)));
            }
        } catch (Exception ex) {

        }
        return todos;
    }

    public static void main(String[] args) {
        TodoDAO dao = new TodoDAO();
        List<Todo> list;
        list = dao.getTodosByUser("7D3C9250");
        for(Todo t : list)
        {
            System.out.println(t);
        }
    }
}
