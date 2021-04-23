/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/
package presentation.profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Profesor;
import logic.Usuario;

@WebServlet(name = "ControllerProfe", urlPatterns = {"/presentation/profesor/show",
                                                     "/presentation/profesor/visualizarprofes"})
public class ControllerProfe extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelProfe());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/profesor/show": { viewURL = this.showAction(request); break;}
            case "/presentation/profesor/visualizarprofes": { viewURL = this.buscar(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request){
        return "/presentation/profesor/grupos.jsp";
    }
    
    private String buscar(HttpServletRequest request) {
        ModelProfe model = (ModelProfe)request.getAttribute("model");
        Profesor p = new Profesor();
        p.setNombre(request.getParameter("buscar"));
        try {
            //model.setProfesor(logic.Service.instancia().busquedaProfe(p));
        } catch (Exception ex) {
            ex.getMessage();
            return "Hubo un error";
        }
        return "/presentation/administrador/verprofesores.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}