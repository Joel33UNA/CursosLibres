/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.profesor;

import logic.Profesor;

public class ModelProfe {
    private Profesor profesor;
    
    public ModelProfe(){
        this.profesor = new Profesor();
    }

    ModelProfe(Profesor profe) {
        this.profesor = profe;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
