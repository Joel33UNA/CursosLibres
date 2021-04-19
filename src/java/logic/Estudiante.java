/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package logic;

import java.util.List;
import java.util.Objects;

public class Estudiante extends Usuario {
    private List<GrupoEstudiante> grupos;

    public Estudiante() {
    }

    public List<GrupoEstudiante> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoEstudiante> grupos) {
        this.grupos = grupos;
    }
}
