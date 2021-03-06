/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.grupoestudiante;

import java.util.ArrayList;
import java.util.List;
import logic.Curso;
import logic.GrupoEstudiante;

public class ModelGrupoEst {
    private GrupoEstudiante grupoEstudiante;
    private List<Curso> cursos;
    private List<GrupoEstudiante> grEsts;
    
    public ModelGrupoEst(){
        this.grupoEstudiante = new GrupoEstudiante();
        this.cursos = new ArrayList<>();
        this.grEsts = new ArrayList<>();
    }
    
    public ModelGrupoEst(GrupoEstudiante gEst){
        this.grupoEstudiante = gEst;
        this.cursos = new ArrayList<>();
        this.grEsts = new ArrayList<>();
    }

    public List<GrupoEstudiante> getGrEsts() {
        return grEsts;
    }

    public void setGrEsts(List<GrupoEstudiante> grEsts) {
        this.grEsts = grEsts;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public GrupoEstudiante getGrupoEstudiante() {
        return grupoEstudiante;
    }

    public void setGrupoEstudiante(GrupoEstudiante grupoEstudiante) {
        this.grupoEstudiante = grupoEstudiante;
    }
}
