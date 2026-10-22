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
    private HashMap<String, ArrayList<String>> risposteFortuna;
    private String personalitaAttuale;
    private boolean fortunaGiaChiesta;

    /**
     * Metodo costruttore
     * @param personalita personalita dell'oracolo
     */
    public Oracolo(String personalita) {
        inizializzaRisposte();
        inizializzaRisposteFortuna();
        if (!setPersonalitaAttuale(personalita)) {
        this.personalitaAttuale = "Aisa"; // Default di sicurezza
    }
    }
    
    /**
     * Metodo per inizializzare la mappa con le risposte in base alla personalita
     */
    private void inizializzaRisposte(){
        risposte = new HashMap<>();

        ArrayList<String> risposteTassi = new ArrayList<>();
        risposteTassi.add("Ti consiglio una pausa caffe'");
        risposteTassi.add("Nel tuo futuro vedo un premio. Il premio come pdm");
        risposteTassi.add("Se devi progammare, non usare c#");
        risposte.put("Tassi", risposteTassi);

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
     * Metodo per inizializzare la mappa con le risposte dedicate alla fortuna
     */
    private void inizializzaRisposteFortuna() {
        risposteFortuna = new HashMap<>();


        ArrayList<String> fortunaBuso = new ArrayList<>();
        fortunaBuso.add("Oggi fortuna pari a zero.");
        fortunaBuso.add("Fortuna a meta': chiedine un po' a eldego");
        fortunaBuso.add("Hai una fortuna altissima. Al livello 67!");
        fortunaBuso.add("Hai gia' la tuo risposta, per cambiarla chiedi a eldego.");
        risposteFortuna.put("Buso", fortunaBuso);

        ArrayList<String> fortunaAisa = new ArrayList<>();
        fortunaAisa.add("Oggi la sfortuna ti inseguira' come un'interrogazione a sorpresa di lunedi' prima ora.");
        fortunaAisa.add("Giornata senza lode e senza infamia: un po' come un 6 striminzito rimediato sul registro.");
        fortunaAisa.add("Oggi gli astri si sono allineati: è il giorno perfetto per farti interrogare e prendere 10!");
        fortunaAisa.add("Ma ti sembra un comportamento corretto farmi perdere tempo? ti e' gia' stata data una risposta.");
        risposteFortuna.put("Aisa", fortunaAisa);

        ArrayList<String> fortunaTassi = new ArrayList<>();
        fortunaTassi.add("Oggi sfortuna totale: la macchinetta del caffe' e' fuori servizio");
        fortunaTassi.add("Oggi giornata da 6 politico, non sei poi cosi' sfortunato.");
        fortunaTassi.add("Oggi fortuna alle stelle: vedo un caffe' offerto");
        fortunaTassi.add("Ancora? Guarda, ho meno voglia io di ripetertela di quanta ne abbia tu di ascoltare la lezione.");
        risposteFortuna.put("Tassi", fortunaTassi);
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
        return listaRisposte.get(rnd.nextInt(3));
    }
    
    /**
     * Metodo per rispondere alle domande
     * @return livello di fortuna
     */
    public String vediFortuna(){
        ArrayList<String> listaRisposte = risposteFortuna.get(personalitaAttuale);
        //controllo per vedere se e' gia' stata chiesta
        if(fortunaGiaChiesta)return listaRisposte.get(3);
        Random rnd = new Random();
        fortunaGiaChiesta = true;
        return listaRisposte.get(rnd.nextInt(3));
    }
}

