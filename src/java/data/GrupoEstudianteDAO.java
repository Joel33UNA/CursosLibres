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
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Curso;
import logic.Estudiante;
import logic.Grupo;
import logic.GrupoEstudiante;
import logic.Profesor;

public class GrupoEstudianteDAO {
    
    public List<GrupoEstudiante> readAll() throws Exception{
        List<GrupoEstudiante> grupos = new ArrayList<>();
        String sql = "select* from gruposestudiantes";
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        while(rs.next()){
            grupos.add(from(rs));
        }
        return grupos;
    }
    
    public GrupoEstudiante readGrupo(String idEst, int idGru) throws Exception{
        String sql = "select* from grupos where id_estudiante=%s and id_grupo=%s";
        sql = String.format(sql, idEst, idGru);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return from(rs);
        }
        else{
            return null;
        }
    }
    
    public void add(GrupoEstudiante g) throws Exception{
        String sql = "insert into gruposestudiantes "
                + "values ('%s', %s, %s)";
        sql = String.format(sql, g.getEstudiante().getId(), g.getGrupo().getId(), g.getNota());
        PreparedStatement stm1 = Connection.instance().prepareStatement(sql);
        if(Connection.instance().executeUpdate(stm1) == 0){
            throw new Exception("Grupo-Estudiante ya existe");
        }
    }
    
    public GrupoEstudiante from (ResultSet rs){
        try {
            Grupo g = new Grupo();
            Estudiante e = new Estudiante();
            try {
                g = readGrupo(rs.getInt("id_grupo"));
                e = readEstudiante(rs.getString("id_estudiante"));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            GrupoEstudiante r = new GrupoEstudiante();
            r.setEstudiante(e);
            r.setGrupo(g);
            r.setNota(rs.getInt(("nota")));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Grupo readGrupo(int id) throws Exception{
        String sql = "select* from grupos where id=%s";
        sql = String.format(sql, id);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return fromGru(rs);
        }
        else{
            return null;
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
    
    private Estudiante fromEstudiante(ResultSet rs) {
        try{
            Estudiante r = new Estudiante();
            r.setRol("estudiante");
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
    
    public Grupo fromGru(ResultSet rs){
        try {
            Grupo r = new Grupo();
            r.setId(rs.getInt("id"));
            r.setHorario(rs.getString(("horario")));
            r.setCurso(readCu(rs.getInt("curso")));
            r.setProfesor(readProfesor(rs.getString("profesor")));
            return r;
        } catch (SQLException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }
    
    private Curso readCu(int curso) throws Exception{
        String sql = "select* from cursos where id=%s";
        sql = String.format(sql, curso);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return fromCu(rs);
        }
        else{
            return null;
        }
    }

    private Profesor readProfesor(String id) throws Exception{
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

    private Curso fromCu(ResultSet rs) {
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

    private Profesor fromProfesor(ResultSet rs) {
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
}
