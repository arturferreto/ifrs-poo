package DAO;

import Models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public VehicleDAO() {
        connection = ConfigDAO.getConnection();
    }

    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_vehicles");
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setPersonId(resultSet.getInt("person_id"));
                vehicle.setPlate(resultSet.getString("plate"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setBrand(resultSet.getString("brand"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setSeats(resultSet.getInt("seats"));
                vehicles.add(vehicle);
            }

            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vehicle findOneById(int id) {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM cu_vehicles WHERE id = ? LIMIT 1");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getInt("id"));
                vehicle.setPersonId(resultSet.getInt("person_id"));
                vehicle.setPlate(resultSet.getString("plate"));
                vehicle.setColor(resultSet.getString("color"));
                vehicle.setBrand(resultSet.getString("brand"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setSeats(resultSet.getInt("seats"));

                return vehicle;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Vehicle vehicle) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO cu_vehicles(person_id, plate, color, brand, model, seats) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, vehicle.getPersonId());
            preparedStatement.setString(2, vehicle.getPlate());
            preparedStatement.setString(3, vehicle.getColor());
            preparedStatement.setString(4, vehicle.getBrand());
            preparedStatement.setString(5, vehicle.getModel());
            preparedStatement.setInt(6, vehicle.getSeats());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Vehicle vehicle) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE cu_vehicles SET plate = ?, color = ?, brand = ?, model = ?, seats = ? WHERE id = ?");
            preparedStatement.setString(1, vehicle.getPlate());
            preparedStatement.setString(2, vehicle.getColor());
            preparedStatement.setString(3, vehicle.getBrand());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setInt(5, vehicle.getSeats());
            preparedStatement.setInt(6, vehicle.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cu_vehicles WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

