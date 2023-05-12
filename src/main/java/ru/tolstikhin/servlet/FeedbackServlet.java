package ru.tolstikhin.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.tolstikhin.DAO.FeedbackDAO;
import ru.tolstikhin.entity.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "FeedbackServlet", value = "/feedback")
public class FeedbackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String feedbackText = request.getParameter("feedbackText");

        HttpSession session = request.getSession();

        FeedbackDAO feedbackDAO = new FeedbackDAO();
        feedbackDAO.addFeedback(((User) session.getAttribute("user")).getId(), orderId, rating, feedbackText);
        response.sendRedirect("/user");
    }
}
