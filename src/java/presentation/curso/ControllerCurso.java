/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.curso;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import logic.Curso;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet(name = "ControllerCurso", urlPatterns = {"/presentation/curso/visualizarcursos",
                                                     "/presentation/curso/visualizarcursoadmin",
                                                     "/presentation/curso/showcursoadd",
                                                     "/presentation/curso/buscar",
                                                     "/presentation/curso/agregar",
                                                     "/presentation/curso/image",
                                                     "/presentation/curso/matricularshow",
                                                     "/presentation/curso/cambiarEstatus",
                                                     "/presentation/curso/enviarCambioEstatus"})
@MultipartConfig(location="C:/imagenesProyecto")
public class ControllerCurso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelCurso());
        String viewURL;
        switch(request.getServletPath()){
            case "/presentation/curso/visualizarcursos":{ viewURL = this.showAction(request); break;}
            case "/presentation/curso/visualizarcursoadmin": { viewURL = this.showActionAdm(request); break; }
            case "/presentation/curso/showcursoadd": { viewURL = this.showCurAdd(request); break; }
            case "/presentation/curso/buscar": { viewURL = this.buscar(request); break; }
            case "/presentation/curso/agregar": { viewURL = this.agregar(request); break; }
            case "/presentation/curso/image": { viewURL = this.image(request,response); break; }
            case "/presentation/curso/cambiarEstatus": { viewURL = this.cambiarEstatus(request); break; }
            case "/presentation/curso/enviarCambioEstatus": { viewURL = this.enviarCambioEstatus(request); break;}
            case "/presentation/curso/matricularshow": { viewURL = this.matriculaView(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
    }

    private String showAction(HttpServletRequest request){
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        try {
            model.setCursos(logic.Service.instancia().cargarCursos());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "/index.jsp";
    }
    
    private String showActionAdm(HttpServletRequest request){
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        try {
            model.setCursos(logic.Service.instancia().cargarCursos());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "/presentation/administrador/vercursos.jsp";
    }
    
    private String showCurAdd(HttpServletRequest request){
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        model.getCurso().setNombre("");
        model.getCurso().setEstatus("");
        model.getCurso().setTematica("");
        return "/presentation/administrador/cursos.jsp";
    }
    
    private String buscar(HttpServletRequest request) {
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        String cadena = request.getParameter("buscar");
        try {
            model.setCursos(logic.Service.instancia().busquedaCurso(cadena));
        } catch (Exception ex) {
            ex.getMessage();
        }
        return "/index.jsp";
    }
    
    private String agregar(HttpServletRequest request){
        Map<String, String> errores = this.comprobarErrores(request);
        if(errores.isEmpty()){
            ModelCurso model = (ModelCurso)request.getAttribute("model");
            model.getCurso().setNombre(request.getParameter("nombre"));
            model.getCurso().setTematica(request.getParameter("tematica"));
            model.getCurso().setEstatus(request.getParameter("estatus"));
            model.getCurso().setPrecio(Integer.valueOf(request.getParameter("precio")));
            return this.agregarBD(request);
        }
        else{
            request.setAttribute("errores", errores);
            return "/presentation/administrador/cursos.jsp";
        }
    }
    
    private String agregarBD(HttpServletRequest request){
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        try{
            Part imagen;
            Curso curso = model.getCurso();
            if (!this.validarCredenciales(curso)){
                throw new Exception();
            } 
            try {
                imagen = request.getPart("imagen");
                logic.Service.instancia().insertarCurso(curso);            
                //request.setAttribute("cursos", logic.Service.instancia().cargarCursos());
                //model = (ModelCurso)request.getAttribute("model");
                imagen.write(model.getCurso().getNombre());
                return "/presentation/curso/visualizarcursoadmin";
            } catch (Exception ex) {
                return "/presentation/error.jsp";
            }
        }
        catch(Exception e){
            Map<String, String> errores = new HashMap<>();
            errores.put("id", "El ID ya está registrado en el sistema");
            request.setAttribute("errores", errores);
            return "/presentation/signin/signin.jsp";
        }
    }
    
    private Map<String, String> comprobarErrores(HttpServletRequest request){
        Map<String, String> errores = new HashMap<>();
        if(request.getParameter("nombre").isEmpty()){
            errores.put("nombre", "El nombre es inválido");
        }
        if(request.getParameter("tematica").isEmpty()){
            errores.put("tematica", "La temática es inválida");
        }
        if(request.getParameter("estatus").isEmpty()){
            errores.put("estatus", "El estatus es inválido");
        }
        if(request.getParameter("precio").isEmpty() || !request.getParameter("precio").matches("[+-]?\\d*(\\.\\d+)?")){
            errores.put("precio", "El precio es inválido");
        }
        if(request.getParameter("imagen").isEmpty()){
            errores.put("imagen", "No se insertó imagen");
        }
        return errores;
    }
    
    private Boolean validarCredenciales(Curso curso) throws Exception{
        Curso cursoBD;
        int id = curso.getId();
        cursoBD = logic.Service.instancia().buscarCurso(id);
        return cursoBD == null;
    }
    
    private String image(HttpServletRequest request,  HttpServletResponse response) {     
        String codigo = request.getParameter("nombre");
        Path path = FileSystems.getDefault().getPath("C:/imagenesProyecto", codigo);
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(path, out);
            out.flush();
        } catch (IOException e) {
            return "/presentation/error.jsp";
        }
        return null;
    }

    private String matriculaView(HttpServletRequest request) {
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        try {
            model.setCursos(logic.Service.instancia().cargarCursos());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "/presentation/estudiante/vercursos.jsp";
    }
    
    private String cambiarEstatus(HttpServletRequest request) {
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        try {
            model.setCursos(logic.Service.instancia().cargarCursos());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "/presentation/administrador/cambiarEstatus.jsp";
    }
    
    private String enviarCambioEstatus(HttpServletRequest request) {
        ModelCurso model = (ModelCurso)request.getAttribute("model");
        try {
            String idCurso = request.getParameter("id");
            int idC = Integer.valueOf(idCurso);
            logic.Service.instancia().updateEstatus(idC);
            model.setCursos(logic.Service.instancia().cargarCursos());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "/presentation/administrador/cambiarEstatus.jsp";
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
