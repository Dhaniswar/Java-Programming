import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel implements AppLayout {
    private JRadioButton FSName;
    private JRadioButton SFName;
    private ButtonGroup bgroup;

    public DataPanel() {
        setBorder(BorderFactory.createTitledBorder("File As"));
        setLayout(new GridLayout(4, 2));
        FSName = new JRadioButton("Forname,Surname");
        SFName = new JRadioButton("Surname,Forname");
        bgroup = new ButtonGroup();
        bgroup.add(FSName);
        bgroup.add(SFName);
        FSName.setSelected(true);

    }

    @Override
    public JPanel panelUI() {
        add(FSName);
        add(new JLabel());
        add(SFName);
        add(new JLabel());
        return this;
    }

    public JRadioButton getFSName() {
        return FSName;
    }

    public JRadioButton getSFName() {
        return SFName;
    }


}
