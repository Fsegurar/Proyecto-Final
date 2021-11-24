package co.edu.unbosque.proyecto_final.servlets;
import co.edu.unbosque.proyecto_final.services.UserAppService;
import co.edu.unbosque.proyecto_final.servlets.pojos.OwnerPOJO;
import co.edu.unbosque.proyecto_final.servlets.pojos.UserAppPOJO;
import co.edu.unbosque.proyecto_final.servlets.pojos.VetPOJO;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "createdservlet", value = "/created-servlet")
public class CreatedServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //no se que debria responder por que creeo que deberia devilverse al index pero si esta repetido el usuario o las contraseñas no coinciden deberia mostrase el alert en el de creacion
        response.setContentType("application/json");

        String username = request.getParameter("username");
        String password1 = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String neighborhood = request.getParameter("neighborhood");

        UserAppService user = new UserAppService();
        for (int i=0;i<user.listUsers().size();i++){
            if (user.listUsers().get(i).getUsername().equalsIgnoreCase(username)  ){
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('Usuario ya existe');");
                out.println("window.location.href = history.back()");
                out.println("</script>");
            }
        }
        if (!password1.equals(password2)){
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('Las contraseñas no coinciden');");
            out.println("window.location.href = history.back()");
            out.println("</script>");
        }
        if (role.equalsIgnoreCase("propietario")){
            OwnerPOJO owner = new OwnerPOJO(name,address,neighborhood);
            UserAppPOJO userapp = new UserAppPOJO(username,password1, email,role,owner);
            // aqui quiero pasar userapp a userappresources
        }else {
            VetPOJO vet = new VetPOJO(name,address,neighborhood);
            UserAppPOJO userAppPOJO = new UserAppPOJO(username,password1,email,role,vet);
            // aqui quiero pasar userapp a userappresources
        }
    }
}
