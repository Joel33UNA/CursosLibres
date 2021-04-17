/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package logic;

import java.util.List;

public class Estudiante extends Usuario {
    private String nombre;
    private String correo;
    private String telefono;
    private List<EstudianteGrupo> grupos;
    
    public Estudiante(){
        super();
        this.nombre = "";
        this.correo = "";
        this.telefono = "";
        this.grupos = null;
    }
    
    public Estudiante(String id, String nombre, String correo, String telefono, List<EstudianteGrupo> grupos){
        super(id, "", "");
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.grupos = grupos;
    }

    public List<EstudianteGrupo> getEstudianteGrupos() {
        return grupos;
    }

    public void setEstudianteGrupos(List<EstudianteGrupo> grupos) {
        this.grupos = grupos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
