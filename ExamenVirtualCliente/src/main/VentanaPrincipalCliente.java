package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import modelo.Examen;
import modelo.Pregunta;

public class VentanaPrincipalCliente {
    
    private JFrame frame;
    private JLabel tituloLabel, notaLabel;
    private JPanel encabezadoPanel, capturaTextoPanel, panelCentro, panelInferior, panelDerecho;
    private JButton solicitarBoton, calificarEnviarButon;
    private JTextField codigoEstudianteCampo;
    private JScrollPane scroll;
    
    private Examen examen;
    
    private ObjectOutputStream ous;
    private ObjectInputStream ois;
    private ArrayList<TarjetaPreguntaCliente> listaTarjetasPregunta;

    public VentanaPrincipalCliente(){
        listaTarjetasPregunta = new ArrayList<TarjetaPreguntaCliente>();
        examen = new Examen();
        construyePanelSuperior(); //El del centro y el inferior se construiran después en los eventos de los botones
        construyePanelInferior();         
        construyePanelDerecho();
        construyeVentana();
        ManejadorEventos();
    }
    
    private void construyePanelSuperior(){
        encabezadoPanel = new JPanel(new GridLayout(2, 1, 10, 10));        
      
        tituloLabel = new JLabel("Cliente");
        encabezadoPanel.add(tituloLabel);
        Font aux=tituloLabel.getFont();
        tituloLabel.setFont(new Font(aux.getFontName(), aux.getStyle(), 20));
        
        capturaTextoPanel = new JPanel(new FlowLayout());        
        capturaTextoPanel.add(new JLabel("Código estudiante"));
        codigoEstudianteCampo = new JTextField();
        codigoEstudianteCampo.setPreferredSize(new Dimension(150,25));
        capturaTextoPanel.add(codigoEstudianteCampo);
        solicitarBoton = new JButton("Solicitar examen");
        capturaTextoPanel.add(solicitarBoton);
                        
        encabezadoPanel.add(capturaTextoPanel);
    }
    
    private void construyePanelInferior(){
        panelInferior = new JPanel();        
        calificarEnviarButon=new JButton("Enviar respuestas y calificar");
        panelInferior.setLayout(new FlowLayout());
        panelInferior.setBackground(Color.black);      
        panelInferior.add(calificarEnviarButon);
    }
        
    private void construyePanelCentro(){
        panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        for (Pregunta pregunta : examen.getListaPreguntas()) {
            TarjetaPreguntaCliente tarjeta = new TarjetaPreguntaCliente();
            tarjeta.getEnunciadoTextArea().setText(pregunta.getEnunciado());
            tarjeta.getOpA().setText(pregunta.getOpcionA());
            tarjeta.getOpB().setText(pregunta.getOpcionB());
            tarjeta.getOpC().setText(pregunta.getOpcionC());
            tarjeta.getOpD().setText(pregunta.getOpcionD());
            if(examen.getListaPreguntas().indexOf(pregunta)%2==0){
                tarjeta.setBackground(Color.GRAY);
            }else{
                tarjeta.setBackground(Color.DARK_GRAY);
            }
            panelCentro.add(tarjeta);
            listaTarjetasPregunta.add(tarjeta);                    
        }        
        scroll = new JScrollPane(panelCentro);        
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
        frame.add(scroll);
        frame.pack();
    }  
    
    private void construyePanelDerecho(){
        panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        JLabel notaLabelTitulo = new JLabel("Nota obtenida");
        notaLabel = new JLabel("---");
        Font aux=notaLabel.getFont();
        notaLabel.setFont(new Font(aux.getFontName(), aux.getStyle(), 35));        
        panelDerecho.add(notaLabelTitulo); 
        panelDerecho.add(notaLabel);
    }
        
    private void construyeVentana(){
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(900,700));
        frame.setBounds(400,50,900,700);
        frame.setTitle("CLIENTE - Examen Vitual");
        frame.add(encabezadoPanel,BorderLayout.NORTH);
        //frame.add(scroll, BorderLayout.CENTER);
        frame.add(panelInferior,BorderLayout.SOUTH);
        
        //frame.add(panelIzquierdo,BorderLayout.WEST);
        frame.add(panelDerecho,BorderLayout.EAST);              
                
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }            
    
    private void ManejadorEventos(){
        solicitarBoton.addActionListener((ActionEvent e) -> {            
            try {                
                Socket miSocket = new Socket("localhost", 8999);                
                ous = new ObjectOutputStream(miSocket.getOutputStream());
                ois = new ObjectInputStream(miSocket.getInputStream());                
                examen = (Examen) ois.readObject();
                System.out.println("... Examen recibido: "+ examen.getIdEstudiante());
                construyePanelCentro();  
                solicitarBoton.setEnabled(false);                
            }catch (UnknownHostException unhe){
                System.out.println(unhe.getMessage());
            }catch (IOException | ClassNotFoundException ioe) {
                System.out.println(ioe.getMessage());
            }            
        });
        calificarEnviarButon.addActionListener((ActionEvent e) -> {
            construirExamen();            
            System.out.println(examen.getNota());
            calificarEnviarButon.setEnabled(false);
            try {
                ous.writeObject(examen);
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }           
        });
    }
    
    private void construirExamen(){
        examen.setIdEstudiante(codigoEstudianteCampo.getText());
        for(int i=0; i<listaTarjetasPregunta.size(); i++){
            examen.getListaPreguntas().get(i).setRespuestaEstudiante(
                    opSelecionada(listaTarjetasPregunta.get(i).getGrupoOpciones()));
        }  
        notaLabel.setText(Float.toString(examen.getNota()));
        if(examen.getNota() < 3.0 ){
            notaLabel.setForeground(Color.RED);            
        }else if(examen.getNota() >= 3.0 && examen.getNota() < 4.5){
            notaLabel.setForeground(Color.YELLOW);            
        } else {
            notaLabel.setForeground(Color.GREEN);
        }
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

    public static void main (String [] string){
        new VentanaPrincipalCliente();
    }   
}