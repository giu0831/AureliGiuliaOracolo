/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aureligiuliaoracolo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author utente
 */
public class AureliGiuliaOracolo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket sSocket;
        Socket client;
        final int porta = 12345; 
        
        try {
            // Il Server risponde sulla porta 12345
            sSocket = new ServerSocket(porta);
            // Ciclo in attesa delle connesisoni
            System.out.println("In attesa di connessioni...");
            while (true) {
                client = sSocket.accept();
                System.out.println("Connessione stabilita con " + client.getInetAddress().toString());
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
}
