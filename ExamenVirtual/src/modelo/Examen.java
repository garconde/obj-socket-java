/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author David_Garcés
 */
public class Examen implements Serializable {
    private ArrayList<Pregunta> listaPreguntas;   
    private String idEstudiante;
    //Fecha para el futuro
    //Materia para el futuro
    //private static int contador = 0;
    
    
    public Examen(){
        this.listaPreguntas = new ArrayList<Pregunta>();              
        this.idEstudiante = "0";
    }
    
    /*public Examen(String idEstudiante) {
        this.listaPreguntas = new ArrayList<Pregunta>();                
        this.idEstudiante = idEstudiante;
    }*/
    
    public float getNota(){        
        int acumulador=0;
        for(int i=0; i<getListaPreguntas().size(); i++){
            if(getListaPreguntas().get(i).esCorrecta()){
                acumulador = acumulador + 1;                
            }
        }
        return (5/getListaPreguntas().size())*acumulador;
    }

    public ArrayList<Pregunta> getListaPreguntas() {
        return listaPreguntas;
    }

    public void setListaPreguntas(ArrayList<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }   

    @Override
    public String toString() {
        return "Examen{" + "listaPreguntas=" + listaPreguntas + ", idEstudiante=" + idEstudiante + '}';
    }    
}
