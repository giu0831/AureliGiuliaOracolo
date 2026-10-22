/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aureligiuliaoracoloclient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author utente
 */
public class AureliGiuliaOracoloClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //creazione form
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrmOracolo().setVisible(true);
            } catch (IOException ex) {
                System.getLogger(FrmOracolo.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });

    }
}
