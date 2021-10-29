package edu.ifmo.web;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/check-area")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        float x = (float) req.getAttribute("x");
        float y = (float) req.getAttribute("y");
        float r = (float) req.getAttribute("r");
        boolean doesHit = doesHit(r, x, y);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("<title>Проверка попадания в область</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<table>");
        writer.println("<thead>");
        writer.println("<tr>");
        writer.println("<th>x</th>");
        writer.println("<th>y</th>");
        writer.println("<th>radius</th>");
        writer.println("<th>Попадание</th>");
        writer.println("</tr>");
        writer.println("</thread>");
        writer.println("<tbody>");
        writer.println("<tr>");
        writer.println("<td>" + x + "</td>");
        writer.println("<td>" + y + "</td>");
        writer.println("<td>" + r + "</td>");
        writer.println("<td>" + (doesHit ? "Попадание есть" : "Попадания нет") + "</td>");
        writer.println("</tr>");
        writer.println("</tbody>");
        writer.println("</table>");
        writer.println("</body>");
        writer.println("</html>");
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean doesHit(float r, float x, float y) {
        if (0 <= x && x <= r && -r <= y && y <= 0) {
            return true;
        }

        return false;
    }
}
