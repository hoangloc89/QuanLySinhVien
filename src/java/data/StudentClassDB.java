/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import bussiness.StudentClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIENMAYXANH
 */
public class StudentClassDB {
    public static List<StudentClass> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        String query = "SELECT * FROM LopHoc";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            List<StudentClass> studentClasses = new ArrayList<>();
            
            while (rs.next()) {
                String maLop = rs.getString("malop");
                String tenLop = rs.getString("tenlop");
                
                StudentClass studentClass = new StudentClass(maLop, tenLop);
                
                studentClasses.add(studentClass);
            }
            
            return studentClasses;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static StudentClass get(String maLop) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM LopHoc "
                     + "WHERE maLop = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, maLop);
            
            rs = ps.executeQuery();
            
            StudentClass studentClass = null;
            if (rs.next()) {
                studentClass = new StudentClass(rs.getString("malop"),
                                rs.getString("tenlop"));
            }
            
            return studentClass;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
