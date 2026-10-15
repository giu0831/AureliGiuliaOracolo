/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aureligiuliaoracolo;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author utente
 */
public class GestioneClienti implements Runnable {

    private Socket socket;
    private Oracolo oracolo;

    public GestioneClienti(Socket socket) {
        this.socket = socket;
        oracolo = new Oracolo("Aisa");
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            
            out.println("Sei connesso all'Oracolo. Personalità iniziale: " + oracolo.getPersonalitaAttuale());

            String messaggioClient;
            // Legge i messaggi finché la connessione rimane aperta
            while ((messaggioClient = in.readLine()) != null) {
                // Elabora il messaggio con il protocollo e invia la risposta
                String risposta = gestisciProtocollo(messaggioClient);
                out.println(risposta);
            }
        } catch (IOException e) {
            System.out.println("Client disconnesso.");
        } 
            chiudiSocket();
    }

    /**
     * Metodo per vedere che protocollo e' stato inserito
     *
     * @param messaggio messaggio del client
     * @return risposta
     */
    public String gestisciProtocollo(String messaggio) {
        //controllo per vedere se il messaggio e' vuoto
        if (messaggio == null || messaggio.isEmpty()) {
            return "Il Comando e' vuoto.";
        }
        //controllo per vedere se il protocollo e' help
        if (messaggio.equalsIgnoreCase("HELP")) {
            return "Comandi: \n - PERSONA_[nome personalita'] serve a cambiare personalita' dell'oracolo \n - DOMANDA_[testo domada] serve a fare una domanda all'oracolo \n - HELP serve a mostrare i comandi";
        }
        //divisione del messaggio in messaggio e parametro
        String[] messaggioDiviso = messaggio.split("_");
        String comando = messaggioDiviso[0];
        //controllo protocollo
        if (comando.equalsIgnoreCase("PERSONA")) {
            if (!oracolo.setPersonalitaAttuale(messaggioDiviso[1])) {
                return "Inserisci una personalita' esistente: \n - Aisa \n - Buso \n - SAGGIO";
            } else {
                return "Personalita' cambiata correttamente";
            }
        } else if (comando.equalsIgnoreCase("DOMANDA")) {
            return oracolo.rispondi();
        }
        return "Inserisci un comando esistente. Comandi: \n - PERSONA_[nome personalita'] serve a cambiare personalita' dell'oracolo \n - DOMANDA_[testo domada] serve a fare una domanda all'oracolo \n - HELP serve a mostrare i comandi";
    }
    
    /**
     * Metodo per chiudere la socket
     */
    public void chiudiSocket(){
        try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Errore durante la chiusura del socket: " + e.getMessage());
            }
    }

}
