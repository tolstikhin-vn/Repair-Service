package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.tolstikhin.DAO.AddressDAO;
import ru.tolstikhin.DAO.ServiceDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.PriceList;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;

@WebServlet(name = "TabletsServlet", value = "/tablets")
public class TabletsServlet extends HttpServlet {

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

            priceLists = serviceDAO.getPriceList(addressId, 2);
            request.setAttribute("priceLists", priceLists);
        }
        request.getRequestDispatcher("/views/tablets.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
