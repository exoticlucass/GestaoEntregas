package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregascontroller.EmpresaController;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Empresa;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;
import java.util.stream.Collectors;

public class CadastroCliente {

    @FXML
    private ComboBox<String> comboBoxProduto;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldLogradouro;
    @FXML
    private TextField textFieldBairro;
    @FXML
    private TextField textFieldCPF;

    @FXML
    private void initialize() {
        // Carregar a lista de empresas do banco de dados
        EmpresaController empresaController = new EmpresaController();
        List<Empresa> empresas = empresaController.listarTodas();

        // Adicionar os nomes das empresas ao ComboBox
        comboBoxProduto.setItems(FXCollections.observableArrayList(empresas.stream().map(Empresa::getNome).collect(Collectors.toList())
        ));
    }

    @FXML
    public void salvar() {
        Cliente cliente = new Cliente();
        cliente.setNome(textFieldNome.getText());
        cliente.setTelefone(textFieldTelefone.getText());
        cliente.setLogradouro(textFieldLogradouro.getText());
        cliente.setBairro(textFieldBairro.getText());
        cliente.setCPF(textFieldCPF.getText());
        ClienteController clienteController = new ClienteController();
        clienteController.inserir(cliente);
    }

    @FXML
    private void onCancelar() {
        // Fechar a janela ou limpar os campos, se necessário
    }
}
