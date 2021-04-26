/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.grupo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Grupo;

@WebServlet(name = "ControllerGrupo", urlPatterns = {"/presentation/grupo/show",
                                                     "/presentation/grupo/matricula",
                                                     "/presentation/grupo/buscar"})
public class ControllerGrupo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelGrupo());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/grupo/show": { viewURL = this.showAction(request); break;}
            case "/presentation/grupo/matricula": { viewURL = this.matricular(request); break; }
            case "/presentation/grupo/buscar": { viewURL = this.buscar(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request) {
        String idCurso = request.getParameter("id");
        int idC = Integer.parseInt(idCurso); 
        ModelGrupo model = (ModelGrupo)request.getAttribute("model");
        List<Grupo> g = new ArrayList<>();
        try {
            g = logic.Service.instancia().buscarGrupos(idC);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if(g!=null){
            model.setGrupos(g);
            request.setAttribute("model", model);
            return "/presentation/grupos/verGrupos.jsp";
        }else{
            return "/presentation/error.jsp";
        }
    }

    private String matricular(HttpServletRequest request) {
        
        return "/presentation/login/login.jsp";
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
