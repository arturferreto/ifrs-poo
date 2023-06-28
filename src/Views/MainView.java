package Views;

import javax.swing.*;

public class MainView extends JFrame {
    private JPanel mainPanel;
    private JLabel image;

    public MainView() {
        setContentPane(mainPanel);
        setTitle("Caronas Universitárias");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
