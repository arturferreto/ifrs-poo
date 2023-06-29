package Views.Vehicle;

import DAO.VehicleDAO;
import Models.Vehicle;
import Views.BaseView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VehicleDetailView extends BaseView implements ActionListener {
    private VehicleDAO dao;
    private String action = "";
    private String[] uf = {"RS", "SC", "PR"};
    private JButton saveButton = new JButton("Salvar");
    private JButton cancelButton = new JButton("Cancelar");
    JLabel titulo = new JLabel("Cadastro de Veículos");
    private JLabel labelId = new JLabel("ID");
    private JLabel labelPersonId = new JLabel("ID do motorista");
    private JLabel labelPlate = new JLabel("Placa");
    private JLabel labelColor = new JLabel("Cor");
    private JLabel labelBrand = new JLabel("Marca");
    private JLabel labelModel = new JLabel("Modelo");
    private JLabel labelSeats= new JLabel("Número de Assentos");
    private JPanel header = new JPanel();
    private JPanel body = new JPanel();
    private JPanel footer = new JPanel();
    private JTextField fieldId = new JTextField(11);
    private JTextField fieldPersonId = new JTextField(11);
    private JTextField fieldPlate = new JTextField(30);
    private JTextField fieldColor = new JTextField(30);
    private JTextField fieldBrand = new JTextField(30);
    private JTextField fieldModel = new JTextField(30);
    private JTextField fieldSeats = new JTextField(10);

    public VehicleDetailView(String action, Vehicle c) {
        super();

        this.action = action;
        fieldId.setEditable(false);

        if (action.equalsIgnoreCase("Editar") || action.equalsIgnoreCase("Excluir")) {
            fieldId.setText(String.valueOf(c.getId()));
            fieldPersonId.setText(String.valueOf(c.getPersonId()));
            fieldPlate.setText(c.getPlate());
            fieldColor.setText(String.valueOf(c.getColor()));
            fieldBrand.setText(c.getBrand());
            fieldModel.setText(c.getModel());
            fieldSeats.setText(String.valueOf(c.getSeats()));
        }

        if (action.equalsIgnoreCase("Incluir")) {
            fieldId.setVisible(false);
            fieldId.setVisible(false);
        }

        titulo.setText(titulo.getText() + " - " + action);
        titulo.setFont(new Font("Arial",Font.BOLD, 32));
        header.add(titulo);

        JPanel personId = new JPanel();
        personId.add(labelPersonId);
        personId.add(fieldPersonId);
        body.add(personId);
        body.add(Box.createVerticalStrut(50));

        JPanel plate = new JPanel();
        plate.add(labelPlate);
        plate.add(fieldPlate);
        body.add(plate);
        body.add(Box.createVerticalStrut(50));

        JPanel color = new JPanel();
        color.add(labelColor);
        color.add(fieldColor);
        body.add(color);
        body.add(Box.createVerticalStrut(50));

        JPanel brand = new JPanel();
        brand.add(labelBrand);
        brand.add(fieldBrand);
        body.add(brand);
        body.add(Box.createVerticalStrut(50));

        JPanel model = new JPanel();
        model.add(labelModel);
        model.add(fieldModel);
        body.add(model);
        body.add(Box.createVerticalStrut(50));

        JPanel seats = new JPanel();
        seats.add(labelSeats);
        seats.add(fieldSeats);
        body.add(seats);
        body.add(Box.createVerticalStrut(50));

        footer.add(saveButton);
        footer.add(cancelButton);

        getContentPane().add(header,BorderLayout.PAGE_START);
        getContentPane().add(body, BorderLayout.CENTER);
        getContentPane().add(footer, BorderLayout.PAGE_END);

        saveButton.addActionListener(this);
        cancelButton.addActionListener(this);
        saveButton.setText(action);

        fieldPersonId.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== saveButton) {
            dao = new VehicleDAO();

            Vehicle veic = new Vehicle();

            veic.setPersonId(Integer.parseInt(fieldPersonId.getText()));
            veic.setPlate(fieldPlate.getText());
            veic.setColor(fieldColor.getText());
            veic.setBrand(fieldBrand.getText());
            veic.setModel(fieldModel.getText());
            veic.setSeats(Integer.parseInt(fieldSeats.getText()));

            if (action.equalsIgnoreCase("Incluir")) {
                dao.insert(veic);
            } else {
                veic.setId(Integer.parseInt(fieldId.getText()));
            }

            if (action.equalsIgnoreCase("Editar")) {
                dao.update(veic);
            }

            if (action.equalsIgnoreCase("Excluir")) {
                dao.delete(veic.getId());
            }

            new VehicleView();
            this.dispose();
        }

        if (e.getSource()== cancelButton) {
            this.dispose();
            new VehicleView();
        }
    }
}
