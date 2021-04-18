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

    public Estudiante(String id, String nombre, String rol, String clave, String correo, long telefono) {
        super(id, nombre, rol, clave, correo, telefono);
    }

    public List<GrupoEstudiante> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoEstudiante> grupos) {
        this.grupos = grupos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Estudiante other = (Estudiante) obj;
        if (!Objects.equals(this.grupos, other.grupos)) {
            return false;
        }
        return true;
    }
}
