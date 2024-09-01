package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLTelaInicialEntregadorController implements Initializable {

    @FXML
    private Label welcomeLabel;

    private Funcionario loggedInFuncionario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialização básica
    }

    // Método para definir o Funcionario
    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
        welcomeLabel.setText("Bem-vindo, " + funcionario.getNome());
    }
}
