package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Produto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class FXMLProdutosListagemController {

    @FXML
    private TableView<Produto> tableViewProdutos;
    @FXML
    private TableColumn<Produto, Integer> columnId;
    @FXML
    private TableColumn<Produto, String> columnCpf;
    @FXML
    private TableColumn<Produto, String> columnNome;
    @FXML
    private TableColumn<Produto, String> columnLocalizacao;
    @FXML
    private TableColumn<Produto, Double> columnValorUnitario;

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;

    private ProdutoController produtoController;

    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();

    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
    }

    @FXML
    private void initialize() {
        produtoController = new ProdutoController();

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnLocalizacao.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        columnValorUnitario.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));


        carregarProdutos();
    }

    private void carregarProdutos() {
        ObservableList<Produto> produtos = FXCollections.observableArrayList(produtoController.listarProdutos());
        tableViewProdutos.setItems(produtos);
    }

    @FXML
    private void pesquisar() {
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();

        List<Produto> produtosFiltrados = produtoController.pesquisarProdutos(nome, telefone);
        tableViewProdutos.setItems(FXCollections.observableArrayList(produtosFiltrados));
    }


    @FXML
    private void voltarPagina() {
        r.loadScene("FXMLTelaInicialAdmAtendente.fxml", loggedInFuncionario, textFieldNome);
    }
}
