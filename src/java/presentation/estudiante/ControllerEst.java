/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.estudiante;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Estudiante;
import logic.Usuario;

@WebServlet(name = "ControllerEst", urlPatterns = {"/presentation/estudiante/show",
                                                   "/presentation/estudiante/showgru",
                                                   "/presentation/estudiante/historial",
                                                   "/presentation/estudiante/showestudiantes"})
public class ControllerEst extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Usuario est = (Usuario)sesion.getAttribute("usuario");
        request.setAttribute("model", new ModelEst());
        String viewURL; 
        switch(request.getServletPath()){
            case "/presentation/estudiante/show": { viewURL = this.showAction(request); break;}
            case "/presentation/estudiante/showgru": { viewURL = this.showGru(request, est.getId()); break; }
            case "/presentation/estudiante/historial": { viewURL = this.showHist(request); break; }
            case "/presentation/estudiante/showestudiantes": { viewURL = this.showEstudiantes(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request){
        String idGru = request.getParameter("gru");
        if (idGru.equals("null")){
            return "/presentation/curso/matricularshow";
        }
        else{
            return "/presentation/grupoestudiante/matricula?gru=" + idGru;
        }
    }
    
    private String showGru(HttpServletRequest request, String est){
        return "/presentation/grupo/showest?id=" + request.getParameter("id");
    }
    
    private String showHist(HttpServletRequest request) {
        return "/presentation/grupoestudiante/historial";
    }
    
    private String showEstudiantes(HttpServletRequest request) {
        Map<String,String> grupo = new HashMap<String, String>();
        ModelEst model = (ModelEst)request.getAttribute("model");
        String idGrupo = request.getParameter("id");
        int idG = Integer.parseInt(idGrupo);
        List<Estudiante> est = new ArrayList<>();
        try{
            est = logic.Service.instancia().buscarEstudiantes(idG);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        model.setEstudiantes(est);
        request.setAttribute("model", model);
        grupo.put("grupo", idGrupo);
        request.setAttribute("grupo", grupo);
        return "/presentation/profesor/verestudiantes.jsp";
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
