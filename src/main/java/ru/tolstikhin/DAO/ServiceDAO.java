package ru.tolstikhin.DAO;

import ru.tolstikhin.entity.PriceList;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class ServiceDAO {
    private Connection connection;

    private final String GET_PRICE_LIST = "SELECT s.name, pl.terms_from, pl.price_from " +
            "FROM public.price_list AS pl " +
            "INNER JOIN public.services AS s ON s.id = pl.service_id " +
            "WHERE pl.center_id = ? AND pl.device_id = ?";

    public ServiceDAO(Connection connection) {
        this.connection = connection;
    }

    public LinkedList<PriceList> getPriceList(int centerId, int deviceId) {
        LinkedList<PriceList> priceList = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_PRICE_LIST)) {
            statement.setInt(1, centerId);
            statement.setInt(2, deviceId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String serviceName = resultSet.getString("name");
                String terms = resultSet.getString("terms_from");
                int price = resultSet.getInt("price_from");
                PriceList priceListItem = new PriceList(serviceName, terms, price);
                priceList.add(priceListItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceList;
    }
}
