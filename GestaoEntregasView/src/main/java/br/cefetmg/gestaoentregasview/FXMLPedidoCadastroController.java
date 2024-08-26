package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasentidades.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FXMLPedidoCadastroController {

  /*  @FXML
    private ComboBox<Produto> comboBoxProduto;

    @FXML
    private TextField textFieldQuantidade;

    @FXML
    private TextField textFieldValorUnitario;

    @FXML
    private TextField textFieldValorTotal;

    @FXML
    private TextField textFieldMarca;

    @FXML
    private TextField textFieldFormaPagamento;

    @FXML
    private TextField textFieldEndereco;

    @FXML
    private TextArea textAreaObservacoes;

    private PedidoController pedidoController;

    public FXMLPedidoCadastroController() {
        this.pedidoController = new PedidoController(); // Inicializa o PedidoController
    }

    @FXML
    private void salvarPedido() {
        if (verificarCamposPreenchidos()) {
            Produto produto = comboBoxProduto.getValue();
            int quantidade = Integer.parseInt(textFieldQuantidade.getText());
            double valorUnitario = Double.parseDouble(textFieldValorUnitario.getText());
            double valorTotal = Double.parseDouble(textFieldValorTotal.getText());
            String marca = textFieldMarca.getText();
            String formaPagamento = textFieldFormaPagamento.getText();
            String endereco = textFieldEndereco.getText();
            String observacoes = textAreaObservacoes.getText();

            // Envia os dados para o PedidoController
            boolean sucesso = pedidoController.salvarPedido(produto, quantidade, valorUnitario, valorTotal, marca, formaPagamento, endereco, observacoes);

            if (sucesso) {
                exibirAlerta("Sucesso", "Pedido salvo com sucesso!", AlertType.INFORMATION);
                limparCampos();
            } else {
                exibirAlerta("Erro", "Erro ao salvar o pedido. Tente novamente.", AlertType.ERROR);
            }
        } else {
            exibirAlerta("Campos Incompletos", "Preencha todos os campos obrigatórios.", AlertType.WARNING);
        }
    }

    @FXML
    private void onCancelar() {
        limparCampos();
    }

    private boolean verificarCamposPreenchidos() {
        return comboBoxProduto.getValue() != null
                && !textFieldQuantidade.getText().isEmpty()
                && !textFieldValorUnitario.getText().isEmpty()
                && !textFieldValorTotal.getText().isEmpty()
                && !textFieldMarca.getText().isEmpty()
                && !textFieldFormaPagamento.getText().isEmpty()
                && !textFieldEndereco.getText().isEmpty();
    }

    private void limparCampos() {
        comboBoxProduto.setValue(null);
        textFieldQuantidade.clear();
        textFieldValorUnitario.clear();
        textFieldValorTotal.clear();
        textFieldMarca.clear();
        textFieldFormaPagamento.clear();
        textFieldEndereco.clear();
        textAreaObservacoes.clear();
    }

    private void exibirAlerta(String titulo, String mensagem, AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    private void initialize() {
        Produto p1 = new Produto();
        p1.setId(666);
        p1.setNome("produto1");
        p1.setLocalização("barreiro");
        p1.setItemPedido(new ItemPedido());
        
        List<Produto> produtos = new ArrayList<>();
        produtos.add(p1);
        comboBoxProduto.setItems(FXCollections.observableArrayList(produtos));
        FXPrincipal.addOnChangeScreenListener(new FXPrincipal.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newString, Object viewData) {
            }
        });
    } */
}
