package edu.ifmo.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
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
            writeError(resp.getWriter(), "x не число");
            return;
        }

        try {
            float y = Float.parseFloat(yAsString);
            req.setAttribute("y", y);
        } catch (NumberFormatException e) {
            writeError(resp.getWriter(), "y не число");
            return;
        }

        try {
            float r = Float.parseFloat(rAsString);
            req.setAttribute("r", r);
        } catch (NumberFormatException e) {
            writeError(resp.getWriter(), "радиус не число");
            return;
        }

        getServletContext().getRequestDispatcher("/check-area").forward(req, resp);
    }
    private void writeError(PrintWriter writer, String error) {
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
