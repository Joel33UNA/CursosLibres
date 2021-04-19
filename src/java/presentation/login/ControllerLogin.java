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

@WebServlet(name = "LoginController", urlPatterns = {"/presentation/login/show",
                                                     "/presentation/login/login",
                                                     "/presentation/login/show"})
public class ControllerLogin extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelLogin());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/login/show": { viewURL = this.showAction(request); break;}
            case "/presentation/login/login": { viewURL = this.login(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request){
        ModelLogin model = (ModelLogin)request.getAttribute("model");
        model.getUsuario().setId("");
        model.getUsuario().setClave("");
        return "/presentation/login/login.jsp";
    }
    
    private String login(HttpServletRequest request){
        Map<String, String> errores = this.comprobarErrores(request);
        if(errores.isEmpty()){
            ModelLogin model = (ModelLogin)request.getAttribute("model");
            model.getUsuario().setId(request.getParameter("id"));
            model.getUsuario().setClave(request.getParameter("password"));
            return this.loginBD(request);
        }
        else{
            request.setAttribute("errores", errores);
            return "/presentation/login/login.jsp";
        }
    }
    
    private Map<String, String> comprobarErrores(HttpServletRequest request){
        Map<String, String> errores = new HashMap<>();
        if(request.getParameter("id").isEmpty()){
            errores.put("id", "id vacio");
        }
        if(request.getParameter("password").isEmpty()){
            errores.put("password", "password vacio");
        }
        return errores;
    }
    
    private String loginBD(HttpServletRequest request){
        ModelLogin model = (ModelLogin)request.getAttribute("model");
        HttpSession session = request.getSession(true);
        try{
            Usuario usuario = this.validarCredenciales(model.getUsuario());
            session.setAttribute("usuario", usuario);
            return "/presentation/administrador/show";
        }
        catch(Exception e){
            Map<String, String> errores = new HashMap<>();
            errores.put("id", "erroneo");
            errores.put("password", "erroneo");
            request.setAttribute("errores", errores);
            return "/presentation/login/login.jsp";
        }
    }
    
    private Usuario validarCredenciales(Usuario usuario) throws Exception{
        Usuario usuarioBD;
        String id = usuario.getId();
        usuarioBD = logic.Service.instancia().buscarUsuario(id);
        if(!usuarioBD.getClave().equals(usuario.getClave())){
            throw new Exception();
        }
        return usuarioBD;
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
