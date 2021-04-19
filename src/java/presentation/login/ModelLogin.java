/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.login;

import logic.Administrador;
import logic.Estudiante;
import logic.Profesor;
import logic.Usuario;

public class ModelLogin {
    private Usuario usuario;
    
    public ModelLogin(){
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
    
    public Profesor getProfesor(){
        return (Profesor)this.usuario;
    }
    
    public Administrador getAdministrador(){
        return (Administrador)this.usuario;
    }
}
