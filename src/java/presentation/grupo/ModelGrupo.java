/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package presentation.grupo;

import java.util.ArrayList;
import java.util.List;
import logic.Grupo;

public class ModelGrupo {
    private Grupo grupo;
    private List<Grupo> grupos;
    
    public ModelGrupo(){
        this.grupo = new Grupo();
        this.grupos = new ArrayList<>();
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
