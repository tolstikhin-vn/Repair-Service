package ru.tolstikhin.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.PriceList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AddressDAO {

    public AddressDAO(Connection connection) {
        this.connection = connection;
    }
    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private Connection connection;

    private SQLController sqlController = new SQLController();

    private final String SELECT_ID_BY_ADDRESS = "SELECT sc.id FROM service_centers AS sc WHERE sc.address = ?";

    public int getAddressId(String address) {
        int addressId = 0;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ID_BY_ADDRESS)) {
            statement.setString(1, address);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                addressId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressId;
    }
}
