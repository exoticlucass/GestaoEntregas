package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregascontroller.PerfilController;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastroCliente {

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
    private TextField textFieldSenha;

    ClienteController clienteController;

    @FXML
    private void initialize() {
        this.clienteController = new ClienteController(); // Inicializa o PedidoController
    }

    @FXML
    private void salvarPedido() {
        try {
            if (verificarCamposPreenchidos()) {
                Cliente cliente = new Cliente(); // colocar cliente logado

                String nome = textFieldNome.getText();
                String telefone = textFieldTelefone.getText();
                String logradouro = textFieldLogradouro.getText();
                String bairro = textFieldBairro.getText();
                String cpf = textFieldCPF.getText();
                String senha = textFieldSenha.getText();

                Perfil perfil = new Perfil();
                perfil.setTipoPerfil(Perfil.TipoPerfil.CLIENTE);
                perfil.setSenha(senha);

                cliente.setBairro(bairro);
                cliente.setCPF(cpf);
                cliente.setCNPJ(cpf);
                cliente.setLogradouro(logradouro);
                cliente.setNome(nome);
                cliente.setPerfil(perfil);

                clienteController.salvarCliente(cliente);

                exibirAlerta("Sucesso", "Perfil cadastrado com sucesso!", AlertType.INFORMATION);
                limparCampos();
                loadScene("FXMLLoginController.fxml");
            } else {
                exibirAlerta("Campos Incompletos", "Preencha todos os campos obrigatórios.", AlertType.WARNING);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exibirAlerta("Erro", "Erro ao cadastrar o Perfil. Tente novamente.", AlertType.ERROR);
        }
    }

    private boolean verificarCamposPreenchidos() {
        return !textFieldNome.getText().isEmpty()
                && !textFieldTelefone.getText().isEmpty()
                && !textFieldLogradouro.getText().isEmpty()
                && !textFieldBairro.getText().isEmpty()
                && !textFieldCPF.getText().isEmpty()
                && !textFieldSenha.getText().isEmpty();
    }

    private void limparCampos() {
        textFieldNome.clear();
        textFieldTelefone.clear();
        textFieldLogradouro.clear();
        textFieldBairro.clear();
        textFieldCPF.clear();
        textFieldSenha.clear();
    }

    private void exibirAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) textFieldCPF.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onCancelar() {
        if (textFieldNome.getScene() != null) {
            textFieldNome.getScene().getWindow().hide();
            loadScene("FXMLLoginController.fxml");
        }
    }
}
