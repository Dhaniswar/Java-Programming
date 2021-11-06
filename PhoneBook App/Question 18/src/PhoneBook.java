import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class PhoneBook extends JFrame {
    InfoPanel infopanel;
    ButtonPanel buttonPanel;
    DataPanel datapanel;
    TablePanel tablePanel;
    PhoneBook self = this;
    UserRegistration reg;

    public PhoneBook() {

        setVisible(true);
        setTitle("Phone Book");
        setMinimumSize(new Dimension(400, 400));
        setJMenuBar(getMenu());
        infopanel = new InfoPanel();
        buttonPanel = new ButtonPanel();
        datapanel = new DataPanel();
        tablePanel = new TablePanel();
        reg = new UserRegistration();
        add(mainLayout());
        refreshTable();
        UpdateActionHandel();
        addTableData();
        clearField();
        removeBtn();
        alterColumn();
        SelectedDataInField();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    public JMenuBar getMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu EditMenu = new JMenu("Edit");
        JMenu HelpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(EditMenu);
        menuBar.add(HelpMenu);

        JMenuItem subFile = new JMenuItem("Exit");
        subFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        subFile.setToolTipText("Click to exit");
        subFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subEdit = new JMenuItem("clear");
        subEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.getClearBtn().doClick();
            }
        });
        subEdit.setToolTipText("Clear all fields");
        subEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subUpdate = new JMenuItem("update");
        subUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.getUpdateBtn().doClick();
            }
        });
        subUpdate.setToolTipText("Update Data in a Table");
        subUpdate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subAdd = new JMenuItem("Add");
        subAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.getAddBtn().doClick();
            }
        });
        subAdd.setToolTipText("Add data entered in the field");
        subAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subRemove = new JMenuItem("Remove");
        subRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.getRemoveBtn().doClick();
            }
        });
        subRemove.setToolTipText("Remove selected data from table");
        subRemove.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subAbout = new JMenuItem("About");
        subAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(self, "Still in trial version", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        subAbout.setToolTipText("About");
        subAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));

        fileMenu.add(subFile);
        EditMenu.add(subEdit);
        EditMenu.add(subUpdate);
        EditMenu.addSeparator();
        EditMenu.add(subAdd);
        EditMenu.add(subRemove);
        HelpMenu.add(subAbout);

        return menuBar;


    }

    private JPanel mainLayout() {
        JPanel mainPanel = new JPanel();


        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridheight = 3;
        mainPanel.add(tablePanel.panelUI(), gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;

        mainPanel.add(infopanel.panelUI(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        mainPanel.add(datapanel.panelUI(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        mainPanel.add(buttonPanel.panelUI(), gbc);

        return mainPanel;
    }


    private void UpdateActionHandel() {
        JTable table = tablePanel.getTable();
        DefaultTableModel model = tablePanel.getModel();
        JButton UButton = buttonPanel.getUpdateBtn();
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                infopanel.getFirstName().setText(model.getValueAt(selectedRow, 0).toString());
                infopanel.getLastName().setText(model.getValueAt(selectedRow, 1).toString());
                infopanel.getPhone().setText(model.getValueAt(selectedRow, 2).toString());

                Boolean check = infopanel.getStatus().isSelected();
                infopanel.getStatus().setSelected(check);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        UButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(self, "You have to select row in table to update.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    String firstName = infopanel.getFirstName().getText().trim();
                    String lastName = infopanel.getLastName().getText().trim();
                    int phone = Integer.parseInt(infopanel.getPhone().getText().trim());
                    String status = infopanel.getStatus().getText();
                    if (firstName.isEmpty() ||lastName.isEmpty() ) {
                        JOptionPane.showMessageDialog(self,"Some of the fields are empty", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                    else {
//                        int id = Integer.parseInt(model.getValueAt(selectedRow,1).toString());
                        reg.update(firstName, lastName, phone, status);
                        refreshTable();
//                        table.setValueAt(firstName, selectedRow,0);
//                        table.setValueAt(lastName, selectedRow,1);
//                        table.setValueAt(phone, selectedRow,2);
//                        table.setValueAt(status, selectedRow,3);


                        JOptionPane.showMessageDialog(self, "Updated successfully", "Success",JOptionPane.INFORMATION_MESSAGE);
                        buttonPanel.getRemoveBtn().doClick();


                    }

                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "Updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }


    private void addTableData() {
        JButton aButton = buttonPanel.getAddBtn();
        DefaultTableModel model = tablePanel.getModel();
        aButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName = infopanel.getFirstName().getText().trim();
                    String lastName = infopanel.getLastName().getText().trim();
                    int phone = Integer.parseInt(infopanel.getPhone().getText().trim());
                    String status = infopanel.getStatus().getText();

                    if (lastName.isEmpty() || firstName.isEmpty()) {
                        JOptionPane.showMessageDialog(self, "All field are required", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        reg.insert(firstName, lastName, phone, status);
                        refreshTable();
//                        Object[] data = {firstName, lastName, phone, status};
//                        model.addRow(data);
                        JOptionPane.showMessageDialog(self, "Registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        buttonPanel.getClearBtn().doClick();

                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "Invalid number format! you must enter phone in Number Format", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void clearField() {
        JButton clearBtn = buttonPanel.getClearBtn();
        JTable table = tablePanel.getTable();
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infopanel.getFirstName().setText("");
                infopanel.getLastName().setText("");
                infopanel.getPhone().setText("");
                infopanel.getStatus().setSelected(true);
                table.clearSelection();
            }
        });
    }

    private void removeBtn() {
        JTable table = tablePanel.getTable();
        JButton removeBtn = buttonPanel.getRemoveBtn();
        DefaultTableModel model = tablePanel.getModel();
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dataRow = table.getSelectedRow();

                if (dataRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please, Select First", "Warning", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int phone = Integer.parseInt(infopanel.getPhone().getText().trim());
                    reg.delete(phone);
                    refreshTable();

//                    model.removeRow(dataRow);
//                    buttonPanel.getClearbtn().doClick();
                }
            }
        });
    }

    private void SelectedDataInField() {
        JTable table = tablePanel.getTable();
        DefaultTableModel model = tablePanel.getModel();
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                infopanel.getFirstName().setText(model.getValueAt(selectedRow, 0).toString());
                infopanel.getLastName().setText(model.getValueAt(selectedRow, 1).toString());
                infopanel.getPhone().setText(model.getValueAt(selectedRow, 2).toString());

                Boolean check = infopanel.getStatus().isSelected();
                infopanel.getStatus().setSelected(check);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void alterColumn() {
        JRadioButton rFsName = datapanel.getFSName();
        JRadioButton rSfName = datapanel.getSFName();
        JTable data = tablePanel.getTable();

        rFsName.setEnabled(false);
        rSfName.setEnabled(true);
        rFsName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rFsName.setEnabled(false);
                rSfName.setEnabled(true);
                data.moveColumn(0, 1);
            }
        });

        rSfName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rSfName.setEnabled(false);
                rFsName.setEnabled(true);
                data.moveColumn(1, 0);
            }
        });
    }

    private void refreshTable() {
        //to remove all the data from JTable.
        tablePanel.getModel().setRowCount(0);
        try{ ResultSet resultSet = reg.get();
            while(resultSet.next()) {
                tablePanel.getModel().addRow(new Object[]{
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("phone"),
                        resultSet.getString("status"),



                });

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) {
        PhoneBook app = new PhoneBook();

    }

}
