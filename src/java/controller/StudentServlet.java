/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bussiness.Student;
import bussiness.StudentClass;
import bussiness.User;
import data.StudentClassDB;
import data.StudentDB;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
@WebServlet(name = "StudentServlet", urlPatterns = {"/student"})
public class StudentServlet extends HttpServlet {
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
        String url = "/WEB-INF/student/index.jsp";

        HttpSession session = request.getSession();
        User userLogin = (User)session.getAttribute("user");
        
        if (userLogin == null) {
            isRedirect = true;
            url = "login";
            response.sendRedirect(url);
            return;
        }
        
        String action = request.getParameter("action");
        
        if (action == null) action = "list";
        
        if (action.equals("list")) { // default action: list all users
            
            List<Student> students = StudentDB.getAll();
            request.setAttribute("students", students);
            
        } else if (action.equals("add")) {
            url = "/WEB-INF/student/add.jsp";
            List<StudentClass> studentClasses = StudentClassDB.getAll();
            request.setAttribute("studentClasses", studentClasses);
            request.setAttribute("action", "add");
        } else if (action.equals("addsv")) {
            String code = request.getParameter("code");
            String firstName = request.getParameter("fistName");
            String lastName = request.getParameter("lastName");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
            String gender = request.getParameter("gender");
            String classId = request.getParameter("classId");
            String address = request.getParameter("address");
            StudentClass studentClass = StudentClassDB.get(classId);
            
            // insert new user to database through StudentDB class
            Student student = new Student(code, firstName, lastName, birthDate, gender, address, studentClass);
            StudentDB.insert(student);
            
            // after insert new student from database, back to the student list
            url = "student";
            isRedirect = true;
        } else if(action.equals("delete")){
            String code = request.getParameter("code");
            StudentDB.deleteByCode(code);
            
         // after delete student from database, back to the student list
            url = "student";
            isRedirect = true;
        } else if(action.equals("edit")){
           url = "/WEB-INF/student/add.jsp";
           String code = request.getParameter("code");
           Student student = StudentDB.getStudent(code);
           List<StudentClass> studentClasses = StudentClassDB.getAll();
           request.setAttribute("student", student);
           request.setAttribute("studentClasses", studentClasses);
           request.setAttribute("action", "edit");
           
        } else if(action.equals("editsv")){
            String code = request.getParameter("code");
            String firstName = request.getParameter("fistName");
            String lastName = request.getParameter("lastName");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
            String gender = request.getParameter("gender");
            String classId = request.getParameter("classId");
            String address = request.getParameter("address");
            StudentClass studentClass = StudentClassDB.get(classId);
            
            // insert new student to database through StudentDB class
            Student student = new Student(code, firstName, lastName, birthDate, gender, address, studentClass);
            StudentDB.update(student);
            
            // after insert new student from database, back to the student list
            url = "student";
            isRedirect = true;   
        } else if(action.equals("updatemarks")){
            url = "/WEB-INF/student/add.jsp";
            String code = request.getParameter("code");
            Student student = StudentDB.getStudent(code);
            List<StudentClass> studentClasses = StudentClassDB.getAll();
            request.setAttribute("student", student);
            request.setAttribute("studentClasses", studentClasses);
            request.setAttribute("action", "updatemarks");
        } else if(action.equals("marked")){
            String code = request.getParameter("code");
            Double theory = Double.parseDouble(request.getParameter("theory"));
            Double practice = Double.parseDouble(request.getParameter("practice"));
//            Student student = StudentDB.getStudent(code);
//            Student studentAndMark = new Student(code, student.getFirstName(), student.getLastName(),
//                   student.getBirthDate(), student.getGender(), student.getAddress(), theory, practice, student.getStudentClass());
            StudentDB.updateMark(code, theory, practice);
            url = "student";
            isRedirect = true;
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
    }// </editor-fold>

}

