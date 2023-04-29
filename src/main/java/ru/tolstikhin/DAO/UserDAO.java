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
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedList;

public class UserDAO {

    private static Logger logger = LoggerFactory.getLogger(UserDAO.class);
    private Connection connection;

    private SQLController sqlController = new SQLController();

    private final String SIGN_UP_QUERY = "select new_user(?, ?, NULL, NULL, NULL, NULL, NULL, NULL)";

    private final String LOG_IN_QUERY = "{? = call user_login(?, ?)}";

    private final String GET_MAIN_USER_ROLE = "{? = call get_main_user_role(?)}";

    private final String UPDATE_USER_DATA = "select update_user_data(?, ?, ?)";

    private final String GET_ID_BY_LOGIN = "SELECT u.id FROM users AS u WHERE u.login = ?";

    private final String GET_USER_DATA = "SELECT u.id, u.name, u.surname, ur.id_role FROM users AS u " +
            "INNER JOIN users_role_link AS ur ON ur.id_user = u.id WHERE u.login = ?";

    private final String GET_USERS = "SELECT * FROM users ORDER BY users.id DESC";

    private final String GET_USER_ALL_DATA = "SELECT * FROM users WHERE users.id = ?";
    private final String CHANGE_PASSWORD = "SELECT change_password(?, ?)";

    private final String ADMIN_USER_BAN = "SELECT admin_user_ban(?)";

    private final String ADMIN_USER_UNBAN = "SELECT admin_user_unban(?)";
    private final String USER_SOFT_DEL = "SELECT admin_user_soft_del(?)";
    private final String USER_RESTORE_SOFT_DEL = "SELECT admin_user_restore_soft_del(?)";

    private final String USER_HARD_DEL = "SELECT admin_user_hard_del(?)";

    private final String ADMIN_SET_PASSWORD_FAIL_COUNT_RESET = "SELECT admin_set_password_fail_count_reset(?)";

    private final String INSERT_USER_ORDER_LINK = "INSERT INTO users_orders_link (user_id, order_id) VALUES (?,?)";

    public LinkedList<User> getUsers() {
        LinkedList<User> users = new LinkedList<>();
//        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(GET_USERS);

                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setName(rs.getString("name"));
                    user.setSurname(rs.getString("surname"));
                    user.setUserRoleId(rs.getInt("id_role"));
                    // добавление пользователя в список
                    users.add(user);
                }
                statement.close();
                rs.close();
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return users;
    }

    public ResultSet executeRegQuery(String log, String pass) {
//        SQLController sqlController = new SQLController();
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
//        SQLController sqlController = new SQLController();
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
                user.setSurname(surname);
            }
        }

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

    public User getUserById(int userId) {
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            try {
                PreparedStatement statement = connection.prepareStatement(GET_USER_ALL_DATA);
                statement.setInt(1, userId);
                ResultSet resultSet = statement.executeQuery();
                return resultSetToUser(resultSet);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }

    public User resultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        if (rs.next()) {
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setActive(rs.getBoolean("active"));
            user.setDeleted(rs.getBoolean("deleted"));
            user.setPasswordFailCount(rs.getInt("password_fail_count"));
            user.setDefaultPasswordFailCount(rs.getInt("default_password_fail_count"));
            user.setLastLogin(rs.getString("last_login"));
            user.setLastLogout(rs.getString("last_logout"));
        }
        return user;
    }

    public ResultSet getUserData(String login) {
//        SQLController sqlController = new SQLController();
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
//        SQLController sqlController = new SQLController();
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
//        SQLController sqlController = new SQLController();
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

    public boolean changePassword(int userId, String newPassword) throws SQLException {
//        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setInt(1, userId);
            statement.setString(2, newPassword);
            ResultSet resultSet = statement.executeQuery();
            boolean passwordChanged = false;
            if (resultSet.next()) {
                passwordChanged = resultSet.getBoolean(1);
            }
            statement.close();
            return passwordChanged;
        }
        return false;
    }

    public boolean userBan(int userId) throws SQLException {
//        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(ADMIN_USER_BAN);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            boolean userBanned = false;
            if (resultSet.next()) {
                userBanned = resultSet.getBoolean(1);
            }
            statement.close();
            return userBanned;
        }
        return false;
    }

    public boolean userUnban(int userId) throws SQLException {
//        SQLController sqlController = new SQLController();
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(ADMIN_USER_UNBAN);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            boolean userUnbanned = false;
            if (resultSet.next()) {
                userUnbanned = resultSet.getBoolean(1);
            }
            statement.close();
            return userUnbanned;
        }
        return false;
    }

    public boolean userSoftDel(int userId) throws SQLException {
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(USER_SOFT_DEL);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            boolean userSoftDeleted = false;
            if (resultSet.next()) {
                userSoftDeleted = resultSet.getBoolean(1);
            }
            statement.close();
            return userSoftDeleted;
        }
        return false;
    }

    public boolean userRestoreSoftDel(int userId) throws SQLException {
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(USER_RESTORE_SOFT_DEL);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            boolean userRestoreSoftDeleted = false;
            if (resultSet.next()) {
                userRestoreSoftDeleted = resultSet.getBoolean(1);
            }
            statement.close();
            return userRestoreSoftDeleted;
        }
        return false;
    }

    public boolean userHardDel(int userId) throws SQLException {
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(USER_HARD_DEL);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            boolean userHardDeleted = false;
            if (resultSet.next()) {
                userHardDeleted = resultSet.getBoolean(1);
            }
            statement.close();
            return userHardDeleted;
        }
        return false;
    }

    public boolean setPassFailCountReset(int userId) throws SQLException {
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(ADMIN_SET_PASSWORD_FAIL_COUNT_RESET);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            boolean userHardDeleted = false;
            if (resultSet.next()) {
                userHardDeleted = resultSet.getBoolean(1);
            }
            statement.close();
            return userHardDeleted;
        }
        return false;
    }

    public void updateUserOrderLink(int userId, int orderId) throws SQLException {
        // Проверяем наличие соединения
        connection = sqlController.getConnection();
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(INSERT_USER_ORDER_LINK);
            statement.setInt(1, userId);
            statement.setInt(2, orderId);
            statement.execute();
            statement.close();
        }
    }
}