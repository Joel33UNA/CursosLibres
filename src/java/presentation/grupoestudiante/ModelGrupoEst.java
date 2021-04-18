/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.grupoestudiante;

import logic.GrupoEstudiante;

public class ModelGrupoEst {
    private GrupoEstudiante grupoEstudiante;
    
    public ModelGrupoEst(){
        this.grupoEstudiante = new GrupoEstudiante();
    }

    public GrupoEstudiante getGrupoEstudiante() {
        return grupoEstudiante;
    }

    public void setGrupoEstudiante(GrupoEstudiante grupoEstudiante) {
        this.grupoEstudiante = grupoEstudiante;
    }
}
