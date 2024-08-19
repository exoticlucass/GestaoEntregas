/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoentregascontroller;
import com.mycompany.gestaoentregasdao.ClienteDAO;
import com.mycompany.gestaoentregasdao.EmpresaDAO;
import com.mycompany.gestaoentregasentidades.Cliente;
import com.mycompany.gestaoentregasentidades.Empresa;

/**
 *
 * @author Aluno
 */
public class Inserir {
    public static void main(String[] args) {
        Empresa p = new Empresa();
        p.setNome("Sarcofago");
        p.setCNPJ("123");
        p.setCPF("123");

        EmpresaDAO dao = new EmpresaDAO();
        dao.inserir(p);
        Cliente cliente = new Cliente("Lucas", "Rua sarcofago", "Buritis", "3378-5339", "", "700-139-874-12", p);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.inserir(cliente);
    }
}
