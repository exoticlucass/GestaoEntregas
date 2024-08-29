package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.ClienteController;
import br.cefetmg.gestaoentregasentidades.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
        // Inicialização do controller, como carregar dados para o ComboBox, etc.
    }

    @FXML
    public void salvarPedido() {
        // Validação dos campos obrigatórios
        String nome = textFieldNome.getText();
        String telefone = textFieldTelefone.getText();
        String bairro = textFieldBairro.getText();
        String CPF = textFieldCPF.getText();
        String logradouro = textFieldLogradouro.getText();

        if (nome.isEmpty() || telefone.isEmpty() || bairro.isEmpty() || CPF.isEmpty() || logradouro.isEmpty()) {
            showAlert(AlertType.WARNING, "Campos incompletos", "Por favor, preencha todos os campos.");
            return;
        }

        try {
            // Criação do objeto Cliente com os dados do formulário
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setTelefone(telefone);
            cliente.setBairro(bairro);
            cliente.setCPF(CPF);
            cliente.setLogradouro(logradouro);

            // Instância do controller e chamada do método para inserir o cliente
            ClienteController controller = new ClienteController();
            controller.inserir(cliente);

            // Exibição de alerta de sucesso
            showAlert(AlertType.INFORMATION, "Sucesso", "Cliente cadastrado com sucesso.");
            onCancelar(); // Fechar a janela após salvar

        } catch (Exception e) {
            // Log do erro e exibição de alerta para o usuário
            e.printStackTrace(); // Exibe o erro completo no console
            showAlert(AlertType.ERROR, "Erro ao salvar", "Ocorreu um erro ao salvar o cliente.");
        }
    }

    @FXML
    private void onCancelar() {
        // Fechar a janela ou limpar os campos, se necessário
        if (textFieldNome.getScene() != null) {
            textFieldNome.getScene().getWindow().hide(); // Fecha a janela atual
        }
    }

    private void showAlert(AlertType type, String title, String message) {
        // Método para exibir alertas para o usuário
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
