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

public class CursoDAO {
    
    public Curso readCurso(int id) throws Exception{
        String sql = "select* from cursos where id=%s";
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
    
    public void add(Curso c) throws Exception{
        String sql = "insert into cursos (nombre, tematica, estatus) "
                + "values ('%s', '%s', '%s')";
        sql = String.format(sql, c.getNombre(), c.getTematica(), c.getEstatus());
        PreparedStatement stm1 = Connection.instance().prepareStatement(sql);
        if(Connection.instance().executeUpdate(stm1) == 0){
            throw new Exception("Curso ya existe");
        }
    }
    
    public List<Curso> readAll() throws Exception{
        List<Curso> cursos = new ArrayList<>();
        String sql = "select* from cursos";
        PreparedStatement stm = Connection.instance().prepareStatement(sql);
        ResultSet rs = Connection.instance().executeQuery(stm);
        while(rs.next()){
            cursos.add(from(rs));
        }
        return cursos;
    }
    
    public Curso from (ResultSet rs){
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
}