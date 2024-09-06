package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.ItemPedido;
import br.cefetmg.gestaoentregasentidades.Pedido;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FXMLPedidosListagemController {

    @FXML
    private TableView<Pedido> tableViewPedidos;
    @FXML
    private TableColumn<Pedido, Integer> columnId;
    @FXML
    private TableColumn<Pedido, String> columnClienteCpf;
    @FXML
    private TableColumn<Pedido, String> columnFuncionarioCpf;
    @FXML
    private TableColumn<Pedido, String> columnData;
    @FXML
    private TableColumn<Pedido, Double> columnValorTotal;
    @FXML
    private TableColumn<Pedido, String> columnStatus;

    @FXML
    private TableView<ItemPedido> tableViewItensPedido;
    @FXML
    private TableColumn<ItemPedido, String> columnItemNome;
    @FXML
    private TableColumn<ItemPedido, Integer> columnItemQuantidade;
//    @FXML
//    private TableColumn<ItemPedido, Double> columnItemValorTotal;

    @FXML
    private TextField textFieldCpfCliente;
    @FXML
    private TextField textFieldCpfFuncionario;
    @FXML
    private TextField textFieldDataInicio;
    @FXML
    private TextField textFieldDataFim;
    @FXML
    private ComboBox<String> comboBoxStatus;

    private PedidoController pedidoController;
    
    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();

    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
    }

    @FXML
    private void initialize() {
        pedidoController = new PedidoController();


        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnClienteCpf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getCPF()));
        columnFuncionarioCpf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFuncionario().getCPF()));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Inicializar colunas da tabela de itens do pedido
        columnItemNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProduto().getNome()));
        columnItemQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
//        columnItemValorTotal.setCellValueFactory(cellData -> new PropertyValueFactory<>("valorTotal"));


        tableViewPedidos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mostrarDetalhesPedido(newSelection);
            }
        });


        comboBoxStatus.setItems(FXCollections.observableArrayList("EM_PREPARAÇÃO", "ENTREGA", "ENTREGUE"));

        carregarPedidos();
    }

    private void carregarPedidos() {
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList(pedidoController.listarPedidos());
        tableViewPedidos.setItems(pedidos);
    }

    @FXML
    private void pesquisar() {
        String cpfCliente = textFieldCpfCliente.getText();
        String cpfFuncionario = textFieldCpfFuncionario.getText();
        String status = comboBoxStatus.getValue();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date dataInicio = null;
        Date dataFim = null;

        try {
            if (!textFieldDataInicio.getText().isEmpty()) {
                dataInicio = sdf.parse(textFieldDataInicio.getText());
            }
            if (!textFieldDataFim.getText().isEmpty()) {
                dataFim = sdf.parse(textFieldDataFim.getText());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Pedido> pedidosFiltrados = pedidoController.pesquisarPedidos(cpfCliente, cpfFuncionario, status, dataInicio, dataFim);
        tableViewPedidos.setItems(FXCollections.observableArrayList(pedidosFiltrados));
    }

    private void mostrarDetalhesPedido(Pedido pedido) {
        ObservableList<ItemPedido> itensPedido = FXCollections.observableArrayList(pedido.getItemPedido());
        tableViewItensPedido.setItems(itensPedido);
    }
    @FXML
    private void voltarPagina() {
        r.loadScene("FXMLTelaInicialAdmAtendente.fxml", loggedInFuncionario, textFieldCpfCliente);
    }
}
