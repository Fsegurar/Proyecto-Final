package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.UserAppService;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "validationServlet", value = "/validation-servlet")
public class ValidationServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Specifying the content type for the response
        response.setContentType("text/html");

        //Login submitted by the user
        String username = request.getParameter("username");
        String pass = request.getParameter("password");

        UserAppService users = new UserAppService();


        //Validate Login with DataBase info
        for (int i =0;i<users.listUsers().size();i++) {
            if (username.equals(users.listUsers().get(i).getUsername()) && pass.equals(users.listUsers().get(i).getPassword())) {
                String rol = users.listUsers().get(i).getRole();
                if (rol.equals("owner")) {
                    //Set login cookies & redirect
                    Cookie emailCookie = new Cookie("username", username);
                    emailCookie.setMaxAge(3600);
                    Cookie rolCookie = new Cookie("Rol", rol);
                    rolCookie.setMaxAge(3600);
                    response.addCookie(emailCookie);
                    response.addCookie(rolCookie);
                    response.sendRedirect(request.getContextPath() + "/propietario.html");
                } else if (rol.equals("official")) {
                    //Set login cookies & redirect
                    Cookie emailCookie = new Cookie("username", username);
                    emailCookie.setMaxAge(3600);
                    Cookie rolCookie = new Cookie("Rol", rol);
                    rolCookie.setMaxAge(3600);
                    response.addCookie(emailCookie);
                    response.addCookie(rolCookie);
                    response.sendRedirect(request.getContextPath() + "/funcionario.html");
                }else if(rol.equals("vet")){
                    //Set login cookies & redirect
                    Cookie emailCookie = new Cookie("username", username);
                    emailCookie.setMaxAge(3600);
                    Cookie rolCookie = new Cookie("Rol", rol);
                    rolCookie.setMaxAge(3600);
                    response.addCookie(emailCookie);
                    response.addCookie(rolCookie);
                    response.sendRedirect(request.getContextPath() + "/vet.html");
                }
            } else {
                //Sends alert if username or password are wrong
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('Usuario o contraseña incorrectos');");
                out.println("window.location.href = history.back()");
                out.println("</script>");
            }
        }
    }

}