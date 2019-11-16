/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Examen;

/**
 *
 * @author Win 10
 */
public class ManejadorCliente extends Thread {
        
    private Socket socketCliente;
    private Examen examenModelo;
    //Examen examenRecibido;

    public ManejadorCliente(Socket socketCliente, Examen examen) {       
        this.socketCliente = socketCliente;
        this.examenModelo = examen;
    }

    @Override
    public void run() {
        try {                
            //ServerSocket servidorSocket = new ServerSocket(8999);                           
            ObjectOutputStream ous = new ObjectOutputStream(socketCliente.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());            
            
            //Si fuera comunicación constante se pusiera dentro de un while(true)
            //con un comando break condicionado
            try {
                ous.writeObject(examenModelo);                    
                Examen examenRecibido = (Examen) ois.readObject();
                VentanaPrincipalServidor.listaExamen.add(examenRecibido);
                VentanaPrincipalServidor.agregarNotaEnPanel();                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(VentanaPrincipalServidor.class.getName()).log(Level.SEVERE, null, ex);
            }                            
        }catch (UnknownHostException unhe){
            System.out.println(unhe.getMessage());
        }catch (IOException ioe) {
            Logger.getLogger(VentanaPrincipalServidor.class.getName()).log(Level.SEVERE, null, ioe);
            System.out.println(ioe.getMessage());
        }
    }
    
    
    
    
    
}
