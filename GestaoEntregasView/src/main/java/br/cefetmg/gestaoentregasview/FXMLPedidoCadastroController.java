package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasentidades.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class FXMLPedidoCadastroController {

    @FXML
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
        try {
            if (verificarCamposPreenchidos()) {
                Produto produto = comboBoxProduto.getValue();
                int quantidade = Integer.parseInt(textFieldQuantidade.getText());
                double valorUnitario = Double.parseDouble(textFieldValorUnitario.getText().replace(',', '.'));
                double valorTotal = Double.parseDouble(textFieldValorTotal.getText().replace(',', '.'));

                String marca = textFieldMarca.getText();
                String formaPagamento = textFieldFormaPagamento.getText();
                String endereco = textFieldEndereco.getText();
                String observacoes = textAreaObservacoes.getText();

                ItemPedido itemPedido = new ItemPedido();

                itemPedido.setProduto(produto);
                itemPedido.setQuantidade(quantidade);

                Pedido pedido = new Pedido();
                Cliente cliente = new Cliente(); // teste
                cliente.setNome("Exotic");
                pedido.setCliente(cliente);
                Date dataAtual = new Date();
                pedido.setData(dataAtual);
                List<ItemPedido> listaitem = new ArrayList<>();
                listaitem.add(itemPedido);
                pedido.setItemPedido(listaitem);
                pedido.setStatus(Pedido.Status.EM_PREPARACAO);
                pedido.setValorTotal(valorTotal);

                // Envia os dados para o PedidoController
                pedidoController.salvarPedido(pedido);

                exibirAlerta("Sucesso", "Pedido salvo com sucesso!", AlertType.INFORMATION);
                limparCampos();
            } else {
                exibirAlerta("Campos Incompletos", "Preencha todos os campos obrigatórios.", AlertType.WARNING);
            }
        } catch (Exception e) {
            e.printStackTrace();
            exibirAlerta("Erro", "Erro ao salvar o pedido. Tente novamente.", AlertType.ERROR);
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
        textFieldValorUnitario.setEditable(false);
        textFieldValorTotal.setEditable(false);
        textFieldMarca.setEditable(false);
        ProdutoController pc = new ProdutoController();
        List<Produto> produtos = pc.listarProdutos();
        comboBoxProduto.setItems(FXCollections.observableArrayList(produtos));

        // Listener para recalcular o valor total quando a quantidade for alterada
        textFieldQuantidade.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());

        // Listener para recalcular o valor total quando o valor unitário for alterado
        textFieldValorUnitario.textProperty().addListener((observable, oldValue, newValue) -> calcularValorTotal());

        // Adiciona listener ao ComboBox para preencher os campos
        comboBoxProduto.getSelectionModel().selectedItemProperty().addListener((obs, oldProduto, newProduto) -> {
            if (newProduto != null) {
                preencherCamposProduto(newProduto);
            }
        });
    }

    private void preencherCamposProduto(Produto produto) {
        textFieldValorUnitario.setText(String.valueOf(produto.getValorUnitario()));
        textFieldMarca.setText(produto.getNome());
    }

    private void calcularValorTotal() {
        try {
            // Converter os valores para números
            double quantidade = Double.parseDouble(textFieldQuantidade.getText());
            double valorUnitario = Double.parseDouble(textFieldValorUnitario.getText());

            // Calcular o valor total
            double valorTotal = quantidade * valorUnitario;

            // Definir o valor total no campo correspondente
            textFieldValorTotal.setText(String.format("%.2f", valorTotal));
        } catch (NumberFormatException e) {
            // Em caso de erro de conversão, deixar o campo de valor total vazio
            textFieldValorTotal.setText("");
        }
    }
}
