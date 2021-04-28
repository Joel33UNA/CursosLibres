/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.administrador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Administrador;

@WebServlet(name = "ControllerAdmin", urlPatterns = {"/presentation/administrador/show",
                                                     "/presentation/administrador/showprof",
                                                     "/presentation/administrador/showcur",
                                                     "/presentation/administrador/showgru",
                                                    "/presentation/admministrador/signin"})
public class ControllerAdmin extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Administrador admin = (Administrador)sesion.getAttribute("usuario");
        request.setAttribute("model", new ModelAdmin(admin));
        String viewURL; 
        switch(request.getServletPath()){
            case "/presentation/administrador/show": { viewURL = this.showAction(request); break; }
            case "/presentation/administrador/showprof": { viewURL = this.showSignin(request); break; }
            case "/presentation/administrador/showcur": { viewURL = this.showCur(request); break; }
            case "/presentation/administrador/showgru": { viewURL = this.showGru(request); break; }
            case "/presentation/admministrador/signin": { viewURL = this.signin(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request){
        return "/presentation/curso/visualizarcursoadmin";
    }
    
    private String showSignin(HttpServletRequest request){
        return "/presentation/signin/showprof";
    }
    
    private String showCur(HttpServletRequest request){
        return "/presentation/curso/show";
    }
    
    private String showGru(HttpServletRequest request){
        return "/presentation/grupo/showadm";
    }
    
    private String signin(HttpServletRequest request){
        return "/presentation/signin/signinprof";
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