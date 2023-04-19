package ru.tolstikhin.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.tolstikhin.DAO.ServiceCenterDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.ServiceCenter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "SimpleAddressesServlet", value = "/addresses-list")
public class SimpleAddressesServlet extends HttpServlet {

    private ServiceCenterDAO serviceCenterDAO;

    private SQLController sqlController = new SQLController();

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = sqlController.getConnection();
        serviceCenterDAO = new ServiceCenterDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LinkedList<ServiceCenter> serviceCenters;
        try {
            serviceCenters = serviceCenterDAO.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Генерируем HTML-код списка адресов
        StringBuilder sb = new StringBuilder();
        for (ServiceCenter serviceCenter : serviceCenters) {
            sb.append("<li id=\"").append(serviceCenter.getId()).append("\">")
                    .append(serviceCenter.getAddress()).append("</li>");
        }
        // Отправляем HTML-код списка адресов в ответ на GET-запрос
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(sb);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("selectedAddress", request.getParameter("address"));
    }
}
