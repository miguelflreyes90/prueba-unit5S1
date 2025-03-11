package org.servtech.junitapp.practicas.models;

import org.servtech.junitapp.ejemplos.models.Cuenta;

public class Candidato {
    private String persona;
    private Partido partido;
    private Urna urna;




    private  Integer votos;

    public Candidato(String persona, Partido partido) {
        this.persona = persona;
        this.partido = partido;
        this.votos = 0;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
        //partido.setCandidato(this);
    }

    public Urna getUrna() {
        return urna;
    }

    public void setUrna(Urna urna) {
        this.urna = urna;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    public Integer agregarVoto() {
        return votos++;
    }




    public  boolean equals(Object obj){

        if(!(obj instanceof Cuenta)){
            return  false;
        }
        Candidato c = (Candidato) obj;
        if(this.persona == null || (this) == null) {
            return  false;
        }
        return this.persona.equals(c.getPersona()) && this.partido.equals(c.getPartido());
    }
}
