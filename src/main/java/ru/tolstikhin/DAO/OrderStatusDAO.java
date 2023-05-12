package ru.tolstikhin.DAO;

import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.RepairStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderStatusDAO {
    private SQLController sqlController = new SQLController();

    private String GET_STATUSES = "SELECT * FROM public.repair_statuses";

    public OrderStatusDAO() {
    }

    public ArrayList<RepairStatus> getAllStatuses() {
        ArrayList<RepairStatus> repairStatuses = new ArrayList<>();
        try (PreparedStatement stmt = sqlController.getConnection().prepareStatement(GET_STATUSES)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RepairStatus repairStatus = new RepairStatus();
                repairStatus.setId(rs.getInt("id"));
                repairStatus.setName(rs.getString("name"));

                repairStatuses.add(repairStatus);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return repairStatuses;
    }
}
