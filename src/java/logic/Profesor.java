/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package logic;

import java.util.ArrayList;
import java.util.List;

public class Profesor extends Usuario {
    private List<Grupo> grupos;
    public Profesor() {
        super();
        this.grupos = new ArrayList<>(); 
    }
    
    public List<Grupo> getGrupos(){
        return this.grupos;
    }
    
    public void setGrupos(List<Grupo> grupos){
        this.grupos = grupos;
    }
}