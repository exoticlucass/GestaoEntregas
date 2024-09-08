package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
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

public class FXMLClientesListagemController {

    @FXML
    private TableView<Cliente> tableViewClientes;
    @FXML
    private TableColumn<Cliente, Integer> columnId;
    @FXML
    private TableColumn<Cliente, String> columnCpf;
    @FXML
    private TableColumn<Cliente, String> columnNome;
    @FXML
    private TableColumn<Cliente, String> columnTelefone;
    @FXML
    private TableColumn<Cliente, String> columnBairro;
    @FXML
    private TableColumn<Cliente, String> columnLogradouro;

    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;

    private ClienteController clienteController;

    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();

    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
    }

    @FXML
    private void initialize() {
        clienteController = new ClienteController();

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCpf.setCellValueFactory(new PropertyValueFactory<>("CPF"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        columnBairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        columnLogradouro.setCellValueFactory(new PropertyValueFactory<>("logradouro"));

        carregarFuncionarios();
    }

    private void carregarFuncionarios() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteController.listarClientes());
        tableViewClientes.setItems(clientes);
    }

    @FXML
    private void pesquisar() {
        String cpf = textFieldCpf.getText();
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();

        List<Cliente> clientesFiltrados = clienteController.pesquisarClientes(cpf, nome, telefone);
        tableViewClientes.setItems(FXCollections.observableArrayList(clientesFiltrados));
    }

    @FXML
    private void voltarPagina() {
        r.loadScene("FXMLTelaInicialAdmAtendente.fxml", loggedInFuncionario, textFieldCpf);
    }
}