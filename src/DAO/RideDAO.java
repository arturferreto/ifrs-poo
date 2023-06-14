package DAO;

import Models.Ride;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RideDAO {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public RideDAO() {
        connection = DatabaseDAO.getConnection();
    }

    public List<Ride> findAll() {
        List<Ride> rides = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_rides");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Ride ride = new Ride();
                ride.setId(resultSet.getInt("id"));
                ride.setDescription(resultSet.getString("description"));
                ride.setDate(resultSet.getDate("date"));
                ride.setVehicleId(resultSet.getInt("vehicle_id"));
                ride.setPersonId(resultSet.getInt("person_id"));
                ride.setAddressId(resultSet.getInt("address_id"));
                ride.setRideType(resultSet.getInt("ride_type"));
                rides.add(ride);
            }

            return rides;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ride findOneById(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_rides WHERE id = ? LIMIT 1");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Ride ride = new Ride();
                ride.setId(resultSet.getInt("id"));
                ride.setDescription(resultSet.getString("description"));
                ride.setDate(resultSet.getDate("date"));
                ride.setVehicleId(resultSet.getInt("vehicle_id"));
                ride.setPersonId(resultSet.getInt("person_id"));
                ride.setAddressId(resultSet.getInt("address_id"));
                ride.setRideType(resultSet.getInt("ride_type"));

                return ride;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Ride ride) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cu_rides(description, date, vehicle_id, person_id, address_id, ride_type) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, ride.getDescription());
            preparedStatement.setDate(2, new java.sql.Date(ride.getDate().getTime()));
            preparedStatement.setInt(3, ride.getVehicleId());
            preparedStatement.setInt(4, ride.getPersonId());
            preparedStatement.setInt(5, ride.getAddressId());
            preparedStatement.setInt(6, ride.getRideType());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Ride ride) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE cu_rides SET description = ?, date = ?, vehicle_id = ?, person_id = ?, address_id = ?, ride_type = ? WHERE id = ?");
            preparedStatement.setString(1, ride.getDescription());
            preparedStatement.setDate(2, new java.sql.Date(ride.getDate().getTime()));
            preparedStatement.setInt(3, ride.getVehicleId());
            preparedStatement.setInt(4, ride.getPersonId());
            preparedStatement.setInt(5, ride.getAddressId());
            preparedStatement.setInt(6, ride.getRideType());
            preparedStatement.setInt(7, ride.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cu_rides WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

