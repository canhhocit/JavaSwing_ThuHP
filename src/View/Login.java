package View;

import Control.LoginControl;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import jdk.jshell.Diag;

public class Login extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblTitle;
    private JLabel lblRegister;
    private JLabel showpass;
    ImageIcon icShow, icHiden;

    public Login() {
        // Frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Login");
        this.setLayout(new BorderLayout());

        icShow = new ImageIcon(getClass().getResource("/imageicon/eyeclose.png"));
        icHiden = new ImageIcon(getClass().getResource("/imageicon/eyeopen.png"));
        showpass = new JLabel(icShow);

        showpass.setToolTipText("Show password");
        showpass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showpass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txtPassword.getEchoChar() == 0) {
                    txtPassword.setEchoChar('*');
                    showpass.setIcon(icShow);
                } else {
                    txtPassword.setEchoChar((char) 0);
                    showpass.setIcon(icHiden);
                }
            }
        });
        // Title
        lblTitle = new JLabel("LOGIN", SwingConstants.CENTER);
        lblTitle.setForeground(Color.red);
        lblTitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
        lblTitle.setPreferredSize(new Dimension(0, 30));
        this.add(lblTitle, BorderLayout.NORTH);

        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        btnLogin = new JButton("Login");
        btnLogin.setToolTipText("Login");
        lblRegister = new JLabel("Register?");
        lblRegister.setToolTipText("Create a new Account");
        lblRegister.setForeground(Color.BLUE);
        lblRegister.setFont(new Font("Arial", Font.ITALIC, 12));
        JPanel pnInput = new JPanel();
        TitledBorder title = new TitledBorder("Input your information");
        pnInput.setBorder(title);

        pnInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnInput.add(lblUsername, gbc);

        gbc.gridx = 1;
        pnInput.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnInput.add(lblPassword, gbc);

        gbc.gridx = 1;
        pnInput.add(txtPassword, gbc);
        gbc.gridx = 2;
        pnInput.add(showpass, gbc);
        this.add(pnInput, BorderLayout.CENTER);
        JPanel pnbtn = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(1, 1, 1, 1);

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        pnbtn.add(btnLogin, gbc2);
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        pnbtn.add(lblRegister, gbc2);
        JPanel pnSouth = new JPanel();
        pnSouth.add(pnbtn);
        this.add(pnSouth, BorderLayout.SOUTH);
        ActionListener ac = new LoginControl(this);
        btnLogin.addActionListener(ac);
        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Register().setVisible(true);
                dispose();
            }
        });

    }

    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
