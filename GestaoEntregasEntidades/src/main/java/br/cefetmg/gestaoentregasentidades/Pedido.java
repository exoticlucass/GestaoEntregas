package br.cefetmg.gestaoentregasentidades;

import java.util.*;
import javax.persistence.*;

@Entity
public class Pedido {

    private static final Random RANDOM = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_pedido")
    private java.util.Date dt; // Use java.util.Date for JPA

    @Column(name = "valor_total")
    private double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private Status status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "pedido")
    private List<ItemPedido> itemPedido;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionario_funcionario")
    private Funcionario funcionario;

    public enum Status {
        EM_PREPARACAO, ENTREGA, ENTREGUE;
    }

    // Default constructor required by JPA
    public Pedido() {
    }

    // Constructor for creating new Pedido instances
    public Pedido(java.util.Date dt, double valorTotal, Cliente cliente) {
        this.dt = dt;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.status = Status.EM_PREPARACAO; // Default status if needed
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.util.Date getData() {
        return dt;
    }

    public void setData(java.util.Date dt) {
        this.dt = dt;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ItemPedido> getItemPedido() {
        return itemPedido;
    }

    public void setItemPedido(List<ItemPedido> itemPedido) {
        this.itemPedido = itemPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getClienteId() {
        return cliente != null ? cliente.getId() : 0; // Ensure cliente is not null
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
