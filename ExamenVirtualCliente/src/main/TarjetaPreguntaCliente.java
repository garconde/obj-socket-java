/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

/**
 *
 * @author David_Garcés
 */
public class TarjetaPreguntaCliente extends JPanel {
    
    private JPanel enunciado, opcionesRadioButton;
    private JTextArea enunciadoTextArea;
    private JScrollPane scrollEnunciado;
    private JLabel enunciadoLabel;
    private ButtonGroup grupoOpciones;    
    private JRadioButton opA, opB, opC, opD;
        
    public TarjetaPreguntaCliente(){
        //(fil, col, espacioH, espacioV)
        super(new GridLayout(2, 1, 1, 1));                
        agregarComponentes();        
        this.darTamanio();        
    }

    public JPanel getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(JPanel enunciado) {
        this.enunciado = enunciado;
    }

    public JPanel getOpcionesRadioButton() {
        return opcionesRadioButton;
    }

    public void setOpcionesRadioButton(JPanel opcionesRadioButton) {
        this.opcionesRadioButton = opcionesRadioButton;
    }

    public JTextArea getEnunciadoTextArea() {
        return enunciadoTextArea;
    }

    public void setEnunciadoTextArea(JTextArea enunciadoTextArea) {
        this.enunciadoTextArea = enunciadoTextArea;
    }

    public JScrollPane getScrollEnunciado() {
        return scrollEnunciado;
    }

    public void setScrollEnunciado(JScrollPane scrollEnunciado) {
        this.scrollEnunciado = scrollEnunciado;
    }

    public JLabel getEnunciadoLabel() {
        return enunciadoLabel;
    }

    public void setEnunciadoLabel(JLabel enunciadoLabel) {
        this.enunciadoLabel = enunciadoLabel;
    }

    public ButtonGroup getGrupoOpciones() {
        return grupoOpciones;
    }

    public void setGrupoOpciones(ButtonGroup grupoOpciones) {
        this.grupoOpciones = grupoOpciones;
    }

    public JRadioButton getOpA() {
        return opA;
    }

    public void setOpA(JRadioButton opA) {
        this.opA = opA;
    }

    public JRadioButton getOpB() {
        return opB;
    }

    public void setOpB(JRadioButton opB) {
        this.opB = opB;
    }

    public JRadioButton getOpC() {
        return opC;
    }

    public void setOpC(JRadioButton opC) {
        this.opC = opC;
    }

    public JRadioButton getOpD() {
        return opD;
    }

    public void setOpD(JRadioButton opD) {
        this.opD = opD;
    }        

    private void darTamanio() {
        //this.setSize(new Dimension(100,100));
        this.setBorder(new LineBorder(Color.RED));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
    
    private void agregarComponentes() {
        enunciado = new JPanel (new FlowLayout (FlowLayout.CENTER));
        enunciado.setBackground(Color.PINK);
        //enunciado.setSize(new Dimension(500,80));
        enunciadoTextArea = new JTextArea();
        enunciadoTextArea.setEnabled(false);
        scrollEnunciado = new JScrollPane(enunciadoTextArea);
        scrollEnunciado.setPreferredSize(new Dimension(600, 50));
        enunciadoLabel = new JLabel("Enunciado: ");
        enunciado.add(enunciadoLabel);
        enunciado.add(scrollEnunciado);        
        this.add(enunciado);
        
        opcionesRadioButton = new JPanel(new FlowLayout (FlowLayout.CENTER)); 
        opcionesRadioButton.setBackground(Color.WHITE);
        opA = new JRadioButton("Opción A", true);
        opB = new JRadioButton("Opción B", false);
        opC = new JRadioButton("Opción C", false);
        opD = new JRadioButton("Opción D", false);  
        opA.setActionCommand("opcionA");
        opB.setActionCommand("opcionB");
        opC.setActionCommand("opcionC");
        opD.setActionCommand("opcionD");

        grupoOpciones = new ButtonGroup ();
        grupoOpciones.add(opA);
        grupoOpciones.add(opB);
        grupoOpciones.add(opC);
        grupoOpciones.add(opD);

        opcionesRadioButton.add(opA);
        opcionesRadioButton.add(opB);
        opcionesRadioButton.add(opC);
        opcionesRadioButton.add(opD);

        this.add (opcionesRadioButton);
    }    
    
}
