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

    public OrderHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<OrderHistory> getByOrderId(int orderId) {
        String sql = "SELECT oh.id, oh.start_datetime, oh.end_datetime FROM public.order_history AS oh " +
                "INNER JOIN repair_statuses AS rs ON rs.id = oh.repair_status_id " +
                "WHERE oh.repair_order_id = ?";
        LinkedList<OrderHistory> history = new LinkedList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setId(rs.getInt("id"));
                orderHistory.setStartDatetime(rs.getTimestamp("start_datetime").toLocalDateTime());
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
        String sql = "SELECT rs.id, rs.name FROM public.repair_statuses AS rs\n" +
                "INNER JOIN order_history AS oh ON oh.repair_status_id = rs.id\n" +
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
}
