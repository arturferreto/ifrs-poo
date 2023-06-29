package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {
    private JButton loginButton;
    private JButton exitButton;
    private JLabel welcomeLabel;

    public LoginView() {
        initComponents();
        setupUI();
        setupListeners();

        setTitle("Caronas Universitárias - Entrar");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
        loginButton = new JButton("Entrar");
        loginButton.setPreferredSize(new Dimension(200, 30));

        exitButton = new JButton("Sair");
        exitButton.setPreferredSize(new Dimension(75, 30));

        welcomeLabel = new JLabel("Bem vindo ao Caronas Universitárias!");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    private void setupUI() {
        JPanel headerPanel = new JPanel();
        headerPanel.add(welcomeLabel);

        JPanel footerPanel = new JPanel();
        footerPanel.add(loginButton);
        footerPanel.add(exitButton);

        getContentPane().add(headerPanel, BorderLayout.CENTER);
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