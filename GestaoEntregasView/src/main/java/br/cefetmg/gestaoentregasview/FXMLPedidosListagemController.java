package br.cefetmg.gestaoentregasview;

import br.cefetmg.gestaoentregascontroller.PedidoController;
import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import br.cefetmg.gestaoentregasentidades.Pedido.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLPedidosListagemController {

    @FXML
    private TableView<Pedido> tableViewPedidos;
    @FXML
    private TableColumn<Pedido, Integer> columnId;
    @FXML
    private TableColumn<Pedido, String> columnCliente;
    @FXML
    private TableColumn<Pedido, String> columnData;
    @FXML
    private TableColumn<Pedido, Double> columnValorTotal;
    @FXML
    private TableColumn<Pedido, String> columnStatus;
    @FXML
    private TableColumn<Pedido, Void> columnAcoes;

    private PedidoController pedidoController;
    private Funcionario loggedInFuncionario;
    public void setFuncionario(Funcionario funcionario) {
        this.loggedInFuncionario = funcionario;
    }

    @FXML
    private void initialize() {
        pedidoController = new PedidoController();

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnCliente.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCliente().getNome()));
        columnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        columnValorTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Defina a célula diretamente no código Java
        columnAcoes.setCellFactory(param -> new ButtonTableCell());

        carregarPedidos();
    }

    private void carregarPedidos() {
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList(pedidoController.listarPedidos());
        tableViewPedidos.setItems(pedidos);
    }

    private class ButtonTableCell extends TableCell<Pedido, Void> {

        private final Button btnAcao = new Button();

        public ButtonTableCell() {
            btnAcao.setOnAction(event -> {
                Pedido pedido = getTableView().getItems().get(getIndex());
                if (pedido.getStatus() == Status.EM_PREPARACAO) {
                    pedido.setStatus(Status.ENTREGA);
                    pedidoController.atualizarPedido(pedido);
                    btnAcao.setText("Terminar entrega");
                } else if (pedido.getStatus() == Status.ENTREGA) {
                    pedido.setStatus(Status.ENTREGUE);
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
                if (pedido.getStatus() == Status.EM_PREPARACAO) {
                    btnAcao.setText("Realizar entrega");
                } else if (pedido.getStatus() == Status.ENTREGA) {
                    btnAcao.setText("Terminar entrega");
                } else {
                    btnAcao.setText("Entregue");
                    btnAcao.setDisable(true);
                }
                setGraphic(btnAcao);
            }
        }
    }
}
