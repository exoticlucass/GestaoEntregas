package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.FuncionarioController;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class FXMLRealizarEntregaController {

    @FXML
    private TableView<Pedido> tableViewPedidos;
    @FXML
    private TableColumn<Pedido, Integer> columnId;
    @FXML
    private TableColumn<Pedido, String> columnClienteCpf;
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
    @FXML
    private TableColumn<Pedido, Void> columnAcoes;
//    @FXML
//    private TableColumn<ItemPedido, Double> columnItemValorTotal;

    @FXML
    private TextField textFieldCpfCliente;
    @FXML
    private TextField textFieldDataInicio;
    @FXML
    private TextField textFieldDataFim;
    @FXML
    private ComboBox<String> comboBoxStatus;
    
    private PedidoController pedidoController;
    
    private FuncionarioController fc = new FuncionarioController();
    private Funcionario loggedInFuncionario;
    private FXRedirecionador r = new FXRedirecionador();

    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
        
        carregarPedidos();
    }

    @FXML
    private void initialize() {
        pedidoController = new PedidoController();

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnClienteCpf.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCliente().getCPF()));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        // Defina a célula diretamente no código Java
        columnAcoes.setCellFactory(param -> new ButtonTableCell());

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

    }

    private void carregarPedidos() {
        List<Pedido> pedidos = pedidoController.pesquisarPedidos("", loggedInFuncionario.getCPF(), "", null, null);
        tableViewPedidos.setItems(FXCollections.observableArrayList(pedidos));
    }

    @FXML
    private void pesquisar() {
        String cpfCliente = textFieldCpfCliente.getText();
        String cpfFuncionario = loggedInFuncionario.getCPF();
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
    
    
    private class ButtonTableCell extends TableCell<Pedido, Void> {

        private final Button btnAcao = new Button();

        public ButtonTableCell() {
            btnAcao.setOnAction(event -> {
                Pedido pedido = getTableView().getItems().get(getIndex());
                if (pedido.getStatus() == Pedido.Status.EM_PREPARACAO) {
                    pedido.setStatus(Pedido.Status.ENTREGA);
                    pedidoController.atualizarPedido(pedido);
                    btnAcao.setText("Terminar entrega");
                } else if (pedido.getStatus() == Pedido.Status.ENTREGA) {
                    pedido.setStatus(Pedido.Status.ENTREGUE);
                    pedidoController.atualizarPedido(pedido);
                    btnAcao.setDisable(true);
                }
                tableViewPedidos.refresh();
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                Pedido pedido = getTableView().getItems().get(getIndex());
                if (pedido.getStatus() == Pedido.Status.EM_PREPARACAO) {
                    btnAcao.setText("Realizar entrega");
                } else if (pedido.getStatus() == Pedido.Status.ENTREGA) {
                    btnAcao.setText("Terminar entrega");
                } else {
                    btnAcao.setText("Entregue");
                    btnAcao.setDisable(true);
                }
                setGraphic(btnAcao);
            }
        }
    }
    @FXML
    private void voltarPagina() {
        r.loadScene("FXMLTelaInicialEntregador.fxml", loggedInFuncionario, textFieldCpfCliente);
    }
}
