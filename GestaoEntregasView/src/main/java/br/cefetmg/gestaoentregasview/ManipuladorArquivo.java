/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasview;

import java.io.*;
import java.util.ArrayList;

public class ManipuladorArquivo {

    private String file;

    public ManipuladorArquivo(String file) {
        this.file = file;
    }

    public ArrayList<String> lerTudo() throws IOException {
        ArrayList<String> linhas = new ArrayList<>();
        // Utilizando try-with-resources para garantir que o BufferedReader seja fechado corretamente.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        }
        return linhas;
    }

    public void escreverFim(String linha) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter fwriter = new BufferedWriter(fw);
        fwriter.write(linha + "\n");
        fwriter.close();
    }
}
