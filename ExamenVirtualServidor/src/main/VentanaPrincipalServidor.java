/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import modelo.Examen;
import modelo.Pregunta;

public class VentanaPrincipalServidor implements Runnable {
   
    private static JFrame frame;
    private JLabel tituloLabel, notaLabel;
    private JPanel encabezadoPanel, panelConfiguraciones, panelCentro, panelInferior;
    private static JPanel panelDerecho;
    private JButton prepararExamenBoton, publicarExamenBoton, examenEjemploBoton;    
    private JScrollPane scroll, scrollDerechoNotas;
    private JSlider numPreguntasSlider;
    private int numPreguntas;
   
    private Examen examen;
    private Pregunta pregunta;
    
    private ArrayList<TarjetaPreguntaServidor> listaTarjetasPregunta;
    public static ArrayList<Examen> listaExamen;
    
    public VentanaPrincipalServidor(){
        this.listaTarjetasPregunta = new ArrayList<TarjetaPreguntaServidor>();
        VentanaPrincipalServidor.listaExamen = new ArrayList<Examen>();
        this.examen = new Examen();        
        construyePanelSuperior();
        construyePanelInferior();         
        construyeVentana();        
        manejaEventos();
    }

    private void construyePanelSuperior(){
        encabezadoPanel = new JPanel(new GridLayout(2, 1, 10, 10));        
      
        tituloLabel = new JLabel("Servidor");
        encabezadoPanel.add(tituloLabel);
        Font aux=tituloLabel.getFont();
        tituloLabel.setFont(new Font(aux.getFontName(), aux.getStyle(), 24));
        
        panelConfiguraciones = new JPanel(new FlowLayout(FlowLayout.CENTER));       
        panelConfiguraciones.add(new JLabel("Número de preguntas"));
        numPreguntasSlider = new JSlider(JSlider.HORIZONTAL, 2, 10, 6);
        numPreguntasSlider.setMinorTickSpacing(1);
        numPreguntasSlider.setMajorTickSpacing(2);        
        numPreguntasSlider.setPaintTicks(true);
        numPreguntasSlider.setPaintLabels(true);
        
        panelConfiguraciones.add(numPreguntasSlider);
        
        prepararExamenBoton = new JButton("Preparar nuevo examen");
        panelConfiguraciones.add(prepararExamenBoton);
        examenEjemploBoton = new JButton("Cargar Examen ejemplo");
        panelConfiguraciones.add(examenEjemploBoton);        
                        
        encabezadoPanel.add(panelConfiguraciones);        
    }
    
    private void construyePanelInferior(){
        panelInferior = new JPanel();        
        publicarExamenBoton = new JButton("Publicar Examen");        
        panelInferior.setLayout(new FlowLayout());
        panelInferior.setBackground(Color.black);      
        panelInferior.add(publicarExamenBoton);        
    }
        
    private void construyePanelCentroGeneral(){
        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        //panelCentro.setSize(600, 700);
        numPreguntas = numPreguntasSlider.getValue();
        //System.out.println(numPreguntas);
        
        for(int i=0; i<numPreguntas; i++){
            TarjetaPreguntaServidor tarjetaPreguntaPanel = new TarjetaPreguntaServidor();
            if(i%2==0){
                tarjetaPreguntaPanel.setBackground(Color.GRAY);
            }else{
                tarjetaPreguntaPanel.setBackground(Color.DARK_GRAY);
            }
            panelCentro.add(tarjetaPreguntaPanel); 
            listaTarjetasPregunta.add(tarjetaPreguntaPanel);
        }
        scroll = new JScrollPane(panelCentro);   
        //scroll.setSize(600, 700);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //panelCentro.getComponent(numPreguntas); Obetener las ubicaciones        
        
        frame.add(scroll, BorderLayout.CENTER);
        frame.pack(); //Redibujar el frame
    }  
    
