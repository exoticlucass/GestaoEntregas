package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregascontroller.FuncionarioController;
import br.cefetmg.gestaoentregasentidades.Cliente;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final FuncionarioController funcionarioController = new FuncionarioController();
    private final ClienteController clienteController = new ClienteController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleLogin() {
        String cpf = usernameField.getText();
        String senha = passwordField.getText();

        Funcionario funcionario = funcionarioController.procurarCPF(cpf);
        if (funcionario != null && funcionarioController.validarSenha(funcionario, senha)) {
            Perfil perfil = funcionario.getPerfil();
            if (perfil.getTipoPerfil() == Perfil.TipoPerfil.ADMINISTRADOR
                    || perfil.getTipoPerfil() == Perfil.TipoPerfil.ATENDENTE) {
                loadScene("FXMLTelaInicialAdmAtendente.fxml", funcionario);
            } else if (perfil.getTipoPerfil() == Perfil.TipoPerfil.ENTREGADOR) {
                loadScene("FXMLTelaInicialEntregador.fxml", funcionario);
            }
            return;
        }

        Cliente cliente = clienteController.procurarCPF(cpf);
        if (cliente != null && clienteController.validarSenha(cliente, senha)) {
            showAlert("Login bem-sucedido", "Bem-vindo, " + cliente.getNome() + "!");
            // redirecionar para a de cliente depois
            return;
        }

        showAlert("Login falhou", "CPF ou senha incorretos. Por favor, tente novamente.");
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Erro", "Não foi possível carregar a tela: " + e.getMessage());
        }
    }

    private void loadScene(String fxmlFile, Funcionario funcionario) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Grama esse aqui é o codigo que passa se vc precisar (dentro do controller desse fxml tem a outra parte dele)
            if (fxmlFile.equals("FXMLTelaInicialAdmAtendente.fxml")) {
                FXMLTelaInicialAdmAtendenteController controller = loader.getController();
                controller.setFuncionario(funcionario);
            } else if (fxmlFile.equals("FXMLTelaInicialEntregador.fxml")) {
                FXMLTelaInicialEntregadorController controller = loader.getController();
                controller.setFuncionario(funcionario);
            }

            Stage stage = (Stage) usernameField.getScene().getWindow();
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
    private void handleCadastro() {
        loadScene("CadastroCliente.fxml");
    }
}
