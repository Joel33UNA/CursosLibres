/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logic.Curso;
import logic.Estudiante;
import logic.Grupo;
import logic.GrupoEstudiante;
import logic.Profesor;

public class GrupoDAO {
    
    public Grupo readGrupo(int id) throws Exception{
        String sql = "select* from grupos where id=%s";
        sql = String.format(sql, id);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return from(rs);
        }
        else{
            return null;
        }
    }
    
    public List<Estudiante> readEstudiantes(int id) throws Exception{
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "select* from gruposestudiantes ge inner join usuarios u "
                + "on ge.id_estudiante=u.id and id_grupo=%s";
        sql = String.format(sql, id);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        while(rs.next()){
            estudiantes.add(fromEstudiante(rs));
        }
        return estudiantes;
    }
    
    public List<GrupoEstudiante> readGruposEstudiantes(int idGrupo) throws Exception {
        List<GrupoEstudiante> gruposestudiantes = new ArrayList<>();
        String sql = "select* from gruposestudiantes where id_grupo=%s";
        sql = String.format(sql, idGrupo);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        while(rs.next()){
            gruposestudiantes.add(fromGruposEstudiantes(rs));
        }
        return gruposestudiantes;
    }
    
    public List<Grupo> readAll() throws Exception{
        List<Grupo> grupos = new ArrayList<>();
        String sql = "select* from grupos";
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        while(rs.next()){
            grupos.add(from(rs));
        }
        return grupos;
    }
    
    public void add(Grupo g) throws Exception{
        String sql = "insert into grupos (horario, curso, profesor) "
                + "values ('%s', '%s', '%s')";
        sql = String.format(sql, g.getHorario(), g.getCurso().getId(), g.getProfesor().getId());
        PreparedStatement stm1 = Connection.instance().prepareStatement(sql);
        if(Connection.instance().executeUpdate(stm1) == 0){
            throw new Exception("Grupo ya existe");
        }
    }
    
    public Grupo from (ResultSet rs){
        try {
            Curso c = null;
            Profesor p = null;
            try {
                c = buscarCurso(rs.getInt("curso"));
                p = readProfesor(rs.getString("profesor"));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            Grupo r = new Grupo();
            r.setId(rs.getInt("id"));
            r.setCurso(c);
            r.setHorario(rs.getString(("horario")));
            r.setProfesor(p);
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Curso buscarCurso(int id) throws Exception{
        String sql = "select* from cursos where id=%s";
        sql = String.format(sql, id);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return fromCu(rs);
        }
        else{
            return null;
        }
    }
    
    public Curso fromCu (ResultSet rs){
        try {
            Curso r = new Curso();
            r.setId(rs.getInt("id"));
            r.setNombre(rs.getString(("nombre")));
            r.setTematica(rs.getString(("tematica")));
            r.setEstatus(rs.getString(("estatus")));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Profesor readProfesor(String id) throws Exception{
        String sql = "select* from profesores, usuarios where "
                + "profesores.id = usuarios.id and profesores.id=%s";
        sql = String.format(sql,id);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return fromProfesor(rs);
        }
        else{
            throw new Exception("Profesor no existe.");
        }
    }
    
    public Estudiante readEstudiante(String id) throws Exception{
        String sql = "select* from estudiantes, usuarios where "
                + "estudiantes.id = usuarios.id and estudiantes.id=%s";
        sql = String.format(sql,id);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return fromEstudiante(rs);
        }
        else{
            throw new Exception("Estudiante no existe.");
        }
    }
    
    public Profesor fromProfesor(ResultSet rs){
        try{
            Profesor r = new Profesor();
            r.setRol("profesor");
            r.setId(rs.getString("id"));
            r.setClave(rs.getString("clave"));
            r.setNombre(rs.getString("nombre"));
            r.setCorreo(rs.getString("correo"));
            r.setTelefono(rs.getLong("telefono"));
            return r;
        }
        catch(SQLException ex){
            return null;
        }
    }

    private Estudiante fromEstudiante(ResultSet rs) {
        try{
            Estudiante e = new Estudiante();
            e.setRol("estudiante");
            e.setId(rs.getString("id"));
            e.setClave(rs.getString("clave"));
            e.setNombre(rs.getString("nombre"));
            e.setCorreo(rs.getString("correo"));
            e.setTelefono(rs.getLong("telefono"));
            return e;
        }
        catch(SQLException ex){
            return null;
        }
    }

    private GrupoEstudiante fromGruposEstudiantes(ResultSet rs) throws Exception{
        try{
            GrupoEstudiante ge = new GrupoEstudiante();
            ge.setEstudiante(readEstudiante(rs.getString("id_estudiante")));
            ge.setGrupo(readGrupo(rs.getInt("id_grupo")));
            ge.setNota(rs.getInt("nota"));
            return ge;
        }
        catch(SQLException ex){
            return null;
        }
    }
}