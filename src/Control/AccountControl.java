package Control;

import DataBase.AccountDAO;
import Model.Account;
import View.HomeMenu;
import View.Register;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AccountControl implements ActionListener {

    private HomeMenu home;
    private JPopupMenu popupMenu;
    private JMenuItem deleteItem;
    private JMenuItem updateItem;
    private JMenuItem viewItem;
    private JMenuItem addItem;

    private DefaultTableModel model;
    private JTable table;

    private AccountDAO dao;

    public AccountControl(HomeMenu home) {
        this.home = home;
        dao = new AccountDAO();
        this.model = home.getTableModel();
        this.table = home.getTableAccount();

        PopupMenu();
        addEvent();
    }

    public void loadDataToTable() {
        model.setRowCount(0);

        List<Account> list = dao.getAllAccount();
        for (Account ac : list) {
            model.addRow(new Object[]{
                ac.getUsername(),
                ac.getPassword()
            });
        }

        table.setModel(model);
    }

    public void PopupMenu() {
        popupMenu = new JPopupMenu();

        updateItem = new JMenuItem("Update");
        deleteItem = new JMenuItem("Delete");
        viewItem = new JMenuItem("View");
        addItem = new JMenuItem("Add");

        popupMenu.add(updateItem);
        popupMenu.add(deleteItem);
        popupMenu.add(viewItem);
        popupMenu.add(addItem);

        table.setComponentPopupMenu(popupMenu);
    }

    public void addEvent() {
        updateItem.addActionListener(this);
        deleteItem.addActionListener(this);
        viewItem.addActionListener(this);
        addItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == updateItem) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(home, "Please select a row");
                return;
            }
            String username = (String) model.getValueAt(row, 0);
            String password = (String) model.getValueAt(row, 1);
            Account acc = new Account(username, password);

            if (dao.updateAccount(acc) > 0) {
                JOptionPane.showMessageDialog(home, "Update successfully");
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(home, "Update failed");
            }
        }

        if (e.getSource() == deleteItem) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(home, "Please select a row");
                return;
            }
            String username = (String) model.getValueAt(row, 0);

            if (dao.deleteAccount(username) > 0) {
                JOptionPane.showMessageDialog(home, "Delete successfully");
                loadDataToTable();
            } else {
                JOptionPane.showMessageDialog(home, "Delete failed");
            }
        }

        if (e.getSource() == viewItem) {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(home, "Please select a row");
                return;
            }

            String username = (String) model.getValueAt(row, 0);
            String password = (String) model.getValueAt(row, 1);

            JOptionPane.showMessageDialog(home,  "Username: " + username + "\nPassword: " + password);
        }

        if (e.getSource() == addItem) {
            new Register().setVisible(true);
            home.dispose();
        }
    }
}
