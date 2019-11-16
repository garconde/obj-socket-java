/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import main.TarjetaPreguntaCliente;

/**
 *
 * @author Win 10
 */
public class VentanaPrincipal2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        
        final int FRAME_WIDTH  = 700;
        final int FRAME_HEIGHT = 700;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Cliente Estudiante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel encabezadoPanel = new JPanel(new GridLayout(2, 1, 10, 10));        
      
        JLabel tituloLabel = new JLabel("Cliente");
        encabezadoPanel.add(tituloLabel);
        
        JPanel capturaTextoPanel = new JPanel(new FlowLayout());
        
        capturaTextoPanel.add(new JLabel("Código estudiante"));
        JButton solicitarBoton = new JButton("Solicitar examen");
        capturaTextoPanel.add(solicitarBoton);
        
        encabezadoPanel.add(capturaTextoPanel);
        

        JPanel preguntasPanel = new JPanel();
        preguntasPanel.setLayout(new BoxLayout(preguntasPanel, BoxLayout.Y_AXIS));
        preguntasPanel.setAutoscrolls(true);
        frame.add(preguntasPanel,BorderLayout.CENTER);

        JScrollPane scrollPanel = new JScrollPane(preguntasPanel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setBounds(10, 10, 700, 700);

        JPanel contenedorPanel = new JPanel(null);
        contenedorPanel.setPreferredSize(new Dimension(600, 600));
        contenedorPanel.add(scrollPanel);
        contenedorPanel.setBackground(Color.orange);
        frame.add(encabezadoPanel);
        frame.add(contenedorPanel, BorderLayout.CENTER);

        for(int i = 0; i < 10; i++) {
            
            TarjetaPreguntaCliente tarjetaPreguntaPanel = new TarjetaPreguntaCliente();
            //JPanel tarjetaPreguntaPanel = new JPanel();
            //tarjetaPreguntaPanel.setLayout(new BoxLayout(tarjetaPreguntaPanel, BoxLayout.Y_AXIS));
            if(i%2==0){
                tarjetaPreguntaPanel.setBackground(Color.GREEN);
            }else{
                tarjetaPreguntaPanel.setBackground(Color.PINK);
            }
            tarjetaPreguntaPanel.setPreferredSize(new Dimension(700, 180));                    
            preguntasPanel.add(tarjetaPreguntaPanel); 
        }

        //frame.add(tituloLabel, BorderLayout.NORTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
