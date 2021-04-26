/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.grupo;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Grupo;

@WebServlet(name = "ControllerGrupo", urlPatterns = {"/presentation/grupos/show",
                                                     "/presentation/grupos/matricula",
                                                     "/presentation/grupos/buscar"})
public class ControllerGrupo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelGrupo());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/grupos/show": { viewURL = this.showAction(request); break;}
            case "/presentation/grupos/matricula": { viewURL = this.matricular(request); break; }
            case "/presentation/grupos/buscar": { viewURL = this.buscar(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request) {
        String idCurso = request.getParameter("id");
        int idC = Integer.parseInt(idCurso); 
        ModelGrupo model = (ModelGrupo)request.getAttribute("model");
        List<Grupo> g = logic.Service.instancia().buscarGrupos(idC);
        if(g!=null){
            model.setGrupos(g);
            request.setAttribute("model", model);
            return "/presentation/grupos/verGrupos.jsp";
        }else{
            return "";
        }
    }

    private String matricular(HttpServletRequest request) {
        return null;
    }

    private String buscar(HttpServletRequest request) {
        return null;
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
