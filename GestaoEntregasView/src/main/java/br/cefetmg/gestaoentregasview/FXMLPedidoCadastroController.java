package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregascontroller.ProdutoController;
import br.cefetmg.gestaoentregasentidades.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
    private TableView<ItemPedido> tableViewItens;

    @FXML
    private TableColumn<ItemPedido, String> colunaProduto;

    @FXML
    private TableColumn<ItemPedido, Integer> colunaQuantidade;

    @FXML
    private TableColumn<ItemPedido, Double> colunaValorTotal;
    private ObservableList<ItemPedido> listaItens;

    private Cliente loggedInCliente;

    public FXMLPedidoCadastroController() {
        this.pedidoController = new PedidoController(); // Inicializa o PedidoController
    }

    @FXML
    private void salvarPedido() {
        try {
            if (!listaItens.isEmpty()) {
                Pedido pedido = new Pedido();
                pedido.setCliente(loggedInCliente);
                pedido.setData(new Date());
                pedido.setItemPedido(new ArrayList<>(listaItens));
                pedido.setStatus(Pedido.Status.EM_PREPARACAO);
                pedido.setValorTotal(Double.parseDouble(textFieldValorTotal.getText()));

                pedidoController.salvarPedido(pedido);

                exibirAlerta("Sucesso", "Pedido salvo com sucesso!", AlertType.INFORMATION);
                limparCampos();
            } else {
                exibirAlerta("Nenhum Item", "Adicione pelo menos um item ao pedido.", AlertType.WARNING);
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
        limparCamposProduto();
        textFieldFormaPagamento.clear();
        textFieldEndereco.clear();
        textAreaObservacoes.clear();
        listaItens.clear();
        textFieldValorTotal.clear();
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

        colunaProduto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaValorTotal.setCellValueFactory(cellData -> {
            ItemPedido item = cellData.getValue();
            double valorTotal = item.getValorTotal();
            return new ReadOnlyObjectWrapper<>(valorTotal);
        });
        colunaValorTotal.setCellFactory(column -> new TableCell<ItemPedido, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : String.format("%.2f", item));
            }
        });
        listaItens = FXCollections.observableArrayList();
        tableViewItens.setItems(listaItens);

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

    @FXML
    private void adicionarItem() {
        if (verificarCamposPreenchidos()) {
            Produto produto = comboBoxProduto.getValue();
            int quantidade = Integer.parseInt(textFieldQuantidade.getText());
            double valorUnitario = Double.parseDouble(textFieldValorUnitario.getText().replace(',', '.'));
            double valorTotal = quantidade * valorUnitario;

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidade);

            listaItens.add(itemPedido);
            calcularValorTotalPedido(); // Recalcula o valor total do pedido com os itens adicionados

            limparCamposProduto(); // Limpa os campos de produto para novo item
        } else {
            exibirAlerta("Campos Incompletos", "Preencha todos os campos obrigatórios.", AlertType.WARNING);
        }
    }

    private void calcularValorTotalPedido() {
        double valorTotalPedido = listaItens.stream().mapToDouble(item -> item.getQuantidade() * item.getProduto().getValorUnitario()).sum();
        textFieldValorTotal.setText(String.format("%.2f", valorTotalPedido));
    }

    private void preencherCamposProduto(Produto produto) {
        textFieldValorUnitario.setText(String.valueOf(produto.getValorUnitario()));
        textFieldMarca.setText(produto.getNome());
    }

    private void limparCamposProduto() {
        comboBoxProduto.setValue(null);
        textFieldQuantidade.clear();
        textFieldValorUnitario.clear();
        textFieldMarca.clear();
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

    public void setCliente(Cliente cliente) {
        this.loggedInCliente = cliente;
    }
}
