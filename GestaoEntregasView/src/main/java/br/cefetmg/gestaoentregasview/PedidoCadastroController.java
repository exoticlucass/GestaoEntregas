/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregasview.ManipuladorArquivo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PedidoCadastroController {

    @FXML
    private ComboBox<String> comboBoxProduto;
    @FXML
    private TextField textFieldQuantidade;
    @FXML
    private TextField textFieldValorUnitario;
    @FXML
    private TextField textFieldValorTotal;
    @FXML
    private TextField textFieldMarca;
    @FXML
    private TextField textFieldFormaPagamento;
    @FXML
    private TextField textFieldEndereco;
    @FXML
    private TextArea textAreaObservacoes;
    @FXML
    private TextField textFieldEntregador;

    @FXML
    private void initialize() {
        comboBoxProduto.setItems(FXCollections.observableArrayList("Produto 1", "Produto 2", "Produto 3"));
    }

    @FXML
    public void salvarPedido() {

        String nomeProduto = comboBoxProduto.getSelectionModel().getSelectedItem();
        String endereco = textFieldEndereco.getText();
        String quantidade = textFieldQuantidade.getText();
        String valorUni = textFieldValorUnitario.getText();
        String valorTotal = textFieldValorTotal.getText();
        String marca = textFieldMarca.getText();
        String formaPagamento = textFieldFormaPagamento.getText();
        String areaObs = textAreaObservacoes.getText();
        String entregador = textFieldEntregador.getText();
        Alert alert = new Alert(AlertType.NONE);

        if (nomeProduto == null || endereco.length() == 0 || quantidade.length() == 0 || valorUni.length() == 0 || valorTotal.length() == 0 || marca.length() == 0 || formaPagamento.length() == 0 || areaObs.length() == 0 || entregador.length() == 0){
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Campos não preenchidos.");
            alert.show();
        }
        else{
        

        try {
            ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo("pedidos.txt");
            manipuladorArquivo.escreverFim(nomeProduto + ";" + endereco + ";" + quantidade + ";" + valorUni + ";" + valorTotal + ";" + marca + ";" + formaPagamento + ";" + areaObs + ";" + entregador + ";");
            alert.setAlertType(AlertType.INFORMATION);
            alert.setContentText("Pedido cadastrado com sucesso! ");
        } catch (Exception ex) {
            System.out.println(ex.toString());
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Error: " + ex.getMessage());
        }
        alert.show(); //exibe a mensagem
// Fechar a janela após salvar
        Stage stage = (Stage) textFieldEndereco.getScene().getWindow();
        stage.close();
        }
    }

    @FXML
    private void onCancelar() {
        Stage stage = (Stage) textFieldEndereco.getScene().getWindow();
        stage.close();

    }
}
