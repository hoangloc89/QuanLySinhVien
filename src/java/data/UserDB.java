/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import bussiness.User;
import bussiness.UserRole;
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
public class UserDB {

   public static List<User> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        String query = "SELECT * FROM user";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            List<User> users = new ArrayList<>();
            
            while (rs.next()) {
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String password = "";
                String role = rs.getString("role");
                
                User user = new User(email, firstName, lastName, password, UserRole.valueOf(role));
                
                users.add(user);
            }
            
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        
        String query = "INSERT INTO User(Email, FirstName, LastName, password, role) "
                     + "VALUES (?,?,?,?,?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().name().toUpperCase());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        
        String query = "UPDATE User SET "
                     + "firstName = ?, "
                     + "lastName = ?, "
                     + "password = ?, "
                     + "role = ?, "
                     + "WHERE email = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().name().toUpperCase());
            ps.setString(5, user.getEmail());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int delete(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        
        String query = "DELETE FROM User "
                     + "WHERE email = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT email FROM User "
                     + "WHERE email = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User get(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM User "
                     + "WHERE email = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("email"),
                                rs.getString("firstName"), 
                                rs.getString("lastName"),
                                rs.getString("password"),
                                UserRole.valueOf(rs.getString("role")));
            }
            
            return user;
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
