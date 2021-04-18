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
    private int idMatricula;

    public GrupoEstudiante(int nota, Estudiante estudiante, Grupo grupo, int idMatricula) {
        this.nota = nota;
        this.estudiante = estudiante;
        this.grupo = grupo;
        this.idMatricula = idMatricula;
    }
    
    public GrupoEstudiante() {
        this.nota = 0;
        this.estudiante = null;
        this.grupo = null;
        this.idMatricula = 0;
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

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        if (this.idMatricula != other.idMatricula) {
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
