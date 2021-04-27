/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/
package presentation.profesor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Grupo;

@WebServlet(name = "ControllerProfe", urlPatterns = {"/presentation/profesor/show",
                                                     "/presentation/profesor/visualizarprofes",
                                                     "/presentation/profesor/buscar"})
public class ControllerProfe extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelProfe());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/profesor/show": { viewURL = showAction(request); break; }
            case "/presentation/profesor/visualizarprofes": { viewURL = this.visualizar(request); break; }
            case "/presentation/profesor/buscar": { viewURL = this.buscar(request); break; } 
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request){
        ModelProfe model = (ModelProfe)request.getAttribute("model");
        try{
            List<Grupo> grupos = logic.Service.instancia().cargarGrupo(model.getProfesor().getId());
            request.setAttribute("grupos", grupos);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return "/presentation/profesor/grupos.jsp";
    }
    
    private String visualizar(HttpServletRequest request){
        ModelProfe model = (ModelProfe)request.getAttribute("model");
        try{
            model.setProfesores(logic.Service.instancia().cargarProfes());
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "/presentation/administrador/verprofesores.jsp";
    }
    
    private String buscar(HttpServletRequest request) {
        ModelProfe model = (ModelProfe)request.getAttribute("model");
        String cadena = request.getParameter("buscar");
        try {
            model.setProfesores(logic.Service.instancia().busquedaProfe(cadena));
        } catch (Exception ex) {
            ex.getMessage();
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