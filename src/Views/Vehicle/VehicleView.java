package Views.Vehicle;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import DAO.VehicleDAO;
import Models.Vehicle;
import Views.BaseView;

public class VehicleView extends BaseView implements ActionListener {
    VehicleDAO dao = new VehicleDAO();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();
    JButton insertButton = new JButton("Incluir");
    JButton updateButton = new JButton("Editar");
    JButton deleteButton = new JButton("Excluir");
    JLabel title = new JLabel("Cadastro de Veículos");
    JTable table;
    JScrollPane scroll;
    String[] columns = {"ID", "ID do Motorista", "Placa", "Cor", "Marca", "Modelo", "Assentos"};
    List<Vehicle> vehicles = new ArrayList<Vehicle>();
    Model model = new Model();

    public VehicleView() {
        super();

        vehicles = dao.findAll();

        table = new JTable(model);
        scroll = new JScrollPane(table);

        title.setFont(new Font("Arial", Font.BOLD, 24));

        header.add(title);

        footer.add(insertButton);
        footer.add(updateButton);
        footer.add(deleteButton);

        insertButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);

        getContentPane().add(header, BorderLayout.PAGE_START);
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(footer, BorderLayout.PAGE_END);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    class Model extends AbstractTableModel{
        @Override
        public int getRowCount() {
            return vehicles.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }
        @Override
        public String getColumnName(int column) {
            return columns[column];
        }

        @Override
        public String getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex) {
                case 0: return String.valueOf(vehicles.get(rowIndex).getId());
                case 1: return String.valueOf(vehicles.get(rowIndex).getPersonId());
                case 2: return vehicles.get(rowIndex).getPlate();
                case 3: return vehicles.get(rowIndex).getColor();
                case 4: return vehicles.get(rowIndex).getBrand();
                case 5: return vehicles.get(rowIndex).getModel();
                case 6: return String.valueOf(vehicles.get(rowIndex).getSeats());
                default: return "";
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Vehicle vehicleSelected = new Vehicle();

        int selectedLine = table.getSelectedRow();

        if (selectedLine != -1) {
            vehicleSelected.setId(Integer.parseInt(table.getValueAt(selectedLine, 0).toString()));
            vehicleSelected.setPersonId(Integer.parseInt(table.getValueAt(selectedLine, 1).toString()));
            vehicleSelected.setPlate(table.getValueAt(selectedLine, 2).toString());
            vehicleSelected.setColor(table.getValueAt(selectedLine, 3).toString());
            vehicleSelected.setBrand(table.getValueAt(selectedLine, 4).toString());
            vehicleSelected.setModel(table.getValueAt(selectedLine, 5).toString());
            vehicleSelected.setSeats(Integer.parseInt(table.getValueAt(selectedLine, 6).toString()));
        }

        if (e.getSource()==insertButton) {
            new VehicleDetailView("Incluir", null);
            this.dispose();
        }

        if (e.getSource()==updateButton) {
            if (selectedLine!= -1) {
                new VehicleDetailView("Editar", vehicleSelected);
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Você deve selecionar um veículo para editar!");
            }
        }

        if (e.getSource()==deleteButton) {
            if (selectedLine!= -1) {
                new VehicleDetailView("Excluir", vehicleSelected);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Você deve selecionar um veículo para excluir!");
            }
        }
    }
}

