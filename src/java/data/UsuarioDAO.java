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
import logic.Administrador;
import logic.Estudiante;
import logic.Profesor;
import logic.Usuario;

public class UsuarioDAO {
    
    public Usuario read(String id, String clave) throws Exception{
        String sql="select * from usuarios where id=%s and clave=%s";
        sql = String.format(sql, id, clave);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs =  Connection.instance().executeQuery(stm);           
        if (rs.next()) {
            return from(rs);
        }
        else{
            throw new Exception ("¡Error!");
        }
    }
    
    public Usuario readUsuario(String id) throws Exception{
        String sql = "select* from usuarios where id=%s";
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
    
    public Administrador readAdministrador(String id) throws Exception{
        String sql = "select* from administradores, usuarios where "
                + "administradores.id = usuarios.id and administradores.id=%s";
        sql = String.format(sql,id);
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        if(rs.next()){
            return fromAdministrador(rs);
        }
        else{
            throw new Exception("Administrador no existe.");
        }
    }
       
    public List<Usuario> readAll() throws Exception{
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select* from usuario";
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        while(rs.next()){
            usuarios.add(from(rs));
        }
        return usuarios;
    }
    
    public void signin(Usuario u) throws Exception{
        String sql = "insert into usuario values (%s, %s, %s, %s, %s, %s)";
        sql = String.format(sql, u.getId(), u.getClave(), u.getRol(), 
                u.getNombre(), u.getCorreo(), String.valueOf(u.getTelefono()));
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        if(Connection.instance().executeUpdate(stm) == 0){
            throw new Exception("Usuario ya existe");
        }
    }
    
    public Usuario from (ResultSet rs){
        try {
            String rol = rs.getString("rol");
            Usuario r = null;
            switch(rol){
                case "estudiante": { r = new Estudiante(); break;}
                case "profesor": { r = new Profesor(); break; }
                case "administrador": { r = new Administrador(); break; }
            }
            r.setRol(rol);
            r.setId(rs.getString(("id")));
            r.setNombre(rs.getString("nombre"));
            r.setClave(rs.getString("clave"));
            r.setCorreo(rs.getString("correo"));
            r.setTelefono(rs.getLong("telefono"));
            return r;
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public Estudiante fromEstudiante(ResultSet rs){
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
    
    public Administrador fromAdministrador(ResultSet rs){
        try{
            Administrador r = new Administrador();
            r.setRol("administrador");
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
