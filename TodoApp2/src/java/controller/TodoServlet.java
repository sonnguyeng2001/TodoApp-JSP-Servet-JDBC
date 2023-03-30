/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DateDAO;
import dao.TodoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Todo;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author FPTshop
 */
@WebServlet(name = "TodoServlet", urlPatterns = {"/todo/*"})
public class TodoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/GetTodoServlet":
                getTodosAction(request, response);
                break;
            case "/GetTodoByStatusServlet":
                getTodosByStatusAction(request, response);
                break;
            case "/AddTodoServlet":
                addTodoAction(request, response);
                break;
            case "/RemoveTodoServlet":
                removeTodoAction(request, response);
                break;
            case "/UpdateTodoServlet":
                updateTodoAction(request, response);
                break;
            case "/DetailsTodoServlet":
                detailsTodoAction(request, response);
                break;
            case "/SearchTodoServlet":
                searchTodoAction(request, response);
                break;

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addTodoAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        LocalDateTime localDate = LocalDateTime.now();
        String title = request.getParameter("titleTodo");
        String note = request.getParameter("noteTodo");
        String idUser = (String) request.getSession(false).getAttribute("idUser");

        JSONObject obj = new JSONObject();
        TodoDAO todoDao = new TodoDAO();
        Todo todo = new Todo("", idUser, title, note, 0, localDate, localDate);
        Todo todoResult = todoDao.addTodo(todo);
        try {
            if (todoResult != null) {
                obj.put("status", true);
                obj.put("id_User ", todoResult.getId_USER());
                obj.put("id_TODO", todoResult.getId_TODO().trim());
                obj.put("title", todoResult.getTitle());
                obj.put("note", todoResult.getNote());
                obj.put("status_Todo", todoResult.getStatus_TODO());
                obj.put("createAt", todoResult.getCreateAt());
                obj.put("updateAt", todoResult.getUpdateAt());
            } else {
                obj.put("status", false);
            }
        } catch (JSONException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.print(obj);
    }

    private void removeTodoAction(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        try (PrintWriter out = response.getWriter()) {
            String idTodo = request.getParameter("idTodo");
            TodoDAO todoDao = new TodoDAO();
            JSONObject obj = new JSONObject();
            if (todoDao.removeTodo(idTodo)) {
                obj.put("status", true);
            } else {
                obj.put("status", false);
            }
            out.print(obj);
        }
    }

    private void getTodosAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String idUser = request.getParameter("id_User");
        TodoDAO todoDao = new TodoDAO();
        List<Todo> todos = todoDao.getTodosByUser(idUser);
        JSONObject obj = new JSONObject();
        try {
            obj.put("status", true);
            obj.put("data", todos);
        } catch (JSONException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.print(obj);
    }

    private void detailsTodoAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException, JSONException {
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();

        String idTodo = request.getParameter("id_todo");
        TodoDAO todoDao = new TodoDAO();
        DateDAO dateDao = new DateDAO();
        Todo todo = todoDao.getDetailsTodo(idTodo);
        if (todo != null) {
            String createAt = dateDao.parseDate(todo.getCreateAt(), "HH:mm - dd/MM/yyyy ");
            String updateAt = dateDao.parseDate(todo.getUpdateAt(), "HH:mm - dd/MM/yyyy ");
            obj.put("status", true);
            obj.put("id_TODO", todo.getId_TODO());
            obj.put("id_USER", todo.getId_USER());
            obj.put("title", todo.getTitle());
            obj.put("note", todo.getNote());
            obj.put("status_TODO", todo.getStatus_TODO());
            obj.put("createAt", createAt);
            obj.put("updateAt", updateAt);
        } else {
            obj.put("status", false);
        }
        out.print(obj);
    }

    private void updateTodoAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {

            String idTodo = request.getParameter("idTodo");
            String title = request.getParameter("title");
            String note = request.getParameter("note");
            TodoDAO todoDao = new TodoDAO();
            JSONObject obj = new JSONObject();
            LocalDateTime localDate = LocalDateTime.now();
            if (todoDao.updateTodo(title, note, localDate, idTodo)) {
                try {
                    obj.put("status", true);
                } catch (JSONException ex) {
                    Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    obj.put("status", false);
                } catch (JSONException ex) {
                    Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.print(obj);

        }
    }

    private void searchTodoAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            String searchKey = request.getParameter(("searchKey"));
            String idUser = (String) request.getSession(false).getAttribute("idUser");
            TodoDAO todoDao = new TodoDAO();
            JSONObject obj = new JSONObject();
            try {
                obj.put("data", todoDao.searchByKey(idUser, searchKey));
            } catch (JSONException ex) {
                Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.print(obj);
        }
    }

    private void getTodosByStatusAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String idUser = request.getParameter("idUser");
        String statusTodo = request.getParameter("statusTodo");

        TodoDAO todoDao = new TodoDAO();
        List<Todo> todos = todoDao.getTodosByStatus(idUser, statusTodo);
        JSONObject obj = new JSONObject();
        try {
            obj.put("status", true);
            obj.put("data", todos);
        } catch (JSONException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.print(obj);
    }

}
