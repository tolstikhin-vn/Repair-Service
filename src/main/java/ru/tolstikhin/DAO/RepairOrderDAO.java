package ru.tolstikhin.DAO;

import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.RepairOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class RepairOrderDAO {

    private Connection connection;

    private SQLController sqlController = new SQLController();

    private final String GET_ORDERS_FOR_USER = "SELECT ro.id, ro.order_number, ro.completed, ro.rated FROM repair_orders AS ro " +
            "INNER JOIN users_orders_link AS uo ON uo.order_id = ro.id WHERE uo.user_id = ?";

    private final String GET_ALL_ABOUT_ORDER = "SELECT ro.id,  ro.order_number, d.name, ro.device_name, oh.comment, ro.client_phone_number " +
            "FROM repair_orders AS ro " +
            "INNER JOIN devices_types AS d ON d.id = ro.id " +
            "INNER JOIN order_history AS oh ON ro.id = oh.repair_order_id " +
            "WHERE ro.order_number = ? AND oh.repair_status_id = 1 " +
            "ORDER BY id DESC";

    private final String GET_ORDERS_FOR_CENTER = "SELECT ro.order_number, oh.start_datetime FROM repair_orders AS ro " +
            "INNER JOIN order_history AS oh ON ro.id = oh.repair_order_id " +
            "WHERE ro.completed = false AND ro.service_center_id = ? AND oh.repair_status_id = 1 " +
            "ORDER BY oh.start_datetime DESC";

    public RepairOrderDAO(Connection connection) {
        this.connection = connection;
    }

    public RepairOrderDAO() {
    }

    public RepairOrder getByOrderNumber(String orderNumber) {
        try (PreparedStatement stmt = connection.prepareStatement(GET_ALL_ABOUT_ORDER)) {
            stmt.setString(1, orderNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                RepairOrder order = new RepairOrder();
                order.setId(rs.getInt("id"));
                order.setOrderNumber(rs.getString("order_number"));
                order.setDeviceType(rs.getString("name"));
                order.setDeviceName(rs.getString("device_name"));
                order.setDescriptionProblem(rs.getString("comment"));
                order.setClientPhoneNumber(rs.getString("client_phone_number"));
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<RepairOrder> getOrdersForUser(int userId) {
        LinkedList<RepairOrder> orders = new LinkedList<>();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try (PreparedStatement stmt = connection.prepareStatement(GET_ORDERS_FOR_USER)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    RepairOrder order = new RepairOrder();
                    order.setId(rs.getInt("id"));
                    order.setOrderNumber(rs.getString("order_number"));
                    order.setCompleted(rs.getBoolean("completed"));
                    order.setRated(rs.getBoolean("rated"));
                    orders.add(order);
                }
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return orders;
    }

    public LinkedList<RepairOrder> getOrdersForCenter(int serviceCenter) {
        LinkedList<RepairOrder> orders = new LinkedList<>();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try (PreparedStatement stmt = connection.prepareStatement(GET_ORDERS_FOR_CENTER)) {
                stmt.setInt(1, serviceCenter);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    RepairOrder order = new RepairOrder();
                    order.setOrderNumber(rs.getString("order_number"));
                    order.setStartDatetime(rs.getTimestamp("start_datetime").toLocalDateTime());
                    orders.add(order);
                }
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return orders;
    }
}
