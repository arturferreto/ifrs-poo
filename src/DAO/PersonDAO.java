package DAO;

import Models.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDAO {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public PersonDAO() {
        connection = DatabaseConfig.getConnection();
    }

    public Person[] index() {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_people");
            resultSet = preparedStatement.executeQuery();
            Person[] people = new Person[100];

            int i = 0;

            while(resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setPhone(resultSet.getString("phone"));
                person.setDriver(resultSet.getBoolean("is_driver"));
                person.setFavAddressId(resultSet.getInt("fav_address_id"));
                person.setFavVehicleId(resultSet.getInt("fav_vehicle_id"));
                people[i] = person;
                i++;
            }

            return people;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Person show(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_people WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Person person = new Person();
            person.setId(resultSet. getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
            person.setPhone(resultSet.getString("phone"));
            person.setDriver(resultSet.getBoolean("is_driver"));
            person.setFavAddressId(resultSet.getInt("fav_address_id"));
            person.setFavVehicleId(resultSet.getInt("fav_vehicle_id"));

            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void store(Person person) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cu_people VALUES (NULL, ?, ?, ?, ?, NULL)");
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
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cu_people WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

