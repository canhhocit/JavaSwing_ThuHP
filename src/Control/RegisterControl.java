package Control;

import DataBase.AccountDAO;
import Model.Account;
import View.Login;
import View.Register;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class RegisterControl implements ActionListener {

    private Register re;

    public RegisterControl(Register re) {
        this.re = re;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == re.getBtnBack()) {
            new Login().setVisible(true);
            re.dispose();
        }

        if (e.getSource() == re.getBtnRegister()) {
            registerAcp();
        }
    }

    private void registerAcp() {
        String user = re.getUsername();
        String pass = re.getPassword();
        String confirm = re.getConfirm();

        if (user.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(re, "Không được để trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!pass.equals(confirm)) {
            JOptionPane.showMessageDialog(re, "Mật khẩu xác nhận không khớp!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AccountDAO dao = new AccountDAO();

        if (dao.checkUsernameExists(user)) {
            JOptionPane.showMessageDialog(re, "Username đã tồn tại!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Account acc = new Account(user, pass);

        if (dao.insertAccount(acc) > 0) {
            JOptionPane.showMessageDialog(re, "Đk thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new Login().setVisible(true);
            re.dispose();
        } else {
            JOptionPane.showMessageDialog(re, "Lỗi khi đăng ký!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
