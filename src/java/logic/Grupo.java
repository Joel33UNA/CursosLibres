/*
UNIVERSIDAD NACIONAL DE COSTA RICA
EIF-209  -  PROGRAMACIÓN IV
PROYECTO I
ESTUDIANTE: JOEL ZAMORA Y DIEGO JIMÉNEZ
PROFESOR: JOSE SÁNCHEZ SALAZAR
*/


package logic;

import java.util.Objects;

public class Grupo {
    private int id;
    private String horario;
    private Curso curso;
    private Profesor profesor;

    public Grupo(int id, String horario, Curso curso, GrupoEstudiante matricula, Profesor profesor) {
        this.id = id;
        this.horario = horario;
        this.curso = curso;
        this.profesor = profesor;
    }
    
    public Grupo() {
        this.id = 0;
        this.horario = "";
        this.curso = null;
        this.profesor = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.id != id) {
            return false;
        }
        return true;
    }
}
