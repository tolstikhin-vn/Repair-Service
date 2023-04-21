package ru.tolstikhin.DAO;

import ru.tolstikhin.entity.RepairOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairOrderDAO {
    private Connection connection;

    public RepairOrderDAO(Connection connection) {
        this.connection = connection;
    }

    public RepairOrder getByOrderNumber(String orderNumber) {
        String sql = "SELECT * FROM repair_orders WHERE order_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orderNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                RepairOrder order = new RepairOrder();
                order.setId(rs.getInt("id"));
                order.setOrderNumber(rs.getString("order_number"));
                order.setServiceCenterId(rs.getInt("service_center_id"));
                order.setDeviceId(rs.getInt("device_id"));
                order.setDescriptionProblem(rs.getString("description_problem"));
                order.setClientPhoneNumber(rs.getString("client_phone_number"));
                order.setCompleted(rs.getBoolean("completed"));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
