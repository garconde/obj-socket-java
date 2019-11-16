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
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author David_Garcés
 */
public class TarjetaPreguntaServidor extends JPanel {
    
    private JPanel enunciado, opcionesRadioButton, opcionesTextField, opcionesGeneral;
    private JTextArea enunciadoTextArea;
    private JScrollPane scrollEnunciado;
    private JLabel enunciadoLabel;
    private ButtonGroup grupoOpciones;
    private JTextField textFieldOpcionA, textFieldOpcionB, textFieldOpcionC, textFieldOpcionD;
    private JRadioButton opA, opB, opC, opD;

    public void setEnunciado(JPanel enunciado) {
        this.enunciado = enunciado;
    }

    public void setOpcionesRadioButton(JPanel opcionesRadioButton) {
        this.opcionesRadioButton = opcionesRadioButton;
    }

    public void setOpcionesTextField(JPanel opcionesTextField) {
        this.opcionesTextField = opcionesTextField;
    }

    public void setOpcionesGeneral(JPanel opcionesGeneral) {
        this.opcionesGeneral = opcionesGeneral;
    }

    public void setEnunciadoTextArea(JTextArea enunciadoTextArea) {
        this.enunciadoTextArea = enunciadoTextArea;
    }

    public void setScrollEnunciado(JScrollPane scrollEnunciado) {
        this.scrollEnunciado = scrollEnunciado;
    }

    public void setEnunciadoLabel(JLabel enunciadoLabel) {
        this.enunciadoLabel = enunciadoLabel;
    }

    public void setGrupoOpciones(ButtonGroup grupoOpciones) {
        this.grupoOpciones = grupoOpciones;
    }

    public void setTextFieldOpcionA(JTextField textFieldOpcionA) {
        this.textFieldOpcionA = textFieldOpcionA;
    }

    public void setTextFieldOpcionB(JTextField textFieldOpcionB) {
        this.textFieldOpcionB = textFieldOpcionB;
    }

    public void setTextFieldOpcionC(JTextField textFieldOpcionC) {
        this.textFieldOpcionC = textFieldOpcionC;
    }

    public void setTextFieldOpcionD(JTextField textFieldOpcionD) {
        this.textFieldOpcionD = textFieldOpcionD;
    }

    public void setOpA(JRadioButton opA) {
        this.opA = opA;
    }

    public void setOpB(JRadioButton opB) {
        this.opB = opB;
    }

    public void setOpC(JRadioButton opC) {
        this.opC = opC;
    }

    public void setOpD(JRadioButton opD) {
        this.opD = opD;
    }

    public JPanel getEnunciado() {
        return enunciado;
    }

    public JPanel getOpcionesRadioButton() {
        return opcionesRadioButton;
    }

    public JPanel getOpcionesTextField() {
        return opcionesTextField;
    }

    public JPanel getOpcionesGeneral() {
        return opcionesGeneral;
    }

    public JTextArea getEnunciadoTextArea() {
        return enunciadoTextArea;
    }

    public JScrollPane getScrollEnunciado() {
        return scrollEnunciado;
    }

    public JLabel getEnunciadoLabel() {
        return enunciadoLabel;
    }

    public ButtonGroup getGrupoOpciones() {
        return grupoOpciones;
    }

    public JTextField getTextFieldOpcionA() {
        return textFieldOpcionA;
    }

    public JTextField getTextFieldOpcionB() {
        return textFieldOpcionB;
    }

    public JTextField getTextFieldOpcionC() {
        return textFieldOpcionC;
    }

    public JTextField getTextFieldOpcionD() {
        return textFieldOpcionD;
    }

    public JRadioButton getOpA() {
        return opA;
    }

    public JRadioButton getOpB() {
        return opB;
    }

    public JRadioButton getOpC() {
        return opC;
    }

    public JRadioButton getOpD() {
        return opD;
    }

        
    public TarjetaPreguntaServidor(){
        //(fil, col, espacioH, espacioV)
        super(new GridLayout(2, 1, 1, 1));                
        this.agregarComponentes();        
        this.darTamanio();        
    }

    private void darTamanio() {
        //this.setSize(new Dimension(100,100));
        this.setBorder(new LineBorder(Color.BLUE));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
    
    private void agregarComponentes() {
        enunciado = new JPanel (new FlowLayout (FlowLayout.CENTER));
        enunciado.setBackground(Color.PINK);        
        enunciadoTextArea = new JTextArea();        
        scrollEnunciado = new JScrollPane(enunciadoTextArea);
        scrollEnunciado.setPreferredSize(new Dimension(600, 70));
        enunciadoLabel = new JLabel("Enunciado: ");
        enunciado.add(enunciadoLabel);
        enunciado.add(scrollEnunciado);        
        this.add(enunciado);
        
        opcionesRadioButton = new JPanel();
        opcionesRadioButton.setLayout(new BoxLayout(opcionesRadioButton, BoxLayout.Y_AXIS));
        opcionesRadioButton.setBackground(Color.WHITE);
        opA = new JRadioButton("Opción A", true);        
        opB = new JRadioButton("Opción B", true);
        opC = new JRadioButton("Opción C", true);
        opD = new JRadioButton("Opción D", true);
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
        
        opcionesTextField = new JPanel();
        opcionesTextField.setLayout(new BoxLayout(opcionesTextField, BoxLayout.Y_AXIS));
        opcionesTextField.setBackground(Color.WHITE);
        textFieldOpcionA = new JTextField("");
        textFieldOpcionB = new JTextField("");
        textFieldOpcionC = new JTextField("");
        textFieldOpcionD = new JTextField("");
        textFieldOpcionA.setPreferredSize(new Dimension(400, 20));
        textFieldOpcionB.setPreferredSize(new Dimension(400, 20));
        textFieldOpcionB.setPreferredSize(new Dimension(400, 20));
        textFieldOpcionB.setPreferredSize(new Dimension(400, 20));
        
        opcionesTextField.add(textFieldOpcionA);
        opcionesTextField.add(textFieldOpcionB);
        opcionesTextField.add(textFieldOpcionC);
        opcionesTextField.add(textFieldOpcionD);
        
        opcionesGeneral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        opcionesGeneral.add(opcionesRadioButton);
        opcionesGeneral.add(opcionesTextField);
        
        this.add (opcionesGeneral);
    }        
}
