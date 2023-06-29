package Views.Person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DAO.PersonDAO;
import Models.Person;
import Views.BaseView;

public class PersonDetailView extends BaseView implements ActionListener {
    private PersonDAO dao;
    private String action = "";
    private String[] driver = {"Sim", "Não"};
    private JButton saveButton = new JButton("Salvar");
    private JButton cancelButton = new JButton("Cancelar");
    JLabel title = new JLabel("Cadastro de Clientes");
    private JLabel labelId = new JLabel("ID");
    private JLabel labelName = new JLabel("Nome");
    private JLabel labelEmail = new JLabel("Email");
    private JLabel labelPhone = new JLabel("Telefone");
    private JLabel labelDriver = new JLabel("É motorista?");
    private JLabel labelFavAddress = new JLabel("Endereço Principal");
    private JLabel labelFavVehicle = new JLabel("Veículo Principal");
    private JPanel header = new JPanel();
    private JPanel body = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private JPanel footer = new JPanel();
    private JTextField fieldId = new JTextField(11);
    private JTextField fieldName = new JTextField(25);
    private JTextField fieldEmail = new JTextField(25);
    private JTextField fieldPhone = new JTextField(20);
    private JComboBox<String> fieldDriver= new JComboBox<>(driver);
    private JTextField fieldFavAddress = new JTextField(11);
    private JTextField fieldFavVehicle = new JTextField(11);

    public PersonDetailView(String action, Person persona) {
        super();

        this.action = action;

        fieldId.setEditable(false);

        if (persona == null) {
            persona = new Person();
        }

        String defaultName = "";
        String defaultEmail = "";
        String defaultPhone = "";
        int defaultFavAddressId = 0;
        int defaultFavVehicleId = 0;

        if (persona != null) {
            defaultName = persona.getName();
            defaultEmail = persona.getEmail();
            defaultPhone = persona.getPhone();
            defaultFavAddressId = persona.getFavAddressId();
            defaultFavVehicleId = persona.getFavVehicleId();
        }

        if (action.equalsIgnoreCase("Editar") || action.equalsIgnoreCase("Excluir")) {
            fieldId.setText(String.valueOf(persona.getId()));
            fieldName.setText(String.valueOf(persona.getName()));
            fieldEmail.setText(String.valueOf(persona.getEmail()));
            fieldPhone.setText(String.valueOf(persona.getPhone()));
            fieldFavAddress.setText(String.valueOf(persona.getFavAddressId()));
            fieldFavVehicle.setText(String.valueOf(persona.getFavVehicleId()));
        } else if (action.equalsIgnoreCase("Incluir")) {
            labelId.setVisible(false);
            fieldId.setVisible(false);

            labelFavAddress.setVisible(false);
            fieldFavAddress.setVisible(false);

            labelFavVehicle.setVisible(false);
            fieldFavVehicle.setVisible(false);

            if (persona != null) {
                defaultName = persona.getName();
                defaultEmail = persona.getEmail();
                defaultPhone = persona.getPhone();
                defaultFavAddressId = persona.getFavAddressId();
                defaultFavVehicleId = persona.getFavVehicleId();
            }

            fieldName.setText(defaultName != null ? defaultName : "");
            fieldEmail.setText(defaultEmail != null ? defaultEmail : "");
            fieldPhone.setText(defaultPhone != null ? defaultPhone : "");
            fieldFavAddress.setText(String.valueOf(defaultFavAddressId));
            fieldFavVehicle.setText(String.valueOf(defaultFavVehicleId));
        }

        title.setText(title.getText() + " - " + action);
        title.setFont(new Font("Arial", Font.BOLD, 32));

        header.add(title);
        body.add(labelId);
        body.add(fieldId);
        body.add(labelName);
        body.add(fieldName);
        body.add(labelEmail);
        body.add(fieldEmail);

        JPanel phone = new JPanel();
        phone.add(labelPhone);
        phone.add(fieldPhone);
        body.add(phone);

        body.add(labelFavAddress);
        body.add(fieldFavAddress);

        body.add(labelFavVehicle);
        body.add(fieldFavVehicle);

        JPanel options = new JPanel();
        options.add(saveButton);
        options.add(cancelButton);
        body.add(options);

        getContentPane().add(header, BorderLayout.PAGE_START);
        getContentPane().add(body, BorderLayout.CENTER);
        getContentPane().add(footer, BorderLayout.PAGE_END);

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        saveButton.setText(action);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            dao = new PersonDAO();

            Person per = new Person();

            per.setName(fieldName.getText());
            per.setEmail(fieldEmail.getText());
            per.setPhone(fieldPhone.getText());
            per.setFavAddressId(Integer.parseInt(fieldFavAddress.getText()));
            per.setFavVehicleId(Integer.parseInt(fieldFavVehicle.getText()));

            if (action.equalsIgnoreCase("Incluir")) {
                dao.insert(per);
                this.dispose();
                new PersonView();
            } else {
                per.setId(Integer.parseInt(fieldId.getText()));
            }

            if (action.equalsIgnoreCase("Editar")) {
                dao.update(per);
                this.dispose();
                new PersonView();
            }

            if (action.equalsIgnoreCase("Excluir")) {
                dao.delete(per.getId());
                this.dispose();
                new PersonView();
            }
        }

        if (e.getSource() == cancelButton) {
            this.dispose();
            new PersonView();
        }
    }
}
