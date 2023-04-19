package ru.tolstikhin.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tolstikhin.DAO.ServiceCenterDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.ServiceCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "AddressesServlet", value = "/addresses")
public class AddressesServlet extends HttpServlet {
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
        try {
            List<ServiceCenter> serviceCenters = serviceCenterDAO.getAll();
            request.setAttribute("serviceCenters", serviceCenters);
            request.getRequestDispatcher("/views/addresses.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error getting service centers", e);
        }
    }

//    @Override
//    public void destroy() {
//        super.destroy();
//        try {
//            serviceCenterDAO.getConnection().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

