/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author FPTshop
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user/*"})
public class UserServlet extends HttpServlet {

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
            throws ServletException, IOException, JSONException {
        String pathInfo = request.getPathInfo();
        switch (pathInfo) {
            case "/LoginServlet":
                loginAction(request, response);
                break;
            case "/RegisterServlet":
                registerAction(request, response);
                break;
            case "/LogoutServlet":
                logoutAction(request, response);
                break;
            case "/UpdateUserServlet":
                updateUserAction(request, response);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private void loginAction(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        String username = request.getParameter("username_SignIn");
        String password = request.getParameter("password_SignIn");
        PrintWriter out = response.getWriter();
        boolean status = false;
        JSONObject obj = new JSONObject();
        UserDAO userDao = new UserDAO();
        HttpSession session = request.getSession();
        User user = userDao.login(username, password);
        if (user != null) {
            session.setAttribute("idUser", user.getId());
            session.setAttribute("userName", username);
            session.setAttribute("password", password);
            status = true;
        } else {
            status = false;
        }
        obj.put("status", status);
        out.print(obj);
    }

    private void registerAction(HttpServletRequest request, HttpServletResponse response) throws JSONException {
        try {
            PrintWriter out = response.getWriter();
            String username = request.getParameter("username_SignUp");
            String password = request.getParameter("password_SignUp");
            UserDAO userDao = new UserDAO();
            JSONObject obj = new JSONObject();
            obj.put("status", userDao.AddUser(username, password));
            out.print(obj);
        } catch (IOException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void logoutAction(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        boolean status = false;
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("idUser");
            session.removeAttribute("userName");
            session.removeAttribute("password");
            status = true;
            obj.put("status", status);
        } catch (JSONException e) {
            status = false;
        }
        out.print(obj);

    }

    private void updateUserAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentPass = request.getParameter("currentPassword");
        String newPass = request.getParameter("newPassword");
        String idUser = (String) request.getSession(false).getAttribute("idUser");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        boolean status = false;
        try {
            UserDAO userDAO = new UserDAO();
            status = userDAO.updatePassword(idUser, currentPass, newPass);
            if (status) {
                HttpSession session = request.getSession();
                session.removeAttribute("password");
                session.setAttribute("password", newPass);
            }
            obj.put("status", status);
        } catch (JSONException e) {
            status = false;
        }
        out.print(obj);

    }

}
