package Views.Address;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import DAO.AddressDAO;
import Models.Address;
import Views.BaseView;

public class AddressView extends BaseView implements ActionListener {
    AddressDAO dao = new AddressDAO();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();
    JButton insertButton = new JButton("Incluir");
    JButton updateButton = new JButton("Editar");
    JButton deleteButton = new JButton("Excluir");
    JLabel titulo = new JLabel("Cadastro de Clientes");
    JTable table;
    JScrollPane scroll;
    String[] colunas = {"ID", "Número","Rua","Bairro","Complemento","Cidade","Estado","País","CEP"};
    List<Address> addresses = new ArrayList<Address>();;
    Model model = new Model();

    public AddressView() {
        super();

        addresses = dao.findAll();

        table = new JTable(model);
        scroll = new JScrollPane(table);

        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        header.add(titulo);

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
            return addresses.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public String getValueAt(int rowIndex, int columnIndex) {
            switch(columnIndex) {
                case 0: return String.valueOf(addresses.get(rowIndex).getId());
                case 1: return addresses.get(rowIndex).getNumber();
                case 2: return addresses.get(rowIndex).getStreet();
                case 3: return addresses.get(rowIndex).getNeighborhood();
                case 4: return addresses.get(rowIndex).getComplement();
                case 5: return addresses.get(rowIndex).getCity();
                case 6: return addresses.get(rowIndex).getState();
                case 7: return addresses.get(rowIndex).getCountry();
                case 8: return addresses.get(rowIndex).getPostalCode();
                default: return "";
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Address selectedAddress = new Address();

        int selectedLine = table.getSelectedRow();

        if (selectedLine != -1) {
            selectedAddress.setId(Integer.parseInt(table.getValueAt(selectedLine, 0).toString()));
            selectedAddress.setNumber(table.getValueAt(selectedLine, 1).toString());
            selectedAddress.setStreet(table.getValueAt(selectedLine, 2).toString());
            selectedAddress.setNeighborhood(table.getValueAt(selectedLine, 3).toString());
            selectedAddress.setComplement(table.getValueAt(selectedLine, 3).toString());
            selectedAddress.setCity(table.getValueAt(selectedLine, 3).toString());
            selectedAddress.setState(table.getValueAt(selectedLine, 3).toString());
            selectedAddress.setCountry(table.getValueAt(selectedLine, 3).toString());
            selectedAddress.setPostalCode(table.getValueAt(selectedLine, 3).toString());
        }

        if (e.getSource()== insertButton) {
            new AddressDetailView("Incluir", null);
            this.dispose();
        }

        if (e.getSource()== updateButton) {
            if (selectedLine!= -1) {
                new AddressDetailView("Editar", selectedAddress);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Você deve serlecionar um cliente para editar!");
            }
        }

        if (e.getSource()== deleteButton) {
            if (selectedLine!= -1) {
                new AddressDetailView("Excluir", selectedAddress);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Você deve serlecionar um cliente para excluir!");
            }
        }
    }
}

