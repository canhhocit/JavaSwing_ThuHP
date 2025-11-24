package Control;

import DataBase.AccountDAO;
import Model.Account;
import View.HomeMenu;
import View.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LoginControl implements ActionListener {

    private Login lg;

    public LoginControl(Login lg) {
        this.lg = lg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AccountDAO dao = new AccountDAO();
        if (e.getSource() == lg.getBtnLogin()) {
            String usname = lg.getUsername();
            String passw = lg.getPassword();
            Account acc = new Account(usname, passw);
            if (dao.checkLogin(acc)) {
                new HomeMenu().setVisible(true);
                lg.dispose();
            }else{
                JOptionPane.showMessageDialog(lg, "username or password isn't exists","ERR",JOptionPane.ERROR_MESSAGE);
                System.out.println("nguyen");
            }
        }
    }

}
