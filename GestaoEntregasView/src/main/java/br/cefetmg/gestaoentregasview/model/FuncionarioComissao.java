/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregasview.model;

/**
 *
 * @author Master
 */
public class FuncionarioComissao {

    private final String nome;
    private final String cpf;
    private final double valorTotalVendido;
    private final double porcentagem;
    private final double comissao;

    public FuncionarioComissao(String nome, String cpf, double valorTotalVendido, double porcentagem, double comissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.valorTotalVendido = valorTotalVendido;
        this.porcentagem = porcentagem;
        this.comissao = comissao;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getValorTotalVendido() {
        return valorTotalVendido;
    }

    public double getPorcentagem() {
        return porcentagem;
    }

    public double getComissao() {
        return comissao;
    }
}
