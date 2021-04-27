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
import data.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service instancia;
    private UsuarioDAO usuarios;
    private CursoDAO cursos;
    private GrupoDAO grupos;
    
    private Service(){
        this.usuarios = new UsuarioDAO();
        this.cursos = new CursoDAO();
        this.grupos = new GrupoDAO();
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
    
    public void insertarUsuario(Usuario usuario) throws Exception{
        usuarios.signin(usuario);
    }
    
    public void insertarCurso(Curso curso) throws Exception{
        cursos.add(curso);
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
