/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.grupoestudiante;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Curso;
import logic.Estudiante;
import logic.Grupo;
import logic.GrupoEstudiante;
import logic.Usuario;


@WebServlet(name = "ControllerGrupoEst", urlPatterns = {"/presentation/grupoestudiante/historial",
                                                        "/presentation/grupoestudiante/matricula",
                                                        "/presentation/grupoestudiante/constancia",
                                                        "/presentation/grupoestudiante/agregarnotas",
                                                        "/presentation/grupoestudiante/setearNota"})
@MultipartConfig(location="C:/imagenesProyecto")
public class ControllerGrupoEst extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("model", new ModelGrupoEst());
        String viewURL; 
        switch(request.getServletPath()){
            case "/presentation/grupoestudiante/historial": { viewURL = this.historial(request); break;}
            case "/presentation/grupoestudiante/matricula": { viewURL = this.matricula(request); break; }
            case "/presentation/grupoestudiante/constancia": {viewURL = this.generarConstancia(request, response); break;}
            case "/presentation/grupoestudiante/agregarnotas": {viewURL = this.agregarNotas(request); break; }
            case "/presentation/grupoestudiante/setearNota": {viewURL = this.setearNota(request); break; }
            default: { viewURL = ""; break; }
        }
        request.getRequestDispatcher(viewURL).forward(request, response);
        
    }
    
    private String historial(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Estudiante est = (Estudiante)sesion.getAttribute("usuario");
        GrupoEstudiante gEst = new GrupoEstudiante(est);
        request.setAttribute("model", new ModelGrupoEst(gEst));
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        String idE = est.getId();
        List<Curso> cursosEstu = null;
        try {
            cursosEstu = logic.Service.instancia().buscarCursoEst(idE);
        } catch (Exception ex) {
            ex.getMessage();
        }
        model.setCursos(cursosEstu); 
        return "/presentation/estudiante/verhistorial.jsp";
    }

    private String matricula(HttpServletRequest request){
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        Usuario est = (Usuario)request.getSession().getAttribute("usuario");
        model.getGrupoEstudiante().setEstudiante((Estudiante)est);
        model.getGrupoEstudiante().setNota(0);
        return this.matriculaBD(request);
    }
    
    private String matriculaBD(HttpServletRequest request) {
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        GrupoEstudiante grupoest = model.getGrupoEstudiante();
        String idGru = request.getParameter("gru");
        try{
            Grupo grupo = logic.Service.instancia().buscarGrupo(idGru);
            if(!logic.Service.instancia().validarMatricula(grupoest)){
                throw new Exception();
            }
            grupoest.setGrupo(grupo);
            logic.Service.instancia().insertarGrupoEst(grupoest);
            return "/presentation/estudiante/historial";
        }
        catch(Exception ex){
            return "/presentation/error.jsp";
        }
    }
    
    
    private String generarConstancia(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        String idEstudiante = request.getParameter("idEst");
        try {
            model.getGrupoEstudiante().setEstudiante(logic.Service.instancia().buscarEstudiante(idEstudiante));
            model.setGrEsts(logic.Service.instancia().buscarGrupoEst(idEstudiante));
            PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()));
            Document document = new Document(pdf, PageSize.A4.rotate());
            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC); 
            document.setMargins(20, 20, 20, 20);
            ImageData data = ImageDataFactory.create("C:/imagenesProyecto/logo.jpg"); 
            Image img = new Image(data); 
            document.add(img);
            document.add(new Paragraph("cursoslibres.com"));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Datos del estudiante:"));
            document.add(new Paragraph("Identificación: " + model.getGrupoEstudiante().getEstudiante().getId()));
            document.add(new Paragraph("Nombre: " + model.getGrupoEstudiante().getEstudiante().getNombre()));
            document.add(new Paragraph("Correo: " + model.getGrupoEstudiante().getEstudiante().getCorreo()));
            document.add(new Paragraph("Teléfono: "+ model.getGrupoEstudiante().getEstudiante().getTelefono()));
            
            document.add(new Paragraph(""));
            document.add(new Paragraph("Cursos llevados por el estudiante:"));
            Table table = new Table(6);
            Cell c; 
            Color bkg = ColorConstants.BLUE;
            Color frg= ColorConstants.WHITE;
            c= new Cell(); c.add(new Paragraph("ID Curso")).setBackgroundColor(bkg).setFontColor(frg); 
            table.addHeaderCell(c);
            c= new Cell(); c.add(new Paragraph("Temática")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c);     
            c= new Cell(); c.add(new Paragraph("Nombre")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c); 
            c= new Cell(); c.add(new Paragraph("Nota")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c); 
            c= new Cell(); c.add(new Paragraph("Condición")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c); 
            c= new Cell(); c.add(new Paragraph("Precio")).setBackgroundColor(bkg).setFontColor(frg);
            table.addHeaderCell(c);                    

            DecimalFormat df = new DecimalFormat("####");
            for(GrupoEstudiante gE:model.getGrEsts()){
                table.addHeaderCell(String.valueOf(gE.getGrupo().getCurso().getId()));
                table.addHeaderCell(String.valueOf(gE.getGrupo().getCurso().getTematica()));
                table.addHeaderCell(String.valueOf(gE.getGrupo().getCurso().getNombre()));
                table.addHeaderCell(String.valueOf(gE.getNota()));
                if(gE.getNota() >= 70){
                    table.addHeaderCell(String.valueOf("Aprobado"));
                }else{
                    table.addHeaderCell(String.valueOf("Reprobado"));
                }
                table.addHeaderCell(String.valueOf(df.format(gE.getGrupo().getCurso().getPrecio())));
            }

            document.add(table);
            document.close();
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline");
            return null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return " ";
        }
    }
    
    
    private String agregarNotas(HttpServletRequest request) {
        String id = request.getParameter("id");
        String gru = request.getParameter("grupo");
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        model.getGrupoEstudiante().getEstudiante().setId(id);
        model.getGrupoEstudiante().getGrupo().setId(Integer.valueOf(gru));
        return "/presentation/profesor/agregarNotas.jsp";
    }
    
    private String setearNota(HttpServletRequest request) {
        ModelGrupoEst model = (ModelGrupoEst)request.getAttribute("model");
        String idE = request.getParameter("id");
        String idG = request.getParameter("grupo");
        int g = Integer.valueOf(idG);
        String nota = request.getParameter("nota");
        int n = Integer.valueOf(nota);
        try {
            if(n<0 || n>100){
                throw new Exception();
            }
            logic.Service.instancia().setearNota(idE,g,n);
        } catch (Exception ex) {
            return "/presentation/error.jsp";
        }
        return "/presentation/estudiante/showestudiantes?id="+idG;
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
