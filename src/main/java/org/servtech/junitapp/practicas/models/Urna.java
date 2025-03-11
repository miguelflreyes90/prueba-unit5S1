package org.servtech.junitapp.practicas.models;

import org.servtech.junitapp.ejemplos.exception.DineroInsuficienteException;
import org.servtech.junitapp.ejemplos.models.Cuenta;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Urna {


    private List<Candidato> candidatos;
    public Urna() {
        this.candidatos = new ArrayList<>();
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    //Relación urna candidato
    public  void addCandidato(Candidato candidato){
        candidatos.add(candidato);
        candidato.setUrna(this);
    }
    public  void votar(Candidato candidato){
        candidato.agregarVoto();
    }


}
