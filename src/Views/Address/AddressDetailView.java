package Views.Address;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import DAO.AddressDAO;
import Models.Address;
import Views.BaseView;

public class AddressDetailView extends BaseView implements ActionListener {
    private AddressDAO dao;
    private String action = "";
    private JButton saveButton = new JButton("Salvar");
    private JButton cancelButton = new JButton("Cancelar");
    JLabel title = new JLabel("Cadastro de Endereços");
    private JLabel labelId = new JLabel("ID");
    private JLabel labelNumber = new JLabel("Número");
    private JLabel labelStreet = new JLabel("Rua");
    private JLabel labelNeighborhood = new JLabel("Bairro");
    private JLabel labelComplement = new JLabel("Complemento?");
    private JLabel labelCity = new JLabel("Cidade");
    private JLabel labelState = new JLabel("Estado");
    private JLabel labelCountry = new JLabel("País");
    private JLabel labelPostalCode = new JLabel("CEP");
    private JPanel header = new JPanel();
    private JPanel body = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    private JPanel footer = new JPanel();
    private JTextField fieldId = new JTextField(11);
    private JTextField fieldNumber = new JTextField(25);
    private JTextField fieldStreet = new JTextField(25);
    private JTextField fieldNeighborhood = new JTextField(20);
    private JTextField fieldComplement = new JTextField(20);
    private JTextField fieldCity = new JTextField(11);
    private JTextField fieldState = new JTextField(11);
    private JTextField fieldCountry = new JTextField(11);
    private JTextField fieldPostalCode = new JTextField(11);

    public AddressDetailView(String action, Address adrs) {
        super();

        this.action = action;
        fieldId.setEditable(false);

        if (adrs == null) {
            adrs = new Address();
        }

        String defaultNumber = "";
        String defaultStreet = "";
        String defaultNeighborhood = "";
        String defaultComplement = "";
        String defaultCity = "";
        String defaultState = "";
        String defaultCountry = "";
        String defaultPostalCode = "";

        if (adrs != null) {
            defaultNumber = adrs.getNumber();
            defaultStreet = adrs.getStreet();
            defaultNeighborhood = adrs.getNeighborhood();
            defaultComplement = adrs.getComplement();
            defaultCity = adrs.getCity();
            defaultState = adrs.getState();
            defaultCountry = adrs.getCountry();
            defaultPostalCode = adrs.getPostalCode();
        }

        if (action.equalsIgnoreCase("Editar") || action.equalsIgnoreCase("Excluir")) {
            fieldId.setText(String.valueOf(adrs.getId()));
            fieldNumber.setText(String.valueOf(adrs.getNumber()));
            fieldStreet.setText(String.valueOf(adrs.getStreet()));
            fieldNeighborhood.setText(String.valueOf(adrs.getNeighborhood()));
            fieldComplement.setText(String.valueOf(adrs.getComplement()));
            fieldCity.setText(String.valueOf(adrs.getCity()));
            fieldState.setText(String.valueOf(adrs.getState()));
            fieldCountry.setText(String.valueOf(adrs.getCountry()));
            fieldPostalCode.setText(String.valueOf(adrs.getPostalCode()));
        } else if (action.equalsIgnoreCase("Incluir")) {
            labelId.setVisible(false);
            fieldId.setVisible(false);

            fieldNumber.setText(adrs.getNumber() != null ? adrs.getNumber() : "");
            fieldStreet.setText(adrs.getStreet() != null ? adrs.getStreet() : "");
            fieldNeighborhood.setText(adrs.getNeighborhood() != null ? adrs.getNeighborhood() : "");
            fieldComplement.setText(adrs.getComplement() != null ? adrs.getComplement() : "");
            fieldCity.setText(adrs.getCity() != null ? adrs.getCity() : "");
            fieldState.setText(adrs.getState() != null ? adrs.getState() : "");
            fieldCountry.setText(adrs.getCountry() != null ? adrs.getCountry() : "");
            fieldPostalCode.setText(adrs.getPostalCode() != null ? adrs.getPostalCode() : "");
        }

        title.setText(title.getText() + " - " + action);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        header.add(title);

        body.add(labelNumber);
        body.add(fieldNumber);

        body.add(labelStreet);
        body.add(fieldStreet);

        body.add(labelNeighborhood);
        body.add(fieldNeighborhood);

        body.add(labelComplement);
        body.add(fieldComplement);

        body.add(labelCity);
        body.add(fieldCity);

        body.add(labelState);
        body.add(fieldState);

        body.add(labelCountry);
        body.add(fieldCountry);

        body.add(labelPostalCode);
        body.add(fieldPostalCode);

        body.add(saveButton);
        body.add(cancelButton);

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
            dao = new AddressDAO();

            Address adr = new Address();

            adr.setNumber(fieldNumber.getText());
            adr.setStreet(fieldStreet.getText());
            adr.setNeighborhood(fieldNeighborhood.getText());
            adr.setComplement(fieldComplement.getText());
            adr.setCity(fieldCity.getText());
            adr.setState(fieldState.getText());
            adr.setCountry(fieldCountry.getText());
            adr.setPostalCode(fieldPostalCode.getText());

            if (action.equalsIgnoreCase("Incluir")) {
                dao.insert(adr);
                new AddressView();
            } else {
                adr.setId(Integer.parseInt(fieldId.getText()));
            }

            if (action.equalsIgnoreCase("Editar")) {
                dao.update(adr);
                new AddressView();
            }

            if (action.equalsIgnoreCase("Excluir")) {
                dao.delete(adr.getId());
                new AddressView();
            }

            this.dispose();
        }

        if (e.getSource() == cancelButton) {
            new AddressView();
        }
    }
}
