package ru.tolstikhin.DAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private Connection connection;

    private final String SIGN_UP_QUERY = "select new_user(?, ?, NULL, NULL, NULL, NULL, NULL, NULL)";

    private final String LOG_IN_QUERY = "{? = call user_login(?, ?)}";

    private final String GET_MAIN_USER_ROLE = "{? = call get_main_user_role(?)}";

    private final String UPDATE_USER_DATA = "select update_user_data(?, ?, ?)";

    private final String GET_ID_BY_LOGIN = "SELECT u.id FROM users AS u WHERE u.login = ?";

    private final String GET_USER_DATA = "SELECT u.name, u.surname FROM users AS u WHERE u.login = ?";

    public ResultSet executeRegQuery(String log, String pass) {
        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try {
                PreparedStatement pstmt = connection.prepareStatement(SIGN_UP_QUERY);
                pstmt.setString(1, log);
                pstmt.setString(2, pass);
                ResultSet rs = pstmt.executeQuery();
                pstmt.close();
                connection.close();
                return rs;
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    private int getIdByLogin(String login) throws SQLException {
        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(GET_ID_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            int userId = 0;
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            statement.close();
            return userId;
        }
        return 0;
    }

    public boolean saveUserData(User user, String name, String surname) {
        // Добавляем данные для объекта сессии
        if (!name.isBlank()) {
            user.setName(name);
            if (!surname.isBlank()) {
                user.setFam(surname);
            }
        }

        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();

        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(UPDATE_USER_DATA);
                System.out.println(user.getLogin());
                System.out.println(getIdByLogin(user.getLogin()));
                statement.setInt(1, getIdByLogin(user.getLogin()));
                statement.setString(2, name);
                statement.setString(3, surname);
                ResultSet resultSet = statement.executeQuery();
                boolean dataUpdated = false;
                if (resultSet.next()) {
                    dataUpdated = resultSet.getBoolean(1);
                }
                statement.close();
                return dataUpdated;
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return false;
    }

    public ResultSet getUserData(String login) {
        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(GET_USER_DATA);
                statement.setString(1, login);
                return statement.executeQuery();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    public String getMainUserRole(String login) {
        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try {
                CallableStatement stmt = connection.prepareCall(GET_MAIN_USER_ROLE);
                stmt.registerOutParameter(1, Types.VARCHAR); // регистрируем выходной параметр
                stmt.setString(2, login);
                stmt.execute(); // выполняем запрос
                return stmt.getString(1).replaceAll("[()]", ""); // получаем результат из выходного параметра без скобок
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }


    public boolean logIn(String log, String pass) {
        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        try {
            if (connection != null) {
                CallableStatement stmt = connection.prepareCall(LOG_IN_QUERY);
                stmt.registerOutParameter(1, Types.BOOLEAN); // регистрируем выходной параметр
                stmt.setString(2, log);
                stmt.setString(3, pass);
                stmt.execute(); // выполняем запрос
                return stmt.getBoolean(1); // получаем результат из выходного параметра
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}

