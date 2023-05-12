package ru.tolstikhin.DAO;

import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.CenterMetroLink;
import ru.tolstikhin.entity.Metro;
import ru.tolstikhin.entity.RepairOrder;
import ru.tolstikhin.entity.ServiceCenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ServiceCenterDAO {
    private Connection connection;

    private SQLController sqlController = new SQLController();

    private String GET_CENTERS_BY_EMPLOYEE = "SELECT sc.id, sc.address FROM service_centers AS sc " +
            "INNER JOIN employee_center_position_link AS ecl ON ecl.center_id = sc.id " +
            "WHERE ecl.user_id = ?";

    public ServiceCenterDAO(Connection connection) {
        this.connection = connection;
    }

    public ServiceCenterDAO() {}

    public LinkedList<ServiceCenter> getAll() throws SQLException {
        LinkedList<ServiceCenter> result = new LinkedList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM service_centers AS sc " +
                        "INNER JOIN centers_phones_link AS cpl ON sc.id = cpl.center_id " +
                        "INNER JOIN centers_metro_link AS cml ON sc.id = cml.center_id " +
                        "INNER JOIN metro_stations AS m ON cml.metro_id = m.id")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String address = resultSet.getString("address");
                    String phoneNumber = resultSet.getString("phone_number");
                    int metroId = resultSet.getInt("metro_id");
                    int distance = resultSet.getInt("distance");
                    String metroName = resultSet.getString("station_name");

                    ServiceCenter serviceCenter = result.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
                    if (serviceCenter == null) {
                        serviceCenter = new ServiceCenter(id, address, new ArrayList<>(), new ArrayList<>());
                        result.add(serviceCenter);
                    }

                    if (phoneNumber != null) {
                        serviceCenter.getPhoneNumbers().add(phoneNumber);
                    }

                    if (metroId > 0) {
                        CenterMetroLink centerMetroLink = new CenterMetroLink(resultSet.getInt("id"), id, metroId, distance);
                        serviceCenter.getCenterMetroLinks().add(centerMetroLink);
                        centerMetroLink.setMetro(new Metro(metroId, metroName));
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<ServiceCenter> getCentersByEmployee(int idEmployee) {
        ArrayList<ServiceCenter> serviceCenters = new ArrayList<>();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try (PreparedStatement stmt = connection.prepareStatement(GET_CENTERS_BY_EMPLOYEE)) {
                stmt.setInt(1, idEmployee);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    ServiceCenter serviceCenter = new ServiceCenter();
                    serviceCenter.setId(rs.getInt("id"));
                    serviceCenter.setAddress(rs.getString("address"));
                    serviceCenters.add(serviceCenter);
                }
                stmt.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return serviceCenters;
    }
}
