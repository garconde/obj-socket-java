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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ScrollTest2 {

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        final int FRAME_WIDTH  = 700;
        final int FRAME_HEIGHT = 700;

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Cliente Estudiante");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel tituloLabel = new JLabel("Cliente");

        JPanel preguntasPanel = new JPanel();
        preguntasPanel.setLayout(new BoxLayout(preguntasPanel, BoxLayout.Y_AXIS));
        preguntasPanel.setAutoscrolls(true);
        frame.add(preguntasPanel,BorderLayout.NORTH);

        JScrollPane scrollPanel = new JScrollPane(preguntasPanel);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setBounds(10, 10, 680, 680);

        JPanel contenedorPanel = new JPanel(null);
        contenedorPanel.setPreferredSize(new Dimension(700, 700));
        contenedorPanel.add(scrollPanel);
        contenedorPanel.setBackground(Color.orange);

        for(int i = 0; i < 10; i++) {
            
            //TarjetaPregunta tarjetaPreguntaPanel = new TarjetaPregunta();
            JPanel tarjetaPreguntaPanel = new JPanel();
            tarjetaPreguntaPanel.setLayout(new BoxLayout(tarjetaPreguntaPanel, BoxLayout.Y_AXIS));
            if(i%2==0){
                tarjetaPreguntaPanel.setBackground(Color.DARK_GRAY);
            }else{
                tarjetaPreguntaPanel.setBackground(Color.LIGHT_GRAY);
            }
            tarjetaPreguntaPanel.setPreferredSize(new Dimension(700, 180));

            JPanel ssp1 = new JPanel();
            //ssp1.setLayout(new FlowLayout());
            ssp1.setBackground(Color.GRAY);
            ssp1.setPreferredSize(new Dimension(300, 170));

            JPanel ssp2 = new JPanel();
            //ssp2.setLayout(new FlowLayout());
            ssp2.setBackground(Color.RED);
            ssp2.setPreferredSize(new Dimension(100, 170));

            JLabel l3 = new JLabel("Title: ");
            l3.setForeground(Color.BLACK);
            l3.setPreferredSize(new Dimension(100, 20));
            JTextField t1 = new JTextField("Electronic Basics");
            t1.setPreferredSize(new Dimension(320, 20));

            JLabel l4 = new JLabel("Type: ");
            l4.setForeground(Color.BLACK);
            l4.setPreferredSize(new Dimension(100, 20));
            JTextField t2 = new JTextField("Book");
            t2.setPreferredSize(new Dimension(320, 20));

            JLabel l5 = new JLabel("Authors: ");
            l5.setForeground(Color.BLACK);
            l5.setPreferredSize(new Dimension(100, 20));
            JTextField t3 = new JTextField("Bob the Builder");
            t3.setPreferredSize(new Dimension(320, 20));

            JLabel l6 = new JLabel("Publisher: ");
            l6.setForeground(Color.BLACK);
            l6.setPreferredSize(new Dimension(100, 20));
            JTextField t4 = new JTextField("ABC Company");
            t4.setPreferredSize(new Dimension(320, 20));

            JLabel l7 = new JLabel("Location: ");
            l7.setForeground(Color.BLACK);
            l7.setPreferredSize(new Dimension(100, 20));
            JTextField t5 = new JTextField("Shelf 1 Row 3");
            t5.setPreferredSize(new Dimension(320, 20));

            JLabel l8 = new JLabel("Status: ");
            l8.setForeground(Color.BLACK);
            l8.setPreferredSize(new Dimension(100, 20));
            JTextField t6 = new JTextField("Available");
            t6.setPreferredSize(new Dimension(320, 20));

            JButton btnLoanHistory = new JButton("Loan History");
            btnLoanHistory.setPreferredSize(new Dimension(300, 20));
            JButton btnLoanItem = new JButton("Loan Item");
            btnLoanItem.setPreferredSize(new Dimension(300, 20));
            JButton btnProcessReturn = new JButton("Process Return");
            btnProcessReturn.setPreferredSize(new Dimension(300, 20));

            ssp1.add(l3);
            ssp1.add(t1);
            ssp1.add(l4);
            ssp1.add(t2);
            ssp1.add(l5);
            ssp1.add(t3);
            ssp1.add(l6);
            ssp1.add(t4);
            ssp1.add(l7);
            ssp1.add(t5);
            ssp1.add(l8);
            ssp1.add(t6);

            ssp2.add(btnLoanHistory);
            ssp2.add(btnLoanItem);
            ssp2.add(btnProcessReturn);

            tarjetaPreguntaPanel.add(ssp1, BorderLayout.NORTH);
            tarjetaPreguntaPanel.add(ssp2, BorderLayout.SOUTH);            
            preguntasPanel.add(tarjetaPreguntaPanel); 
        }

        frame.add(tituloLabel, BorderLayout.NORTH);
        frame.add(contenedorPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

}
