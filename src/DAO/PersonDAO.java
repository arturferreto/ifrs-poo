package DAO;

import Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public PersonDAO() {
        connection = ConfigDAO.getConnection();
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_people");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setPhone(resultSet.getString("phone"));
                person.setDriver(resultSet.getBoolean("is_driver"));
                person.setFavAddressId(resultSet.getInt("fav_address_id"));
                person.setFavVehicleId(resultSet.getInt("fav_vehicle_id"));
                people.add(person);
            }

            return people;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person findOneById(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_people WHERE id = ? LIMIT 1");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setPhone(resultSet.getString("phone"));
                person.setDriver(resultSet.getBoolean("is_driver"));
                person.setFavAddressId(resultSet.getInt("fav_address_id"));
                person.setFavVehicleId(resultSet.getInt("fav_vehicle_id"));

                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Person person) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cu_people(name, email, phone, is_driver) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setBoolean(4, person.isDriver());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Person person) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE cu_people SET name = ?, email = ?, phone = ?, is_driver = ? WHERE id = ?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getPhone());
            preparedStatement.setBoolean(4, person.isDriver());
            preparedStatement.setInt(5, person.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cu_people WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

