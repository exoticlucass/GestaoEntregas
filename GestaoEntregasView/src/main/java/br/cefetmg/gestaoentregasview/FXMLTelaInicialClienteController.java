package br.cefetmg.gestaoentregasview;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import br.cefetmg.gestaoentregasentidades.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLTelaInicialClienteController implements Initializable {

    @FXML
    private Label welcomeLabel;

    private Cliente loggedInCliente;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    // Método para definir o Cliente
    public void setCliente(Cliente cliente) {
        this.loggedInCliente = cliente;
        welcomeLabel.setText("Bem-vindo, " + cliente.getNome());
    }
    
    
}
