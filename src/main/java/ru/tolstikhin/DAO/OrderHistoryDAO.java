package ru.tolstikhin.DAO;

import ru.tolstikhin.entity.OrderHistory;
import ru.tolstikhin.entity.RepairStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class OrderHistoryDAO {
    private Connection connection;

    private String ADD_NEW_ORDER_STATUS = "call add_order_status(?,?,?)";

    public OrderHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public LinkedList<OrderHistory> getByOrderId(int orderId) {
        String sql = "SELECT oh.id, oh.start_datetime, oh.end_datetime FROM public.order_history AS oh " +
                "INNER JOIN repair_statuses AS rs ON rs.id = oh.repair_status_id " +
                "WHERE oh.repair_order_id = ? ORDER BY oh.id";
        LinkedList<OrderHistory> history = new LinkedList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setId(rs.getInt("id"));

                Timestamp startTimestamp = rs.getTimestamp("start_datetime");
                if (startTimestamp != null) {
                    orderHistory.setStartDatetime(startTimestamp.toLocalDateTime());
                }

                Timestamp endTimestamp = rs.getTimestamp("end_datetime");
                if (endTimestamp != null) {
                    orderHistory.setEndDatetime(endTimestamp.toLocalDateTime());
                }

                orderHistory.setRepairStatus(getStatus(rs.getInt("id")));
                history.add(orderHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    public RepairStatus getStatus(int HistoryItemId) {
        String sql = "SELECT rs.id, rs.name FROM public.repair_statuses AS rs " +
                "INNER JOIN order_history AS oh ON oh.repair_status_id = rs.id " +
                "WHERE oh.id = ?";
        RepairStatus repairStatus = new RepairStatus();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, HistoryItemId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                repairStatus.setId(rs.getInt("id"));
                repairStatus.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repairStatus;
    }

    public void addNewOrderStatus(String orderNumber, int statusId, int employeeId) {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_NEW_ORDER_STATUS)) {
            stmt.setString(1, orderNumber);
            stmt.setInt(2, statusId);
            stmt.setInt(3, employeeId);
            stmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
