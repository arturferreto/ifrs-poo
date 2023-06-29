package Views.Person;

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

import DAO.PersonDAO;
import Models.Person;
import Views.BaseView;

public class PersonView extends BaseView implements ActionListener{
    PersonDAO dao = new PersonDAO();
    JPanel header = new JPanel();
    JPanel footer = new JPanel();
    JButton insertButton = new JButton("Incluir");
    JButton updateButton = new JButton("Editar");
    JButton deleteButton = new JButton("Excluir");
    JLabel titulo = new JLabel("Cadastro de Clientes");
    JTable table;
    JScrollPane scroll;
    String[] colunas = {"ID", "Nome", "Email", "Número", "É motorista?", "ID de Endereço Principal", "ID de Veículo Principal"};
    List<Person> people = new ArrayList<Person>();;
    Model model = new Model();

    public PersonView() {
        super();

        people = dao.findAll();

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
            return people.size();
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
                case 0: return String.valueOf(people.get(rowIndex).getId());
                case 1: return people.get(rowIndex).getName();
                case 2: return people.get(rowIndex).getEmail();
                case 3: return people.get(rowIndex).getPhone();
                case 4: return String.valueOf(people.get(rowIndex).isDriver());
                case 5: return String.valueOf(people.get(rowIndex).getFavAddressId());
                case 6: return String.valueOf(people.get(rowIndex).getFavVehicleId());
                default: return "";
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Person selectedPerson = new Person();

        int selectedLine = table.getSelectedRow();

        if (selectedLine != -1) {
            selectedPerson.setId(Integer.parseInt(table.getValueAt(selectedLine, 0).toString()));
            selectedPerson.setName(table.getValueAt(selectedLine, 1).toString());
            selectedPerson.setEmail(table.getValueAt(selectedLine, 2).toString());
            selectedPerson.setPhone(table.getValueAt(selectedLine, 3).toString());
            selectedPerson.setDriver(Boolean.parseBoolean(table.getValueAt(selectedLine, 4).toString()));
            selectedPerson.setFavAddressId(Integer.parseInt(table.getValueAt(selectedLine, 5).toString()));
            selectedPerson.setFavVehicleId(Integer.parseInt(table.getValueAt(selectedLine, 6).toString()));
        }

        if (e.getSource()== insertButton) {
            new PersonDetailView("Incluir", null);
            this.dispose();
        }

        if (e.getSource()== updateButton) {
            if (selectedLine!= -1) {
                new PersonDetailView("Editar", selectedPerson);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Você deve serlecionar um cliente para editar!");
            }
        }

        if (e.getSource()== deleteButton) {
            if (selectedLine!= -1) {
                new PersonDetailView("Excluir", selectedPerson);
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "Você deve serlecionar um cliente para excluir!");
            }
        }
    }
}

