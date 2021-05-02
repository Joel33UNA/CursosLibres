/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/

package logic;

import java.util.Objects;


public class GrupoEstudiante {
    private int nota;
    private Estudiante estudiante;
    private Grupo grupo;

    public GrupoEstudiante(int nota, Estudiante estudiante, Grupo grupo) {
        this.nota = nota;
        this.estudiante = estudiante;
        this.grupo = grupo;
    }
    
    public GrupoEstudiante() {
        this.nota = 0;
        this.estudiante = new Estudiante();
        this.grupo = new Grupo();
    }

    public GrupoEstudiante(Estudiante est) {
        this.nota = 0;
        this.estudiante = est;
        this.grupo = new Grupo();
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoEstudiante other = (GrupoEstudiante) obj;
        if (this.nota != other.nota) {
            return false;
        }
        if (!Objects.equals(this.estudiante, other.estudiante)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        return true;
    }

    
}
