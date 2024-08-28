package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.util.Random;

public class CadastroAtendente {

    @FXML
    private ComboBox<String> comboBoxProduto;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldSenha;

    @FXML
    private void initialize() {
    }

    @FXML
    public void salvarPedido() {
        // Validação dos campos
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();
        String senha = textFieldSenha.getText();
        String nomeEmpresaSelecionada = comboBoxProduto.getSelectionModel().getSelectedItem();

        if (nome.isEmpty() || telefone.isEmpty() || senha.isEmpty() || nomeEmpresaSelecionada == null) {
            showAlert(AlertType.WARNING, "Campos incompletos", "Por favor, preencha todos os campos e selecione uma empresa.");
            return;
        }

        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setTelefone(telefone);
            funcionario.setSenha(senha);


            Perfil perfil = new Perfil();
            perfil.setTipoPerfilById(1); // Defina o tipo de perfil conforme necessário
            perfil.setId(new Random().nextInt(1000)); // ID aleatório para o perfil
            funcionario.setPerfil(perfil);
            perfil.setFuncionario(funcionario);

            FuncionarioController funcionarioController = new FuncionarioController();
            funcionarioController.inserir(funcionario, perfil);

            showAlert(AlertType.INFORMATION, "Sucesso", "Funcionário cadastrado com sucesso.");
            onCancelar(); // Fechar a janela após salvar

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "Erro ao salvar", "Ocorreu um erro ao salvar o funcionário.");
        }
    }

    @FXML
    private void onCancelar() {
        // Obtém a janela (Stage) atual e a fecha
        if (textFieldNome.getScene() != null) {
            textFieldNome.getScene().getWindow().hide();
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
