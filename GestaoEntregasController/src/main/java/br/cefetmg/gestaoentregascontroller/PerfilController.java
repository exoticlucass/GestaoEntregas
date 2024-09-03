/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasdao.PerfilDAO;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class PerfilController {
    
    PerfilDAO perfilDAO = new PerfilDAO();

    public void salvarCliente(Perfil perfil) {
        perfilDAO.create(perfil);
    }

    public List<Perfil> listarClientes() {
        return perfilDAO.listAll();
    }
}
