package DAO;

import Models.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerDAO {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public PassengerDAO() {
        connection = ConfigDAO.getConnection();
    }

    public List<Passenger> findAll() {
        List<Passenger> passengers = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM passengers");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getInt("id"));
                passenger.setId(resultSet.getInt("ride_id"));
                passenger.setId(resultSet.getInt("person_id"));
                passenger.setId(resultSet.getInt("address_id"));
                passenger.setDate(resultSet.getDate("date"));
                passengers.add(passenger);
            }

            return passengers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Passenger findOneById(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM passengers WHERE id = ? LIMIT 1");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getInt("id"));
                passenger.setId(resultSet.getInt("ride_id"));
                passenger.setId(resultSet.getInt("person_id"));
                passenger.setId(resultSet.getInt("address_id"));
                passenger.setDate(resultSet.getDate("date"));

                return passenger;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Passenger passenger) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO passengers(ride_id, person_id, address_id, date) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, passenger.getRideId());
            preparedStatement.setInt(2, passenger.getPersonId());
            preparedStatement.setInt(3, passenger.getAddressId());
            preparedStatement.setDate(4, new java.sql.Date(passenger.getDate().getTime())); // TODO: testar data
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Passenger passenger) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE passengers SET ride_id = ?, person_id = ?, address_id = ?, date = ? WHERE id = ?");
            preparedStatement.setInt(1, passenger.getRideId());
            preparedStatement.setInt(2, passenger.getPersonId());
            preparedStatement.setInt(3, passenger.getAddressId());
            preparedStatement.setDate(4, new java.sql.Date(passenger.getDate().getTime())); // TODO: testar data
            preparedStatement.setInt(5, passenger.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM passengers WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

