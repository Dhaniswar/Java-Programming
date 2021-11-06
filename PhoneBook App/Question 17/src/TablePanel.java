import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TablePanel extends JPanel implements AppLayout {
    public DefaultTableModel model;
    private JTable table;

    public TablePanel() {
        setBorder(BorderFactory.createTitledBorder("Name"));
        model = new DefaultTableModel();
        String[] headers = {"First Name", "Last Name", "Phone", "Status"};
        model.setColumnIdentifiers(headers);
        table = new JTable(model);
        table.setSelectionBackground(Color.GRAY);
    }

    @Override
    public JPanel panelUI() {
        table.setShowGrid(true);
        JScrollPane scrollable = new JScrollPane(table);
        add(scrollable);
        return this;
    }

    public DefaultTableModel getModel() {
        return (DefaultTableModel) getTable().getModel();
    }

    public JTable getTable() {
        return table;
    }

}
