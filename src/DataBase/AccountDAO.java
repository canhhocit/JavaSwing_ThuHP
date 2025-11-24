package DataBase;

import Model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public boolean checkUsernameExists(String username) {
        String sql = "SELECT * FROM account WHERE username = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLogin(Account acc) {
        String sql = "SELECT * FROM account WHERE username = ? AND passw = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

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

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM account";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String passw = rs.getString("passw");
                Account acc = new Account(username, passw);
                list.add(acc);
            }

        } catch (Exception e) {
            e.getMessage();
        }

        return list;
    }
    public int updateAccount(Account acc){
        String sql = "UPDATE account SET passw = ? WHERE username = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, acc.getPassword());
            ps.setString(2, acc.getUsername());
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int deleteAccount(String username){
        String sql = "DELETE FROM account WHERE username = ?";
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            return ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

}
