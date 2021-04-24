/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.profesor;

import java.util.ArrayList;
import java.util.List;
import logic.Profesor;

public class ModelProfe {
    private Profesor profesor;
    private List<Profesor> profesores;
    
    public ModelProfe(){
        this.profesor = new Profesor();
        this.profesores = new ArrayList<>();
    }

    ModelProfe(Profesor profe) {
        this.profesor = profe;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
