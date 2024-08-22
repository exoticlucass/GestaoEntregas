/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.cefetmg.gestaoentregasview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import br.cefetmg.gestaoentregasview.ManipuladorArquivo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class FXMLDadosController {

    @FXML
    private TableView<Tabela> tabela;
    @FXML
    private TableColumn<Tabela, String> selectCol;
    @FXML
    private TableColumn<Tabela, String> nomeCol;
    @FXML
    private TableColumn<Tabela, String> quantidadeCol;
    @FXML
    private TableColumn<Tabela, String> enderecoCol;
    @FXML
    private TableColumn<Tabela, String> valorUniCol;
    @FXML
    private TableColumn<Tabela, String> valorTotalCol;
    @FXML
    private TableColumn<Tabela, String> formaPagamentoCol;
    @FXML
    private TableColumn<Tabela, String> marcaCol;
    @FXML
    private TableColumn<Tabela, String> observacoesCol;
    @FXML
    private TableColumn<Tabela, String> entregadorCol;

    public void initialize(URL location, ResourceBundle resources) throws IOException {
        selectCol.setCellValueFactory(
                new PropertyValueFactory<>("selected"));
        nomeCol.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
        quantidadeCol.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        enderecoCol.setCellValueFactory(
                new PropertyValueFactory<>("endereco"));
        valorUniCol.setCellValueFactory(
                new PropertyValueFactory<>("valorUnitario"));
        valorTotalCol.setCellValueFactory(
                new PropertyValueFactory<>("valorTotal"));
        formaPagamentoCol.setCellValueFactory(
                new PropertyValueFactory<>("formaPagamento"));
        marcaCol.setCellValueFactory(
                new PropertyValueFactory<>("marca"));
        observacoesCol.setCellValueFactory(
                new PropertyValueFactory<>("observacoes"));
        entregadorCol.setCellValueFactory(
                new PropertyValueFactory<>("entregador"));
        try {
            ManipuladorArquivo manipulador = new ManipuladorArquivo("pedidos.txt");
            ArrayList<String> linhas = manipulador.lerTudo();
            ArrayList<Tabela> linhasTabela = new ArrayList<Tabela>();
            for (int i = 0; i < linhas.size(); i++) {
                String[] partes = linhas.get(i).split(";");

                Tabela tb = new Tabela(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], partes[6], partes[7], partes[8]);
                linhasTabela.add(tb);
            }
            ObservableList<Tabela> lista = FXCollections.observableArrayList(linhasTabela);

            tabela.setItems(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
