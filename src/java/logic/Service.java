/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
 */

package logic;

import data.CursoDAO;
import data.GrupoDAO;
import data.GrupoEstudianteDAO;
import data.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service instancia;
    private final UsuarioDAO usuarios;
    private final CursoDAO cursos;
    private final GrupoDAO grupos;
    private final GrupoEstudianteDAO grupoestudiante;
    
    private Service(){
        this.usuarios = new UsuarioDAO();
        this.cursos = new CursoDAO();
        this.grupos = new GrupoDAO();
        this.grupoestudiante = new GrupoEstudianteDAO();
    }
    
    public static Service instancia(){
        if (instancia == null){
            instancia = new Service();
        }
        return instancia;
    }
    
    public Usuario buscarUsuario(String id) throws Exception{
        Usuario usuario = usuarios.readUsuario(id);
        return usuario;
    }
    
    public Profesor buscarProfesor(String id) throws Exception{
        Profesor profesor = usuarios.readProfesor(id);
        return profesor;
    }   
    
    public Estudiante buscarEstudiante(String id) throws Exception{
        Estudiante estudiante = usuarios.readEstudiante(id);
        return estudiante;
    }
    
    public Grupo buscarGrupo(String id) throws Exception{
        Grupo grupo = grupos.readGrupo(Integer.valueOf(id));
        return grupo;
    }
    
    public void insertarUsuario(Usuario usuario) throws Exception{
        this.usuarios.signin(usuario);
    }
    
    public void insertarCurso(Curso curso) throws Exception{
        this.cursos.add(curso);
    }
    
    public void insertarGrupo(Grupo grupo) throws Exception{
        this.grupos.add(grupo);
    }
    
    public void insertarGrupoEst(GrupoEstudiante grupoest) throws Exception{
        this.grupoestudiante.add(grupoest);
    }
    
    public List<Curso> cargarCursos() throws Exception{
        List<Curso> cur = cursos.readAll();
        return cur;
    }
    
    public List<Profesor> cargarProfes() throws Exception {
        List<Profesor> pro = usuarios.readProfes();
        return pro;
    }
    
    public Curso buscarCurso(int id) throws Exception{
        Curso curso = cursos.readCurso(id);
        return curso;
    }
    
    public Grupo buscarGrupo(int id) throws Exception{
        Grupo grupo = grupos.readGrupo(id);
        return grupo;
    }
    
    public List<Grupo> cargarGrupo(String id) throws Exception{
        List<Grupo> grupos = this.grupos.readAll();
        List<Grupo> nuevo = new ArrayList<>();
        for (Grupo grupo : grupos){
            if(grupo.getProfesor() != null && grupo.getProfesor().getId().equals(id)){
                nuevo.add(grupo);
            }
        }
        return nuevo;
    }
    
    public List<Profesor> busquedaProfe(String p) throws Exception{
        List<Profesor> profes = usuarios.readProfes();
        List<Profesor> nuevo = new ArrayList<>();
        for(Profesor profesor : profes){
            if(profesor.getNombre().contains(p)){
                nuevo.add(profesor);
            }
        }
        return nuevo;
    }

    public List<Curso> busquedaCurso(String c) throws Exception {
        List<Curso> cur = cursos.readAll();
        List<Curso> nuevo = new ArrayList<>();
        for(Curso curso: cur){
            if(curso.getNombre().contains(c) || curso.getTematica().contains(c)){
                nuevo.add(curso);
            }
        }
        return nuevo;
    }

    public List<Grupo> buscarGrupos(int idCurso) throws Exception {
        List<Grupo> grups = this.grupos.readAll();
        List<Grupo> nuevo = new ArrayList<>();
        for(Grupo grupo: grups){
            if(grupo.getCurso().getId().equals(idCurso)) {
                nuevo.add(grupo);
            }
        }
        return nuevo;
    }
}
