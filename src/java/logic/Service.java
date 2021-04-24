/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
 */

package logic;

import data.CursoDAO;
import data.UsuarioDAO;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static Service instancia;
    private UsuarioDAO usuarios;
    private CursoDAO cursos;

    
    private Service(){
        this.usuarios = new UsuarioDAO();
        this.cursos = new CursoDAO();
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
    
    public List<Profesor> busquedaProfe(Profesor p) throws Exception{
        List<Profesor> profes = usuarios.readProfes();
        List<Profesor> nuevo = new ArrayList<>();
        for(Profesor profesor : profes){
            if(profesor.getNombre().contains(p.getNombre()) || profesor.getId().contains(p.getId())){
                nuevo.add(profesor);
            }
        }
        return nuevo;
    }

    public List<Curso> busquedaCurso(Curso c) throws Exception {
        List<Curso> cur = cursos.readAll();
        List<Curso> nuevo = new ArrayList<>();
        for(Curso curso : cur){
            if(curso.getNombre().contains(c.getNombre()) || curso.getTematica().contains(c.getNombre())){
                nuevo.add(curso);
            }
        }
        return nuevo;
    }
}
