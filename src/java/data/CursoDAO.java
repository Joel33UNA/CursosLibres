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