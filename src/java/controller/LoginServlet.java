/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.User;
import data.UserDB;
import java.io.IOException;
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
@WebServlet({"/login"})
public class LoginServlet extends HttpServlet{
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    
    String url = "/WEB-INF/login.jsp";
    String action = request.getParameter("action");
    if(action == null) action = "login";
    
    HttpSession session = request.getSession();
    User userLogin = (User) session.getAttribute("user");
    
    if("logout".equals(action) && userLogin != null){
        session.removeAttribute("user");
        url = "login";
        response.sendRedirect(url);
        return;
    }
    
    if(userLogin != null){
        url = "/WEB-INF/welcome.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
        return;
    }
    
    if(action.equals("login")){
        url = "/WEB-INF/login.jsp";
    } else if(action.equals("checkLogin")){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        User user = UserDB.get(email);
        if(user != null && user.checkLogin(email, password)){
            session.setAttribute("user", user);
            url = "/WEB-INF/welcome.jsp";
        } else {
            request.setAttribute("email", email);
            request.setAttribute("message", "Email hoặc password không đúng mời bạn nhập lại");
        }
    }
    getServletContext().getRequestDispatcher(url).forward(request, response);
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
