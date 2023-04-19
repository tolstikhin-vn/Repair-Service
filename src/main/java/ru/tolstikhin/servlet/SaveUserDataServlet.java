package ru.tolstikhin.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.tolstikhin.DAO.UserDAO;
import ru.tolstikhin.entity.User;

import java.io.IOException;

@WebServlet("/save-data")
public class SaveUserDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("first-name");
        String surname = request.getParameter("last-name");
        ObjectNode json = saveData(name, surname, request);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }

    private ObjectNode saveData(String name, String surname, HttpServletRequest request) {
        UserDAO userDAO = new UserDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        boolean success = userDAO.saveUserData(user, name, surname);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        if (success) {
            // добавляем данные пользователя в JSON объект
            node.put("name", user.getName());
            node.put("surname", user.getSurname());
        }
        return node;
    }
}
