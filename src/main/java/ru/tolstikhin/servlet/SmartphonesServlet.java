package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.Session;
import ru.tolstikhin.DAO.AddressDAO;
import ru.tolstikhin.DAO.ServiceCenterDAO;
import ru.tolstikhin.DAO.ServiceDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.PriceList;
import ru.tolstikhin.entity.ServiceCenter;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "SmartphonesServlet", value = "/smartphones")
public class SmartphonesServlet extends HttpServlet {

    private ServiceDAO serviceDAO;
    private AddressDAO addressDAO;

    private SQLController sqlController = new SQLController();

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = sqlController.getConnection();
        serviceDAO = new ServiceDAO(connection);
        addressDAO = new AddressDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LinkedList<PriceList> priceLists;
        HttpSession session = request.getSession();
        String address = (String) session.getAttribute("selectedAddress");
        if (address != null) {
            int addressId = addressDAO.getAddressId(address);

            priceLists = serviceDAO.getPriceList(addressId, 1);
            request.setAttribute("priceLists", priceLists);
        }
        request.getRequestDispatcher("/views/smartphones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
