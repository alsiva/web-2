package edu.ifmo.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(value = "/check-area")
public class AreaCheckServlet extends HttpServlet {
    private final List<HitResult> hits = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        float x = (float) req.getAttribute("x");
        float y = (float) req.getAttribute("y");
        float r = (float) req.getAttribute("r");
        boolean doesHit = doesHit(r, x, y);

        HitResult hitResult = new HitResult();
        hitResult.setX(x);
        hitResult.setY(y);
        hitResult.setR(r);
        hitResult.setDoesHit(doesHit);

        hits.add(hitResult);
        getServletContext().setAttribute("hits", hits);

        String accept = req.getHeader("Accept");
        if ("text/plain".equalsIgnoreCase(accept)) {
            resp.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.print(doesHit ? "true" : "false");
            return;
        }

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("<title>Проверка попадания в область</title>");
        writer.println("<link rel=\"stylesheet\" href=\"check-area.css\">");
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
        writer.println("<a href=\"\">Обратно</a>");
        writer.println("</body>");
        writer.println("</html>");
    }

    @SuppressWarnings("RedundantIfStatement")
    private boolean doesHit(float r, float x, float y) {
        if (0 <= x && x <= r/2 && 0 <= y && y <= r) {
            return true;
        }

        if (x <= 0 && y <= 0 && Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)) {
            return true;
        }

        if (x >= 0 && y <= 0 && y >= x - r/2) {
            return true;
        }

        return false;
    }
}
