package edu.ifmo.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String xAsString = req.getParameter("x");
        String yAsString = req.getParameter("y");
        String rAsString = req.getParameter("r");

        try {
            float x = Float.parseFloat(xAsString);
            req.setAttribute("x", x);
        } catch (NumberFormatException e) {
            writeError(resp, "x не число");
            return;
        }

        try {
            float y = Float.parseFloat(yAsString);
            req.setAttribute("y", y);
        } catch (NumberFormatException e) {
            writeError(resp, "y не число");
            return;
        }

        try {
            float r = Float.parseFloat(rAsString);
            req.setAttribute("r", r);
        } catch (NumberFormatException e) {
            writeError(resp, "радиус не число");
            return;
        }

        getServletContext().getRequestDispatcher("/check-area").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
    }

    private void writeError(HttpServletResponse resp, String error) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("<title>Ошибка в запросе</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>" + error + "</h1>");
        writer.println("</body>");
        writer.println("</html>");
    }
}
