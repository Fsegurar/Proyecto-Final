package co.edu.unbosque.taller_3;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "createdservlet", value = "/created-servlet")
public class CreatedServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");


    }
}
