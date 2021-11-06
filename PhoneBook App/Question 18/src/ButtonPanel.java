import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel implements AppLayout {
    private JButton clearA;
    private JButton updateB;
    private JButton addC;
    private JButton removeD;



    public ButtonPanel() {
        setLayout(new GridLayout(2, 2));
        clearA = new JButton("Clear");
        updateB = new JButton("Update");
        addC = new JButton("Add");
        removeD = new JButton("Remove");


    }

    @Override
    public JPanel panelUI() {
        add(clearA);

        add(updateB);

        add(addC);

        add(removeD);


        return this;
    }

    //for actionListener
    public JButton getClearBtn() {
        return clearA;
    }


    public JButton getUpdateBtn() {
        return updateB;
    }

    public JButton getAddBtn() {
        return addC;
    }

    public JButton getRemoveBtn() {
        return removeD;
    }

}
