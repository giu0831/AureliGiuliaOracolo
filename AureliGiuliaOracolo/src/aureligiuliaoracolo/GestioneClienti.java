/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aureligiuliaoracolo;

import java.io.*;
import java.net.Socket;

/**
 * Classe che gestisce le interazioni con il client
 * @author utente
 */
public class GestioneClienti implements Runnable {

    private Socket socket;
    private Oracolo oracolo;

    /**
     * Metodo costruttore
     * @param socket Socket 
     */
    public GestioneClienti(Socket socket) {
        this.socket = socket;
        //personalita' default
        oracolo = new Oracolo("Aisa");
    }

    @Override
    public void run() {
        try {
            //inizzializzazione variabili
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            //messaggio di inizio
            out.println("Sei connesso all'Oracolo. Personalita' iniziale: " + oracolo.getPersonalitaAttuale());
            //variabile del messaggio del client
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
        //chiusura socket
        chiudiSocket();
    }

    /**
     * Metodo per vedere che protocollo e' stato inserito
     * @param messaggio messaggio del client
     * @return risposta
     */
    public String gestisciProtocollo(String messaggio) {
        //controllo per vedere se il messaggio e' vuoto
        if (messaggio == null || messaggio.isEmpty()) {
            return "Il Comando e' vuoto.";
        }

        //divisione del messaggio in comando e parametro
        String[] messaggioDiviso = messaggio.split("_", 2);
        String comando = messaggioDiviso[0];
        //conrollo per vedere se è stato scritto bene un comando
        if (messaggioDiviso.length < 2 && !comando.equalsIgnoreCase("HELP")) {
            return "Inserisci un comando poi un _ , infine inserisci i parametri";
        }
        //controllo comando
        switch (comando) {
            case "HELP":
                return "I comandi sono PERSONA_[nome personalita'], DOMANDA_[testo domanda], FORTUNA_, HELP";

            case "PERSONA":
                if (!oracolo.setPersonalitaAttuale(messaggioDiviso[1])) {
                    return "Inserisci una personalita' esistente: Aisa, Buso, Tassi";
                } else {
                    return "Personalita' cambiata correttamente";
                }

            case "DOMANDA":
                if (messaggioDiviso[1].isBlank()) {
                    return "Inserisci una domanda dopo il comando";
                }
                return oracolo.rispondi();

            case "FORTUNA":
                return oracolo.vediFortuna();

            default:
                return "Inserisci un comando corretto";
        }
    }

    /**
     * Metodo per chiudere la socket
     */
    public void chiudiSocket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Errore durante la chiusura del socket: " + e.getMessage());
        }
    }

}
