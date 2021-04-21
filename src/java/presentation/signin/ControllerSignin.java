/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.signin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControllerSignin", urlPatterns = {"/presentation/signin/show",
                                                      "/presentation/signin/signin"})
public class ControllerSignin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelSignin());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/signin/show": { viewURL = this.showAction(request); break; }
            case "/presentation/signin/signin": { viewURL = ""; break; }
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
        model.getUsuario().setRol("estudiante");
        return "/presentation/signin/signin.jsp";
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
