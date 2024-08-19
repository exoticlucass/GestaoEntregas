/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoentregasentidades;

import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Master
 */
@Entity
public class Empresa {
    private static final Random RANDOM = new Random();
    public Empresa() {
        this.id = RANDOM.nextInt(10000);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String CNPJ;
    private String CPF;
    private double porcentagemComissaoEntregador;

    @OneToMany(fetch = FetchType.EAGER, cascade
            = CascadeType.PERSIST, mappedBy = "empresa")
    private List<Funcionario> funcionario;

    @OneToMany(fetch = FetchType.EAGER, cascade
            = CascadeType.PERSIST, mappedBy = "empresa")
    private List<Cliente> cliente;

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

    public double getPorcentagemComissaoEntregador() {
        return porcentagemComissaoEntregador;
    }

    public void setPorcentagemComissaoEntregador(double PorcentagemComissaoEntregador) {
        this.porcentagemComissaoEntregador = PorcentagemComissaoEntregador;
    }

    public List<Funcionario> getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(List<Funcionario> funcionario) {
        this.funcionario = funcionario;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }
    
}
