package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregasdao.ClienteDAO;
import br.cefetmg.gestaoentregasdao.EmpresaDAO;
import br.cefetmg.gestaoentregasdao.FuncionarioDAO;
import br.cefetmg.gestaoentregasdao.PerfilDAO;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Empresa;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;
import java.util.Random;
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
        EmpresaDAO empresaDAO = new EmpresaDAO();
        List<Empresa> empresas = empresaDAO.listarTodas();

        // Adicionar os nomes das empresas ao ComboBox
        comboBoxProduto.setItems(FXCollections.observableArrayList(empresas.stream().map(Empresa::getNome).collect(Collectors.toList())
        ));
    }

    @FXML
    public void salvarPedido() {
        Cliente cliente = new Cliente();
        cliente.setNome(textFieldNome.getText());
        cliente.setTelefone(textFieldTelefone.getText());
        cliente.setLogradouro(textFieldLogradouro.getText());
        cliente.setBairro(textFieldBairro.getText());
        cliente.setCPF(textFieldCPF.getText());
        ClienteDAO clienteDAO = new ClienteDAO();
        // clienteDAO.inserir(cliente);
    }

    @FXML
    private void onCancelar() {
        // Fechar a janela ou limpar os campos, se necessário
    }
}
