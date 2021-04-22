/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.signin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Usuario;

@WebServlet(name = "ControllerSignin", urlPatterns = {"/presentation/signin/show",
                                                      "/presentation/signin/signin"})
public class ControllerSignin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelSignin());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/signin/show": { viewURL = this.showAction(request); break; }
            case "/presentation/signin/signin": { viewURL = this.signin(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request){
        ModelSignin model = (ModelSignin)request.getAttribute("model");
        model.getUsuario().setId("");
        model.getUsuario().setNombre("");
        model.getUsuario().setCorreo("");
        model.getUsuario().setTelefono(0);
        model.getUsuario().setClave("");
        model.getUsuario().setRol("");
        return "/presentation/signin/signin.jsp";
    }
    
    private String signin(HttpServletRequest request){
        Map<String, String> errores = this.comprobarErrores(request);
        if(errores.isEmpty()){
            ModelSignin model = (ModelSignin)request.getAttribute("model");
            model.getUsuario().setId(request.getParameter("id"));
            model.getUsuario().setNombre(request.getParameter("nombre"));
            model.getUsuario().setCorreo(request.getParameter("correo"));
            model.getUsuario().setTelefono(Long.valueOf(request.getParameter("telefono")));
            model.getUsuario().setRol("estudiante");
            model.getUsuario().setClave(this.generarPass(4));
            return this.signinBD(request);
        }
        else{
            request.setAttribute("errores", errores);
            return "/presentation/signin/signin.jsp";
        }
    }
    
    private Map<String, String> comprobarErrores(HttpServletRequest request){
        Map<String, String> errores = new HashMap<>();
        if(request.getParameter("id").isEmpty()){
            errores.put("id", "id vacio");
        }
        if(request.getParameter("nombre").isEmpty()){
            errores.put("nombre", "nombre vacio");
        }
        if(request.getParameter("correo").isEmpty()){
            errores.put("correo", "correo vacio");
        }
        if(request.getParameter("telefono").isEmpty()){
            errores.put("telefono", "telefono vacio");
        }
        return errores;
    }
    
    private String signinBD(HttpServletRequest request){
        ModelSignin model = (ModelSignin)request.getAttribute("model");
        try{
            Usuario usuario = model.getUsuario();
            if (!this.validarCredenciales(usuario)){
                throw new Exception();
            }
            logic.Service.instancia().insertarUsuario(usuario);
            return "/index.jsp";
        }
        catch(Exception e){
            Map<String, String> errores = new HashMap<>();
            errores.put("id", "id repetido");
            request.setAttribute("errores", errores);
            return "/presentation/signin/signin.jsp";
        }
    }
    
    private Boolean validarCredenciales(Usuario usuario) throws Exception{
        Usuario usuarioBD;
        String id = usuario.getId();
        usuarioBD = logic.Service.instancia().buscarUsuario(id);
        return usuarioBD == null;
    }
    
    public String generarPass(int len){
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadena = "";
        for (int i = 0; i < len; i++) {
            int indiceAleatorio = ThreadLocalRandom.current().nextInt(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
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