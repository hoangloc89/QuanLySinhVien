/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.User;
import bussiness.UserRole;
import data.UserDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DIENMAYXANH
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
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
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        boolean isRedirect = false;
        String url = "/WEB-INF/user/index.jsp";

        HttpSession session = request.getSession();
        User userLogin = (User)session.getAttribute("user");
        
        if (userLogin == null) {
            isRedirect = true;
            url = "login";
            response.sendRedirect(url);
            return;
        }
        
        if (userLogin.getRole() != UserRole.ADMIN) {
            url = "/WEB-INF/405.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action == null) action = "list";
        
        if (action.equals("list")) { // default action: list all users
            List<User> users = UserDB.getAll();
            request.setAttribute("users", users);      
            
        } else if (action.equals("add")) {    // add action: load add new form to fill user information
            url = "/WEB-INF/user/add.jsp";
            request.setAttribute("action", "add");
        } else if (action.equals("save")) {   // save action: get user information from add new form and save to database
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String roleString = request.getParameter("role");
            UserRole role = UserRole.valueOf(roleString.toUpperCase());
            
            // insert new user to database through UserDB class
            User user = new User(email, firstName, lastName, password, role);
            UserDB.insert(user);
            
            // after insert new user from database, back to the user list
            url = "user";
            isRedirect = true;
        } else if (action.equals("edit")) {   // edit action: load edit form and fill user information
            url = "/WEB-INF/user/add.jsp";
            String email = request.getParameter("email");
            User user = UserDB.get(email);
            request.setAttribute("u", user);
            request.setAttribute("action", "edit");
            
        } else if (action.equals("update")) { // update action: update user information from edit form and save to database
            // update user with new information to database through UserDB class
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String roleString = request.getParameter("role");
            UserRole role = UserRole.valueOf(roleString.toUpperCase());

            // after update user from database, back to the user list
            User user = new User(email, firstName, lastName, password, role);
            UserDB.update(user);
            
            isRedirect = true;
            url = "user?action=list";
        } else if (action.equals("delete")) { // delete action: load delete form and fill user information, confirm before remove a user
            url = "/WEB-INF/user/add.jsp";
            String email = request.getParameter("email");
            User user = UserDB.get(email);
            request.setAttribute("u", user);
            request.setAttribute("action", "delete");
            
        } else if (action.equals("remove")) { // remove action: delete user from database
                // remove user from database through UserDB class
                String email = request.getParameter("email");
                User user = UserDB.get(email);
                UserDB.delete(user);
            
            // after remove user from database, back to the user list
            isRedirect = true;
            url = "user?action=list";
        }

        if (isRedirect) {
            response.sendRedirect(url);
        } else {
            getServletContext().getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

