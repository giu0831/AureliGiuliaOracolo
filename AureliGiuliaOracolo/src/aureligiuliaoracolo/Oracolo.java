/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aureligiuliaoracolo;

import java.util.*;

/**
 * Classe Oracolo
 * @author utente
 */
public class Oracolo {
    private HashMap<String, ArrayList<String>> risposte;
    private String personalitaAttuale;

    /**
     * Metodo costruttore
     * @param personalita personalita dell'oracolo
     */
    public Oracolo(String personalita) {
        inizializzaPersonalita();
        if (!setPersonalitaAttuale(personalita)) {
        this.personalitaAttuale = "Aisa"; // Default di sicurezza
    }
    }
    
    /**
     * Metodo per inizializzare la mappa con le risposte in base alla personalita
     */
    private void inizializzaPersonalita(){
        risposte = new HashMap<>();

        ArrayList<String> risposteSaggio = new ArrayList<>();
        risposteSaggio.add("Le stelle dicono che la risposta è dentro di te.");
        risposteSaggio.add("Pazienza e tempo riveleranno ogni verità.");
        risposteSaggio.add("Ciò che cerchi si manifesterà a tempo debito.");
        risposte.put("SAGGIO", risposteSaggio);

        ArrayList<String> risposteBuso = new ArrayList<>();
        risposteBuso.add("La risposta e' sempre Eldego");
        risposteBuso.add("Non ti preoccupare, vai a comprare un trapano alla lidl");
        risposteBuso.add("67");
        risposte.put("Buso", risposteBuso);

        ArrayList<String> risposteAisa = new ArrayList<>();
        risposteAisa.add("La soluzione ai tuoi problemi e' un commentino su Parini.");
        risposteAisa.add("C'è sempre tempo per risolvere tutto, basta chiedere l'ora a Tassi.");
        risposteAisa.add("Ma ti sembra un comportamento corretto?");
        risposte.put("Aisa", risposteAisa);
    }

    /**
     * Metodo get
     * @return personalita attuale dell'oracolo
     */
    public String getPersonalitaAttuale() {
        return personalitaAttuale;
    }

    
    /**
     * Metodo set
     * @param personalita nuova personalita
     * @return true se la personalita e' stata cambiata, false altrimenti
     */
    public boolean setPersonalitaAttuale(String personalita) {
        if(controlloPersonalitaPresente(personalita)) {
            personalitaAttuale = personalita;
            return true;
        }
        return false;
        
    }
    
    /**
     * Metodo per vedere se la personalita e' presente nell'array 
     * @param personalita personalita da controllare
     * @return true se e' presente, altrimenti false 
     */
    private boolean controlloPersonalitaPresente(String personalita){
        return risposte.containsKey(personalita);
    }
    
    /**
     * Metodo per rispondere alle domande
     * @return risposta alla domanda
     */
    public String rispondi(){
        Random rnd = new Random();
        ArrayList<String> listaRisposte = risposte.get(personalitaAttuale);
        return listaRisposte.get(rnd.nextInt(listaRisposte.size()));
    }
}

