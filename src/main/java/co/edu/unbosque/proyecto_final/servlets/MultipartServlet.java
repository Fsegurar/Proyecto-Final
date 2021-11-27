package co.edu.unbosque.proyecto_final.servlets;

import java.io.*;

import co.edu.unbosque.proyecto_final.services.PetService;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "multiPartServlet", value = "/multiPartServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class MultipartServlet extends HttpServlet {

    private String UPLOAD_DIRECTORY = "uploads";


    //object initialization
    public void init() {


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Specifying the content type for the response
        response.setContentType("text/html");

        // Getting the file from the propietarios.html and separating it into parts
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        //validating or creating folder
        if (!uploadDir.exists()) uploadDir.mkdir();

        //adding file to the folder
        String fileName = "";
        try {
            for (Part part : request.getParts()) {
                fileName = part.getSubmittedFileName();
                part.write(uploadPath + File.separator + fileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        response.sendRedirect("http://localhost:8080/Taller_5-1.0-SNAPSHOT/propietario.html");


    }

    public void destroy() {
    }
}
