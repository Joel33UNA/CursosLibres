/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.signin;

import logic.Estudiante;
import logic.Usuario;

public class ModelSignin {
    private Usuario usuario;
    
    public ModelSignin(){
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Estudiante getEstudiante(){
        return (Estudiante)this.usuario;
    }
}
