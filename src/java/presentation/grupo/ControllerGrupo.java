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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Curso;
import logic.Grupo;
import logic.Profesor;

@WebServlet(name = "ControllerGrupo", urlPatterns = {"/presentation/grupo/show",
                                                     "/presentation/grupo/showgrupoadd",
                                                     "/presentation/grupo/showadm",
                                                     "/presentation/grupo/matricula",
                                                     "/presentation/grupo/buscar",
                                                     "/presentation/grupo/agregar"})
public class ControllerGrupo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelGrupo());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/grupo/show": { viewURL = this.showAction(request); break; }
            case "/presentation/grupo/showgrupoadd": { viewURL = this.showGruAdd(request); break; }
            case "/presentation/grupo/showadm": { viewURL = this.showActionAdm(request); break; }
            case "/presentation/grupo/matricula": { viewURL = this.matricular(request); break; }
            case "/presentation/grupo/buscar": { viewURL = this.buscar(request); break; }
            case "/presentation/grupo/agregar": { viewURL = this.agregar(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }
    
    private String showAction(HttpServletRequest request) {
        String idCurso = request.getParameter("id");
        int idC = Integer.parseInt(idCurso); 
        ModelGrupo model = (ModelGrupo)request.getAttribute("model");
        request.setAttribute("curso", idCurso);
        model.getGrupo().setCurso(new Curso(idC));
        List<Grupo> g = new ArrayList<>();
        try {
            g = logic.Service.instancia().buscarGrupos(idC);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        model.setGrupos(g);
        request.setAttribute("model", model);
        return "/presentation/grupos/verGrupos.jsp";
    }
    
    private String showGruAdd(HttpServletRequest request){
        ModelGrupo model = (ModelGrupo)request.getAttribute("model");
        String curso = request.getParameter("id");
        int idC = Integer.valueOf(curso);
        model.getGrupo().setCurso(new Curso(idC));
        request.setAttribute("model", model);
        model.getGrupo().setHorario("");
        model.getGrupo().setProfesor(new Profesor());
        return "/presentation/administrador/grupos.jsp";
    }
    
    private String showActionAdm(HttpServletRequest request){
        String idCurso = request.getParameter("id");
        int idC = Integer.parseInt(idCurso); 
        ModelGrupo model = (ModelGrupo)request.getAttribute("model");
        model.getGrupo().setCurso(new Curso(idC));
        List<Grupo> g = new ArrayList<>();
        try {
            g = logic.Service.instancia().buscarGrupos(idC);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        model.setGrupos(g);
        request.setAttribute("model", model);
        return "/presentation/administrador/vergrupos.jsp";
    }

    private String matricular(HttpServletRequest request) {
        return "/presentation/login/login.jsp";
    }

    private String buscar(HttpServletRequest request) {
        return null;
    }
    
    private String agregar(HttpServletRequest request) {
        Map<String, String> errores = this.comprobarErrores(request);
        if(errores.isEmpty()){
            ModelGrupo model = (ModelGrupo)request.getAttribute("model");
            model.getGrupo().setHorario(request.getParameter("horario"));
            model.getGrupo().setProfesor(new Profesor(request.getParameter("profesor")));
            return this.agregarBD(request);
        }
        else{
            request.setAttribute("errores", errores);
            return "/presentation/administrador/grupos.jsp";
        }
    }
    
    private String agregarBD(HttpServletRequest request){
        ModelGrupo model = (ModelGrupo)request.getAttribute("model");
        Grupo grupo = model.getGrupo();
        try {
            int id = Integer.valueOf(request.getParameter("id"));
            grupo.setCurso(logic.Service.instancia().buscarCurso(id));
            if(this.validarCredenciales(grupo)){
                throw new Exception();
            }
            grupo.setProfesor(logic.Service.instancia().buscarProfesor(request.getParameter("profesor")));
            logic.Service.instancia().insertarGrupo(grupo);            
            //request.setAttribute("grupos", logic.Service.instancia().cargarGrupos());
            return "/presentation/administrador/showgru?id=" + id;
        } catch (Exception ex) {
            Map<String, String> errores = new HashMap<>();
            errores.put("profesor", "El ID del profesor no existe en el sistema");
            request.setAttribute("errores", errores);
            return "/presentation/administrador/grupos.jsp";
        }
    }
    
    private Boolean validarCredenciales(Grupo grupo) throws Exception{
        Profesor profesorBD;
        String id = grupo.getProfesor().getId();
        profesorBD = logic.Service.instancia().buscarProfesor(id);
        return profesorBD == null;
    }
    
    private Map<String, String> comprobarErrores(HttpServletRequest request){
        Map<String, String> errores = new HashMap<>();
        if(request.getParameter("horario").isEmpty()){
            errores.put("horario", "El horario es inválido");
        }
        if(request.getParameter("profesor").isEmpty()){
            errores.put("profesor", "El ID del profesor es inválido");
        }
        return errores;
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
