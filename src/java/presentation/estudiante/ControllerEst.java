/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.estudiante;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Estudiante;

@WebServlet(name = "ControllerEst", urlPatterns = {"/presentation/estudiante/show",
                                                   "/presentation/estudiante/showgru"})
public class ControllerEst extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Estudiante est = (Estudiante)sesion.getAttribute("usuario");
        request.setAttribute("model", new ModelEst(est));
        String viewURL; 
        switch(request.getServletPath()){
            case "/presentation/estudiante/show": { viewURL = this.showAction(request); break;}
            case "/presentation/estudiante/showgru": { viewURL = this.showGru(request, est.getId()); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request){
        return "/presentation/curso/matricularshow";
    }
    
    private String showGru(HttpServletRequest request, String est){
        return "/presentation/grupo/showest?id=" + request.getParameter("id");
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
