package com.mycompany.gestaoentregascontroller;

import com.mycompany.gestaoentregasdao.ClienteDAO;
import com.mycompany.gestaoentregasdao.EmpresaDAO;
import com.mycompany.gestaoentregasdao.PedidoDAO;
import com.mycompany.gestaoentregasentidades.Cliente;
import com.mycompany.gestaoentregasentidades.Empresa;
import com.mycompany.gestaoentregasentidades.Pedido;
import java.util.Date;

public class Inserir {
    public static void main(String[] args) {
        // Create and insert Empresa
        Empresa empresa = new Empresa();
        empresa.setNome("Sarcofago");
        empresa.setCNPJ("123");
        empresa.setCPF("123");

        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresaDAO.inserir(empresa);

        // Create and insert Cliente
        Cliente cliente = new Cliente("Lucas", "Rua sarcofago", "Buritis", "3378-5339", "", "700-139-874-12", empresa);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.inserir(cliente);

        // Create and insert Pedido
        Pedido pedido = new Pedido(new Date(), 123.0, cliente); // Provide current date
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.inserir(pedido);
    }
}