    private void construyePanelCentroEjemplo(){
        
        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        numPreguntasSlider.setValue(examen.getListaPreguntas().size());
        numPreguntas = numPreguntasSlider.getValue();
        //System.out.println(numPreguntas);
        
        for(int i=0; i<numPreguntas; i++){
            TarjetaPreguntaServidor tarjetaPreguntaPanel = new TarjetaPreguntaServidor();
            if(i%2==0){
                tarjetaPreguntaPanel.setBackground(Color.GRAY);
            }else{
                tarjetaPreguntaPanel.setBackground(Color.DARK_GRAY);
            }
            tarjetaPreguntaPanel.getEnunciadoTextArea().
                    setText(examen.getListaPreguntas().
                            get(i).getEnunciado());
            tarjetaPreguntaPanel.getTextFieldOpcionA().
                    setText(examen.getListaPreguntas().
                            get(i).getOpcionA());
            tarjetaPreguntaPanel.getTextFieldOpcionB().
                    setText(examen.getListaPreguntas().
                            get(i).getOpcionB());
            tarjetaPreguntaPanel.getTextFieldOpcionC().
                    setText(examen.getListaPreguntas().
                            get(i).getOpcionC());
            tarjetaPreguntaPanel.getTextFieldOpcionD().
                    setText(examen.getListaPreguntas().
                            get(i).getOpcionD());
            char letraRespuesta;
            letraRespuesta = examen.getListaPreguntas().get(i).getRespuesta();
            switch(letraRespuesta){
                case 'a':
                    tarjetaPreguntaPanel.getGrupoOpciones().setSelected(tarjetaPreguntaPanel.getOpA().getModel(), true);
                    break;
                case 'b':
                    tarjetaPreguntaPanel.getGrupoOpciones().setSelected(tarjetaPreguntaPanel.getOpB().getModel(), true);
                    break;
                case 'c':
                    tarjetaPreguntaPanel.getGrupoOpciones().setSelected(tarjetaPreguntaPanel.getOpC().getModel(), true);
                    break;
                case 'd':
                    tarjetaPreguntaPanel.getGrupoOpciones().setSelected(tarjetaPreguntaPanel.getOpD().getModel(), true);
                    break;
                default:
                    System.out.println("No se encuentra respuesta");
                    break;                               
            }
            panelCentro.add(tarjetaPreguntaPanel); 
            listaTarjetasPregunta.add(tarjetaPreguntaPanel);
        }
        scroll = new JScrollPane(panelCentro);        
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                            
        frame.add(scroll, BorderLayout.CENTER);
        frame.pack(); //Redibujar el frame
    }  
    
