/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Usuario;

@WebServlet(name = "ControllerLogin", urlPatterns = {"/ControllerLogin","/presentation/login/login","/presentation/login/logout"})
public class ControllerLogin extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("modelLogin", new ModelLogin()); 
        
        String viewUrl="";
        switch(request.getServletPath()){        
            case "/presentation/login/login":
                viewUrl=this.login(request);
                break;            
            case "/presentation/login/logout":
                viewUrl=this.logout(request);
                break;
        }
        request.getRequestDispatcher(viewUrl).forward( request, response);
    }
    
    private String login(HttpServletRequest request) {
        try{
            Map<String,String> errores =  this.validar(request);
            if(errores.isEmpty()){
                this.updateModel(request);          
                return this.loginAction(request);
            }
            else{
                request.setAttribute("errores", errores);
                return "/presentation/login/login.jsp"; 
            }            
        }
        catch(Exception e){
            return null;             
        }
    }
    
    private Map<String, String> validar(HttpServletRequest request) {
        Map<String,String> errores = new HashMap<>();
        if (request.getParameter("cedulaFld").isEmpty()){
            errores.put("cedulaFld","Cedula requerida");
        }

        if (request.getParameter("claveFld").isEmpty()){
            errores.put("claveFld","Clave requerida");
        }
        return errores;
    }

    private void updateModel(HttpServletRequest request) {
        ModelLogin modelLogin= (ModelLogin) request.getAttribute("modelLogin");
        modelLogin.getUsuario().setId(request.getParameter("cedulaFld"));
        modelLogin.getUsuario().setClave(request.getParameter("claveFld"));
    }

    private String loginAction(HttpServletRequest request) {
        ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
        logic.Service  instance = logic.Service.instance();
        HttpSession session = request.getSession(true);
        try {
            Usuario real = instance.usuarioFind(modelLogin.getUsuario().getId(),modelLogin.getUsuario().getClave());
            session.setAttribute("usuario", real);
            String viewUrl="/presentation/estudiante/show";
            return viewUrl;
        } catch (Exception ex) {
            Map<String,String> errores = new HashMap<>();
            request.setAttribute("errores", errores);
            errores.put("cedulaFld","Identificación o clave incorrectas");
            errores.put("claveFld","Identificación o clave incorrectas");
            return "/presentation/login/login.jsp"; 
        }
    }

    private String logout(HttpServletRequest request) {
        return this.logoutAction(request);
    }
    
    public String logoutAction(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.removeAttribute("usuario");
        session.invalidate();
        return "/CursosLibres/Index.jsp";   
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
