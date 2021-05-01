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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Estudiante;
import logic.Grupo;
import logic.GrupoEstudiante;
import logic.Usuario;

@WebServlet(name = "ControllerGrupoEst", urlPatterns = {"/presentation/grupoestudiante/matricula"})
public class ControllerGrupoEst extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelGrupoEst());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/grupoestudiante/matricula": { viewURL = matricula(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }

    private String matricula(HttpServletRequest request){
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        Usuario est = (Usuario)request.getSession().getAttribute("usuario");
        model.getGrupoEstudiante().setEstudiante((Estudiante)est);
        model.getGrupoEstudiante().setNota(0);
        return this.matriculaBD(request);
    }
    
    private String matriculaBD(HttpServletRequest request) {
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        GrupoEstudiante grupoest = model.getGrupoEstudiante();
        String idGru = request.getParameter("gru");
        try{
            Grupo grupo = logic.Service.instancia().buscarGrupo(idGru);
            grupoest.setGrupo(grupo);
            logic.Service.instancia().insertarGrupoEst(grupoest);
            return "/presentation/estudiante/vergrupos.jsp";
        }
        catch(Exception ex){
            return "/presentation/Error.jsp";
        }
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
