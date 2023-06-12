import javax.swing.*;
import java.awt.*;
import java.awt.Color;
public class GUI extends JFrame
{
    public GUI()
    {
        setTitle("GUI");
        setSize(450, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());

        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        Color newColor = new Color(128, 31, 240);
        contentPane.setBackground(newColor);

        JLabel logo = new JLabel("CU");
        logo.setHorizontalAlignment(JLabel.CENTER);
        add(logo);

        JButton enter = new JButton("Entrar");
        logo.setBackground(Color.gray);
        logo.setFont(new Font("Comic Sans MS", Font.BOLD, 50));

    }
    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}

