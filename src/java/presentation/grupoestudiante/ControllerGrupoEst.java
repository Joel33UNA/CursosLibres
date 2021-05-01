/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.grupoestudiante;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Curso;
import logic.Estudiante;
import logic.GrupoEstudiante;

@WebServlet(name = "ControllerGrupoEst", urlPatterns = {"/presentation/grupoestudiante/historial"})
public class ControllerGrupoEst extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Estudiante est = (Estudiante)sesion.getAttribute("usuario");
        GrupoEstudiante gEst = new GrupoEstudiante(est);
        request.setAttribute("model", new ModelGrupoEst(gEst));
        String viewURL; 
        switch(request.getServletPath()){
            case "/presentation/grupoestudiante/historial": { viewURL = this.historial(request); break;}
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
        
    }
    
    private String historial(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Estudiante est = (Estudiante)sesion.getAttribute("usuario");
        GrupoEstudiante gEst = new GrupoEstudiante(est);
        request.setAttribute("model", new ModelGrupoEst(gEst));
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        String idE = est.getId();
        //List<Curso> cursosEstu = logic.Service.instancia().buscarCursoEst(idE);
        //model.setCursos(cursosEstu); 
        return "/";
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
