/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.estudiante;

import logic.Estudiante;

public class ModelEst {
    private Estudiante estudiante;
    
    public ModelEst(){
        this.estudiante = new Estudiante();
    }

    public ModelEst(Estudiante est){
        this.estudiante = est;
    }
    
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
