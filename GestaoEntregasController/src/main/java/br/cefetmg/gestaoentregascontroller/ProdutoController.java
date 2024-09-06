/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.ProdutoDAO;
import br.cefetmg.gestaoentregasentidades.Produto;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ProdutoController {

    ProdutoDAO produtoDAO = new ProdutoDAO();

    public void salvarProduto(Produto produto) {
        produtoDAO.create(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listAll();
    }
    public List<Produto> pesquisarProdutos(String nome, String localizacao, Double valorUnitario) {
        return produtoDAO.pesquisarProdutos(nome, localizacao, valorUnitario);
    }
}
