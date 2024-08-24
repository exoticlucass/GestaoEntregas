/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasentidades;

import java.util.*;
import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "bairro")
    private String bairro;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "CNPJ_cliente")
    private String CNPJ;
    @Column(name = "CPF_cliente")
    private String CPF;
    private static final Random RANDOM = new Random();

    @OneToMany(fetch = FetchType.EAGER, cascade
            = CascadeType.PERSIST, mappedBy = "cliente")
    private List<Pedido> pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empresa_empresa")
    private Empresa empresa;

    public Cliente() {
        this.id = RANDOM.nextInt(10000);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

    public int getEmpresaId() {
        return empresa.getId();
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente(String nome, String logradouro, String bairro, String telefone, String CNPJ, String CPF, Empresa empresa) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.telefone = telefone;
        this.CNPJ = CNPJ;
        this.CPF = CPF;
        this.empresa = empresa;
        this.id = RANDOM.nextInt(10000);
    }

}
