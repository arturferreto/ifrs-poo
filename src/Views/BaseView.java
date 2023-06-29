package Views;

import Views.Address.AddressView;
import Views.Person.PersonDetailView;
import Views.Person.PersonView;
import Views.Ride.RideDetailView;
import Views.Ride.RideView;
import Views.Vehicle.VehicleView;

import javax.swing.*;

public abstract class BaseView extends JFrame {
    protected JMenuBar menuBar = new JMenuBar();

    protected JMenu navMenu = new JMenu("Cadastros");
    protected JMenu configMenu = new JMenu("Configurações");

    protected JMenuItem peopleMenuItem = new JMenuItem("Pessoas");
    protected JMenuItem vehiclesMenuItem = new JMenuItem("Veículos");
    protected JMenuItem addressesMenuItem = new JMenuItem("Endereços");

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

        vehiclesMenuItem.addActionListener(e -> {
            new VehicleView();
            dispose();
        });

        addressesMenuItem.addActionListener(e -> {
            new AddressView();
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
        menuBar.add(navMenu);
        menuBar.add(configMenu);

        navMenu.add(peopleMenuItem);
        navMenu.add(vehiclesMenuItem);
        navMenu.add(addressesMenuItem);

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
