/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/jvswing_ThuHP"; 
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            if (conn != null) {
                System.out.println("Kết nối CSDL thành công!");
            }
        }catch (SQLException ex) {
            System.err.println("Lỗi kết nối CSDL: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Lỗi không xác định khi kết nối CSDL: " + ex.getMessage());
            ex.printStackTrace();
        }
        return conn;
    }
}