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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Service {
    private static Service instancia;
    private UsuarioDAO usuarios;
    private CursoDAO cursos;
    private GrupoDAO grupos;
    
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
    
    public Curso buscarCurso(int id) throws Exception{
        Curso curso = cursos.readCurso(id);
        return curso;
    }

    public List<Curso> busquedaCurso(String c) throws Exception {
        List<Curso> cur = cursos.readAll();
        List<Curso> nuevo = new ArrayList<>();
        for(int i = 0; i < cur.size(); i++){
            if(cur.get(i).getNombre().contains(c) || cur.get(i).getTematica().contains(c)){
                nuevo.add(cur.get(i));
            }
        }
        return nuevo;
    }

    public List<Grupo> buscarGrupos(int idCurso) {
        try {
            List<Grupo> gru = grupos.readAll();
            List<Grupo> nuevo = new ArrayList<>();
            for(int i = 0; i < gru.size(); i++){
                if(gru.get(i).getCurso().equals(idCurso)) {
                    nuevo.add(gru.get(i));
                }
            }
            return nuevo;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
