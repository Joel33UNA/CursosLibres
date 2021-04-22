/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.curso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Curso;

@WebServlet(name = "ControllerCurso", urlPatterns = {"/presentation/visualizarCursos/show", 
                                                     "/presentation/visualizarCursos/buscar"})
public class ControllerCurso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelCurso());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/visualizarCursos/show":{ viewURL = this.showAction(request); break;}
            case "/presentation/visualizarCursos/buscar": { viewURL = this.buscar(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }

    private String showAction(HttpServletRequest request){
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        try {
            model.setCursos(logic.Service.instancia().cargarCursos());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "/presentation/verCursos/verCursos.jsp";
    }

    private String buscar(HttpServletRequest request) {
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        String cadena = request.getParameter("buscar");
        try {
            model.setCursos(logic.Service.instancia().busquedaCurso(cadena));
        } catch (Exception ex) {
            ex.getMessage();
            return "Hubo un error";
        }
        return "/presentation/verCursos/verCursos.jsp";
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
