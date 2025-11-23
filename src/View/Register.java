package View;

import Control.RegisterControl;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Register extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirm;
    private JButton btnRegister;
    private JButton btnBack;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblConfirm;
    private JLabel lblTitle;
    private JLabel showPass;
    private JLabel showConfirm;
    ImageIcon icShow, icHide;

    public Register() {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(450, 330);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Register");
        this.setLayout(new BorderLayout());

        // Load icon
        icShow = new ImageIcon(getClass().getResource("/imageicon/eyeclose.png"));
        icHide = new ImageIcon(getClass().getResource("/imageicon/eyeopen.png"));

        // Title
        lblTitle = new JLabel("REGISTER", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(lblTitle, BorderLayout.NORTH);

        // Input
        lblUsername = new JLabel("Username:");
        lblPassword = new JLabel("Password:");
        lblConfirm = new JLabel("Confirm:");

        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtConfirm = new JPasswordField(15);

        btnRegister = new JButton("Register");
        btnBack = new JButton("Back");

        showPass = new JLabel(icShow);
        showPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showPass.setToolTipText("Show password");
        showPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txtPassword.getEchoChar() == 0) {
                    txtPassword.setEchoChar('*');
                    showPass.setIcon(icShow);
                } else {
                    txtPassword.setEchoChar((char) 0);
                    showPass.setIcon(icHide);
                }
            }
        });

        // Show password 
        showConfirm = new JLabel(icShow);
        showConfirm.setCursor(new Cursor(Cursor.HAND_CURSOR));
        showConfirm.setToolTipText("Show confirm password");
        showConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txtConfirm.getEchoChar() == 0) {
                    txtConfirm.setEchoChar('*');
                    showConfirm.setIcon(icShow);
                } else {
                    txtConfirm.setEchoChar((char) 0);
                    showConfirm.setIcon(icHide);
                }
            }
        });

        // Center panel
        JPanel pnInput = new JPanel();
        pnInput.setBorder(new TitledBorder("Create new account"));
        pnInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnInput.add(lblUsername, gbc);

        gbc.gridx = 1;
        pnInput.add(txtUsername, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnInput.add(lblPassword, gbc);

        gbc.gridx = 1;
        pnInput.add(txtPassword, gbc);

        gbc.gridx = 2;
        pnInput.add(showPass, gbc);

        // Confirm Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnInput.add(lblConfirm, gbc);

        gbc.gridx = 1;
        pnInput.add(txtConfirm, gbc);

        gbc.gridx = 2;
        pnInput.add(showConfirm, gbc);

        this.add(pnInput, BorderLayout.CENTER);

        // Bottom Panel
        JPanel pnBottom = new JPanel(new FlowLayout());
        pnBottom.add(btnRegister);
        pnBottom.add(btnBack);

        this.add(pnBottom, BorderLayout.SOUTH);
        ActionListener ac = new RegisterControl(this);
        btnRegister.addActionListener(ac);
        btnBack.addActionListener(ac);
    }

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public String getConfirm() {
        return new String(txtConfirm.getPassword());
    }
}
