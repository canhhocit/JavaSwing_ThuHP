package DataBase;

import Model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {

    public boolean checkUsernameExists(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // có dòng => tồn tại
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean checkLogin(Account acc){
        String sql = "SELECT * FROM account WHERE username = ? AND passw = ?";
        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int insertAccount(Account acc) {
        String sql = "INSERT INTO account(username, passw) VALUES (?, ?)";

        try (Connection conn = DBConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());

            return ps.executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
