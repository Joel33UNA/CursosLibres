/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package logic;

    public class Administrador extends Usuario {
    private String nombre;
    private String correo;
    private String telefono;
    
    public Administrador(){
        super();
        this.nombre = "";
        this.correo = "";
        this.telefono = "";
    }
    
    public Administrador(String id, String nombre, String correo, String telefono){
        super(id, "", "");
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
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
