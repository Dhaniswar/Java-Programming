import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements AppLayout {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField phone;
    private JCheckBox status;
    private JLabel checkLabel;


    public InfoPanel() {
        setBorder(BorderFactory.createTitledBorder("info"));
        setLayout(new GridLayout(4, 6, 6, 4));

        firstName = new JTextField(20);
        lastName = new JTextField(20);
        phone = new JTextField(20);
        status = new JCheckBox("Private", true);

    }

    @Override
    public JPanel panelUI() {

        add(new JLabel("First Name"));
        add(firstName);

        add(new JLabel("Last Name"));
        add(lastName);

        add(new JLabel("Phone"));
        add(phone);

        add(status);

        return this;
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getPhone() {
        return phone;
    }

    public JCheckBox getStatus() {
        return status;
    }

}
