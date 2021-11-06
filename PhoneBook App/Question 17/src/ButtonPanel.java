import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel implements AppLayout {
    private JButton clear;
    private JButton add;
    private JButton remove;
    private JButton search;


    public ButtonPanel() {
        setLayout(new GridLayout(2, 2));
        clear = new JButton("Clear");
        search = new JButton("Search");
        add = new JButton("Add");
        remove = new JButton("Remove");


    }

    @Override
    public JPanel panelUI() {
        add(clear);

        add(search);

        add(add);

        add(remove);


        return this;
    }

    //for actionListener
    public JButton getClearbtn() {
        return clear;
    }

    public JButton getSearchbtn() {
        return search;
    }

    public JButton getAddbtn() {
        return add;
    }

    public JButton getRemovebtn() {
        return remove;
    }

}