    private void construyePanelDerecho(){
        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        notaLabel = new JLabel("   Estudiantes y sus notas   ");
        panelDerecho.add(notaLabel);
        
        agregarNotaEnPanel(); //
        
        scrollDerechoNotas = new JScrollPane(panelDerecho);
        scrollDerechoNotas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollDerechoNotas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollDerechoNotas, BorderLayout.EAST);
        frame.pack();
    }
    
    public static void agregarNotaEnPanel(){
        if(listaExamen.size() > 0){
            Examen ultExamen = listaExamen.get(listaExamen.size()-1);
            JPanel tarjetaNota = new JPanel(new FlowLayout(FlowLayout.CENTER));

            if(ultExamen.getNota() < 3.0 ){
                tarjetaNota.setBackground(Color.RED);
            }else if(ultExamen.getNota() >= 3.0 && ultExamen.getNota() < 4.5){
                tarjetaNota.setBackground(Color.YELLOW);
            } else {
                tarjetaNota.setBackground(Color.GREEN);
            }
            tarjetaNota.setLayout(new BoxLayout(tarjetaNota, BoxLayout.X_AXIS));
            JLabel labelEstudiante = new JLabel(ultExamen.getIdEstudiante());
            tarjetaNota.add(labelEstudiante);
            JLabel labelSeparador = new JLabel("  nota-->  ");
            tarjetaNota.add(labelSeparador);
            JLabel labelNota = new JLabel(Float.toString(ultExamen.getNota()));
            tarjetaNota.add(labelNota);
            panelDerecho.add(tarjetaNota);
            frame.pack();
        }        
    }
        
    private void construyeVentana(){
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(900, 700));
        frame.setBounds(400, 50, 900, 700);
        frame.setTitle("SERVIDOR - Examen Vitual");
        frame.add(encabezadoPanel, BorderLayout.NORTH);        
        frame.add(panelInferior, BorderLayout.SOUTH);        
        //frame.add(panelIzquierdo, BorderLayout.WEST);
        //frame.add(panelDerecho, BorderLayout.EAST);              
                
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
        
    /*
    Antes de la conexión:
        Sólo visible la sección NORTH        
    Después de la conexión:        
        Deshabilitar la sección NORTH
        Visualizar la sección CENTER y SOUTH
        Rellenar enunciados y opciones en las tarjetas respuestas
        Iterar en el for hasta el numero de preguntas del examen
    Despues de la segunda conexión:
        Desabilitar todas las secciones y habilitar la sección RIGHT
    */
    
    private void manejaEventos(){
        
        prepararExamenBoton.addActionListener((ActionEvent e) -> {
            construyePanelCentroGeneral();
            JOptionPane.showMessageDialog(null, "Rellene cada enunciado, "
                    + "los campos de opciones y elija la respuesta correcta", "Instrucciones"
                    , JOptionPane.INFORMATION_MESSAGE);            
            //Desahibilitar el panel superior
            prepararExamenBoton.setEnabled(false);
            examenEjemploBoton.setEnabled(false);
            numPreguntasSlider.setEnabled(false);
        });
        
        examenEjemploBoton.addActionListener((ActionEvent e) -> {
            crearExamenEjemplo();
            construyePanelCentroEjemplo();
            //Desahibilitar el panel superior
            prepararExamenBoton.setEnabled(false);
            examenEjemploBoton.setEnabled(false);
            numPreguntasSlider.setEnabled(false);
        });
        
        publicarExamenBoton.addActionListener((ActionEvent e) -> {
            crearExamen();            
            construyePanelDerecho();
            new Thread(this).start(); //Hilo para atender la conexión y seguir en la vista
            //frame.pack();
            panelCentro.setEnabled(false);
            publicarExamenBoton.setEnabled(false);
        });     
    }
    
    @Override
    public void run() {
        try {
            iniciarServidor();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void main (String [] string){
        new VentanaPrincipalServidor();
    }
    
    public void iniciarServidor() throws IOException {                     
        ServerSocket servidorSocket = new ServerSocket(8999);
        while (true){ 
            Socket clienteSocket = null;               
            try{
                //Aceptando Socket del cliente                
                clienteSocket = servidorSocket.accept(); //Esperando
                System.out.println("Nuevo cliente conectado: " + clienteSocket);                                                                

                System.out.println("Asignando al nuevo cliente");                 
                Thread hiloCliente = new ManejadorCliente(clienteSocket, examen);

                hiloCliente.start();
                
                //clienteSocket.close(); //mmmmm?
                
            }catch(IOException ioe){
                System.out.println(ioe.getMessage());
            }
        }      
    }
    
    private void crearExamen(){
        numPreguntas = numPreguntasSlider.getValue();
        //Pregunta pregunta = new Pregunta(enunciado, opcionA, opcionB, opcionC, opcionD, 0)
        examen.getListaPreguntas().clear();
        for(int i=0; i<listaTarjetasPregunta.size(); i++){
            //System.out.println("Pregunnnnnnnta opción A: " + listaTarjetasPregunta.get(i).getTextFieldOpcionA().getText());
            pregunta = new Pregunta(listaTarjetasPregunta.get(i).getEnunciadoTextArea().getText(),
                                    listaTarjetasPregunta.get(i).getTextFieldOpcionA().getText(),
                                    listaTarjetasPregunta.get(i).getTextFieldOpcionB().getText(),
                                    listaTarjetasPregunta.get(i).getTextFieldOpcionC().getText(),
                                    listaTarjetasPregunta.get(i).getTextFieldOpcionD().getText(),
                                    opSelecionada(listaTarjetasPregunta.get(i).getGrupoOpciones())                                    
                                    );
            examen.getListaPreguntas().add(pregunta);            
        }
        System.out.println("Número de tarjetas preguntas: "+listaTarjetasPregunta.size());
        System.out.println("Número de preguntas slider: "+numPreguntas);        
    }
    
    private void crearExamenEjemplo(){
        //String simpleName = panelCentro.getComponent(1).getClass().getSimpleName();
        //System.out.println(simpleName);  
        
        pregunta = new Pregunta("A las altura de los tiempos",
                "Universidad de Cartagena", 
                "Universidad Nacional", 
                "Universidad Tecnólogica de Bolívar", 
                "Universidad de Antioquia", 
                'a');
        examen.getListaPreguntas().add(pregunta);
        pregunta = new Pregunta("¿Cuál es el estándar ISO para la calidad del software?",
                "ISO 9001", 
                "ISO 14001", 
                "ISO 22000", 
                "ISO 25000", 
                'd');
        examen.getListaPreguntas().add(pregunta);
        pregunta = new Pregunta("¿Cuál es de los siguientes programas es una herramienta CASE?",
                "Paint", 
                "Enterprise Arquitect", 
                "Avira", 
                "Cisco Packet Tracer", 
                'b');
        examen.getListaPreguntas().add(pregunta);
        pregunta = new Pregunta("¿Cuál de los siguientes NO es un protocolo de transmición de datos?",
                "TCP", 
                "UDP", 
                "TGS", 
                "RMI", 
                'c');
        examen.getListaPreguntas().add(pregunta);
        pregunta = new Pregunta("¿Cuál de las siguientes no es una materia de Ingeniría de Sistemas UniCartagena?",
                "Seminario de Actualización", 
                "Seminario de Programación", 
                "Seminario de Investigación", 
                "Seminario Taller Creación de Empresas", 
                'b');
        examen.getListaPreguntas().add(pregunta);                                
        System.out.println("veamos: "+examen.getIdEstudiante());
    }
    
    private char opSelecionada(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            
            System.out.println("Buttonnnnn: "+button.getActionCommand());

            if (button.isSelected()) {
                switch (button.getActionCommand()) {
                    case "opcionA":
                        return 'a';
                    case "opcionB":
                        return 'b';
                    case "opcionC":
                        return 'c';
                    case "opcionD":
                        return 'd';
                    default:
                        break;
                }
            }
        }                
        return 'e';
    }      
}