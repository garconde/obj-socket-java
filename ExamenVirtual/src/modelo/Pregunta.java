/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Win 10
 */
public class Pregunta implements Serializable {
    private String enunciado;
    private String opcionA;
    private String opcionB;
    private String opcionC;
    private String opcionD;
    private char respuesta;
    private char respuestaEstudiante;

    public Pregunta(String enunciado, String opcionA, String opcionB, String opcionC, String opcionD, char respuesta) {
        this.enunciado = enunciado;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.respuesta = respuesta;
        this.respuestaEstudiante = 'e';
    }
    
    public boolean esCorrecta(){
        return respuesta == respuestaEstudiante;
    }    

    public String getEnunciado() {
        return enunciado;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    public char getRespuesta() {
        return respuesta;
    }
    
    public char getRespuestaEstudiante() {
        return respuestaEstudiante;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setOpcionA(String opcionA) {
        this.opcionA = opcionA;
    }

    public void setOpcionB(String opcionB) {
        this.opcionB = opcionB;
    }

    public void setOpcionC(String opcionC) {
        this.opcionC = opcionC;
    }

    public void setOpcionD(String opcionD) {
        this.opcionD = opcionD;
    }

    public void setRespuesta(char respuesta) {
        this.respuesta = respuesta;
    }  
    
    public void setRespuestaEstudiante(char respuestaEstudiante){
        this.respuestaEstudiante = respuestaEstudiante;
    }

    @Override
    public String toString() {
        return "Pregunta{" + "Enunciado=" + enunciado + ", opcionA=" + opcionA + ", opcionB=" + opcionB + ", opcionC=" + opcionC + ", opcionD=" + opcionD + ", respuesta=" + respuesta + '}';
    }

}
