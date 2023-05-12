package ru.tolstikhin.DAO;

import ru.tolstikhin.controller.SQLController;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class FeedbackDAO {

    private Connection connection;

    private SQLController sqlController = new SQLController();

    private String ADD_FEEDBACK = "call add_feedback(?,?,?,?)";

    public FeedbackDAO() {
    }

    public void addFeedback(int userId, int orderId, int rating, String feedbackText) {
        connection = sqlController.getConnection();
        try {
            if (connection != null) {
                PreparedStatement statement = connection.prepareStatement(ADD_FEEDBACK);
                statement.setInt(1, userId);
                statement.setInt(2, orderId);
                statement.setInt(3, rating);
                statement.setString(4, feedbackText);
                statement.execute();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
