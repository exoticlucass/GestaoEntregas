/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;
import br.cefetmg.gestaoentregasdao.*;
import br.cefetmg.gestaoentregasentidades.*;
import java.util.List;
/**
 *
 * @author Aluno
 */
public class Teste {
    public static void main(String[] args) {
        Produto p = new Produto();
        p.setNome("legal");
        p.setItemPedido(null);
        p.setValorUnitario(2);
        p.setLocalização("ali");
        ProdutoController pc = new ProdutoController();
        pc.salvarProduto(p);
        
        
        List<Produto> pl = pc.listarProdutos();
        
        System.out.println(pl.get(0).getNome());
    }
}
