/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package logic;

    public class Administrador extends Usuario {

    public Administrador() {
        super();
    }

    public Administrador(String id, String nombre, String rol, String clave, String correo, long telefono) {
        super(id, nombre, rol, clave, correo, telefono);
    }
}
