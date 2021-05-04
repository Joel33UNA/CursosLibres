/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.estudiante;

import java.util.ArrayList;
import java.util.List;
import logic.Estudiante;
import logic.GrupoEstudiante;

public class ModelEst {
    private Estudiante estudiante;
    private List<Estudiante> estudiantes;
    private List<GrupoEstudiante> gruposestudiantes;
    
    public ModelEst(){
        this.estudiante = new Estudiante();
        this.estudiantes = new ArrayList<>();
        this.gruposestudiantes = new ArrayList<>();
    }
    
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public List<Estudiante> getEstudiantes(){
        return estudiantes;
    }
    
    public void setEstudiantes(List<Estudiante> estudiantes){
        this.estudiantes = estudiantes;
    }

    public List<GrupoEstudiante> getGruposEstudiantes() {
        return gruposestudiantes;
    }

    public void setGruposEstudiantes(List<GrupoEstudiante> gruposestudiantes) {
        this.gruposestudiantes = gruposestudiantes;
    }
}
