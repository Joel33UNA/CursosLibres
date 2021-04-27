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

public class Curso {
    private int id;
    private String nombre;
    private String tematica;
    private String estatus;
    private int precio;
    private List<Grupo> grupos;

    public Curso(int id, String nombre, String tematica, String estatus, int precio, List<Grupo> grupos) {
        this.id = id;
        this.nombre = nombre;
        this.tematica = tematica;
        this.estatus = estatus;
        this.precio = precio;
        this.grupos = grupos;
    }

    public Curso() {
        this.id = 0;
        this.nombre = " ";
        this.tematica = " ";
        this.estatus = " ";
        this.precio = 0;
        this.grupos = null;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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
        final Curso other = (Curso) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.tematica, other.tematica)) {
            return false;
        }
        if (!Objects.equals(this.estatus, other.estatus)) {
            return false;
        }
        if (!Objects.equals(this.grupos, other.grupos)) {
            return false;
        }
        return true;
    }

    
}
