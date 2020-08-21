/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import bussiness.Student;
import bussiness.StudentClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIENMAYXANH
 */
public class StudentDB {

    public static List<Student> getAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;

        ResultSet rs = null;

        String query = "SELECT * FROM SinhVien left join DiemThi on SinhVien.masv = DiemThi.masv";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            List<Student> students = new ArrayList<>();

            while (rs.next()) {
                String code = rs.getString("masv");
                String firstName = rs.getString("ten");
                String lastName = rs.getString("hodem");
                LocalDate birthdate = rs.getDate("ngaysinh").toLocalDate();
                String gender = rs.getString("gioitinh");
                String address = rs.getString("diachi");
                String maLop = rs.getString("malop");
                StudentClass studentClass = StudentClassDB.get(maLop);
                // Get StudentClass object from classId
                Double theory = rs.getDouble("diemlt");
                Double practice = rs.getDouble("diemth");
                Student student = new Student(code, firstName, lastName, birthdate, gender, address, theory, practice, studentClass);

                students.add(student);
            }

            return students;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int insert(Student student) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;

        String query = "INSERT INTO SinhVien(masv, ten, hodem, ngaysinh, gioitinh, diachi, malop) "
                + "VALUES (?,?,?,?,?,?,?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, student.getCode());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setString(4, student.getBirthDate().toString());
            ps.setString(5, student.getGender());
            ps.setString(6, student.getAddress());
            ps.setString(7, student.getStudentClass().getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(Student student) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;

        String query = "UPDATE SinhVien SET "
                + "ten = ?, "
                + "hodem = ?, "
                + "ngaysinh = ?, "
                + "giotinh = ?, "
                + "diachi = ?, "
                + "malop = ?, "
                + "WHERE masv = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getBirthDate().toString());
            ps.setString(4, student.getGender());
            ps.setString(5, student.getAddress());
            ps.setString(6, student.getStudentClass().getId());
            ps.setString(7, student.getCode());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int deleteByCode(String code) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;

        String query = "DELETE FROM SinhVien "
                + "WHERE masv = ? ";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, code);

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

    public static Student getStudent(String code) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM SinhVien "
                + "WHERE masv = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, code);

            rs = ps.executeQuery();

            Student student = null;
            if (rs.next()) {
                student = new Student(code,
                        rs.getString("ten"),
                        rs.getString("hodem"),
                        LocalDate.parse(rs.getString("ngaysinh")),
                        rs.getString("gioitinh"),
                        rs.getString("diachi"),
                        StudentClassDB.get(rs.getString("malop")));
            }
            return student;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static int updateMark(String code, Double theory, Double practice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;

        String query = "UPDATE DiemThi SET "
                + "diemlt = ?, "
                + "diemth = ?, "
                + "WHERE masv = ? ";

        try {
            ps = connection.prepareStatement(query);
            ps.setDouble(1, theory);
            ps.setDouble(2, practice);
            ps.setString(3, code);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
}
