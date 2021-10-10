package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContadorServlet", urlPatterns = {"/ContadorServlet"})
public class ContadorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensaje = null;
        String actividad = "Emergentes II - Uso Contador de Cookies";
        String visita = null;
        int con = 0;

        //obtener el areglo de Cookies (extrae)
        Cookie[] cookies = request.getCookies();
        Cookie conCookies = cuentaCookie("contadorCookies", cookies);

        if (conCookies == null) {

            //Crea la cookie con el valor de 1 en su contadorCookies
            Cookie cookie = new Cookie("contadorCookies", "1");
            cookie.setMaxAge(120);
            response.addCookie(cookie);

            // Imprime la visita
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            mensaje = "Gracias por visitar nuestra pagina ";
            out.println("<h1>" + mensaje + "</h1>");
            out.println("<h2>" + actividad + "</h2>");
            out.println("Visita Nro: 1 <br>");
            out.println("<br><a href='index.jsp'>Ir al Inicio</a>");

        } else {
            //getValue
            //Obtenemos el valor actual del contador
            int cont = Integer.parseInt(conCookies.getValue());
            //Contador se incrementa
            cont++;

            //Modifica la cookie
            Cookie cookie = new Cookie("contadorCookies", "" + cont);
            cookie.setMaxAge(120);
            response.addCookie(cookie);

            // Imprime la visita
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            mensaje = "Estamos agradecidos por tenerlo nuevamente";
            out.println("<h1>" + mensaje + "</h1>");
            out.println("<h2>" + actividad + "</h2>");
            out.println("Visita Nro: " + cont + "<br>");
            out.println("<br><a href='index.jsp'>Ir al Inicio</a>");
        }
    }

    private Cookie cuentaCookie(String nombre,
            Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals(nombre)) {
                return cookies[i];
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
