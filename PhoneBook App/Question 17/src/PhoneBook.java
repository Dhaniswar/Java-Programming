import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class PhoneBook extends JFrame {
    InfoPanel infopanel;
    ButtonPanel buttonPanel;
    DataPanel datapanel;
    TablePanel tablePanel;
    PhoneBook self = this;

    public PhoneBook() {

        setVisible(true);
        setTitle("Phone Book");
        setMinimumSize(new Dimension(400, 400));
        setJMenuBar(getMenu());
        infopanel = new InfoPanel();
        buttonPanel = new ButtonPanel();
        datapanel = new DataPanel();
        tablePanel = new TablePanel();
        add(mainLayout());
        SearchActionHandel();
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
                buttonPanel.getClearbtn().doClick();
            }
        });
        subEdit.setToolTipText("Clear all fields");
        subEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subSearch = new JMenuItem("search");
        subSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.getSearchbtn().doClick();
            }
        });
        subSearch.setToolTipText("Search Data in a Table");
        subSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subAdd = new JMenuItem("Add");
        subAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.getAddbtn().doClick();
            }
        });
        subAdd.setToolTipText("Add data entered in the field");
        subAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        JMenuItem subRemove = new JMenuItem("Remove");
        subRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonPanel.getRemovebtn().doClick();
            }
        });
        subRemove.setToolTipText("Add selected data from table");
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
        EditMenu.add(subSearch);
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

    private void SearchActionHandel() {
        JButton SButton = buttonPanel.getSearchbtn();
        SButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(self, "Search functionality will be supported in the professional version", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void addTableData() {
        JButton aButton = buttonPanel.getAddbtn();
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
                        Object[] data = {firstName, lastName, phone, status};
                        model.addRow(data);
                        JOptionPane.showMessageDialog(self, "Registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        buttonPanel.getClearbtn().doClick();

                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(self, "Invalid number format! you must enter phone in Number Format", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void clearField() {
        JButton clearBtn = buttonPanel.getClearbtn();
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
        JButton removeBtn = buttonPanel.getRemovebtn();
        DefaultTableModel model = tablePanel.getModel();
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dataRow = table.getSelectedRow();

                if (dataRow == -1) {
                    JOptionPane.showMessageDialog(self, "Please, Select First", "Warning", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    model.removeRow(dataRow);
                    buttonPanel.getClearbtn().doClick();
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


    public static void main(String[] args) {
        PhoneBook app = new PhoneBook();

    }

}
