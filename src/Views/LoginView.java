package Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;

public class LoginView extends JFrame {
    private JButton loginButton;
    private JButton exitButton;
//    private JLabel welcomeLabel;

    public LoginView() {
        initComponents();
        setupUI();
        setupListeners();
        setupImage();

        setTitle("Caronas Universitárias - Entrar");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void setupImage() {
        try {
            Image img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/Public/login.png")));
            JLabel label = new JLabel(new ImageIcon(img));
            getContentPane().add(label);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initComponents() {
        loginButton = new JButton("Entrar");
        loginButton.setPreferredSize(new Dimension(200, 30));

        exitButton = new JButton("Sair");
        exitButton.setPreferredSize(new Dimension(75, 30));

//        welcomeLabel = new JLabel("Bem vindo ao Caronas Universitárias!");
//        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
    }

    private void setupUI() {
//        JPanel headerPanel = new JPanel();
//        headerPanel.add(welcomeLabel);

        JPanel footerPanel = new JPanel();
        footerPanel.add(loginButton);
        footerPanel.add(exitButton);

//        getContentPane().add(headerPanel, BorderLayout.CENTER);
        getContentPane().add(footerPanel, BorderLayout.PAGE_END);
    }

    private void setupListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainView();
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new LoginView();
    }
}