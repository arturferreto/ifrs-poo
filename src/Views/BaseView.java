package Views;

import Views.Person.PersonDetailView;
import Views.Person.PersonView;
import Views.Ride.RideDetailView;
import Views.Ride.RideView;

import javax.swing.*;

public abstract class BaseView extends JFrame {
    protected JMenuBar menuBar = new JMenuBar();

    protected JMenu peopleMenu = new JMenu("Pessoas");
    protected JMenu ridesMenu = new JMenu("Caronas");
    protected JMenu configMenu = new JMenu("Configurações");

    protected JMenuItem peopleMenuItem = new JMenuItem("Lista");
    protected JMenuItem createPeopleMenuItem = new JMenuItem("Cadastrar");
    protected JMenuItem ridesMenuItem = new JMenuItem("Lista");
    protected JMenuItem createRidesMenuItem = new JMenuItem("Cadastrar");

    protected JMenuItem mainMenuItem = new JMenuItem("Menu Principal");
    protected JMenuItem exitMenuItem = new JMenuItem("Sair");

    public BaseView() {
        setupFrame();
        setupNavBar();
        setupListeners();
    }

    public void setupListeners() {
        peopleMenuItem.addActionListener(e -> {
            new PersonView();
            dispose();
        });

        createPeopleMenuItem.addActionListener(e -> {
            new PersonDetailView();
            dispose();
        });

        ridesMenuItem.addActionListener(e -> {
            new RideView();
            dispose();
        });

        createRidesMenuItem.addActionListener(e -> {
            new RideDetailView();
            dispose();
        });

        mainMenuItem.addActionListener(e -> {
            new MainView();
            dispose();
        });

        exitMenuItem.addActionListener(e -> {
            System.exit(0);
        });
    }

    public void setupNavBar() {
        menuBar.add(peopleMenu);
        menuBar.add(ridesMenu);
        menuBar.add(configMenu);

        peopleMenu.add(peopleMenuItem);
        peopleMenu.add(createPeopleMenuItem);

        ridesMenu.add(ridesMenuItem);
        ridesMenu.add(createRidesMenuItem);

        configMenu.add(mainMenuItem);
        configMenu.add(exitMenuItem);

        setJMenuBar(menuBar);
    }

    public void setupFrame() {
        setTitle("Caronas Universitárias");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BaseView() {};
    }
}
