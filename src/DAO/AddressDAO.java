package DAO;

import Models.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public AddressDAO() {
        connection = ConfigDAO.getConnection();
    }

    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_addresses");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setNumber(resultSet.getString("number"));
                address.setStreet(resultSet.getString("street"));
                address.setNeighborhood(resultSet.getString("neighborhood"));
                address.setComplement(resultSet.getString("complement"));
                address.setCity(resultSet.getString("city"));
                address.setState(resultSet.getString("state"));
                address.setCountry(resultSet.getString("country"));
                address.setPostalCode(resultSet.getString("postal_code"));
                addresses.add(address);
            }

            return addresses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Address findOneById(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_addresses WHERE id = ? LIMIT 1");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setNumber(resultSet.getString("number"));
                address.setStreet(resultSet.getString("street"));
                address.setNeighborhood(resultSet.getString("neighborhood"));
                address.setComplement(resultSet.getString("complement"));
                address.setCity(resultSet.getString("city"));
                address.setState(resultSet.getString("state"));
                address.setCountry(resultSet.getString("country"));
                address.setPostalCode(resultSet.getString("postal_code"));

                return address;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Address address) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cu_addresses(number, street, neighborhood, complement, city, state, country, postal_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, address.getNumber());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNeighborhood());
            preparedStatement.setString(4, address.getComplement());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getState());
            preparedStatement.setString(7, address.getCountry());
            preparedStatement.setString(8, address.getPostalCode());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Address address) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE cu_addresses SET number = ?, street = ?, neighborhood = ?, complement = ?, city = ?, state = ?, country = ?, postal_code = ? WHERE id = ?");
            preparedStatement.setString(1, address.getNumber());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setString(3, address.getNeighborhood());
            preparedStatement.setString(4, address.getComplement());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getState());
            preparedStatement.setString(7, address.getCountry());
            preparedStatement.setString(8, address.getPostalCode());
            preparedStatement.setInt(6, address.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cu_addresses WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

