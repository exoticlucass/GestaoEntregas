package br.cefetmg.gestaoentregascontroller;

import br.cefetmg.gestaoentregasentidades.Empresa;
import java.util.List;
import br.cefetmg.gestaoentregasdao.EmpresaDAO;

public class EmpresaController {

    private EmpresaDAO dao;

    public EmpresaController() {
        EmpresaDAO dao = new EmpresaDAO();
    }

    public List<Empresa> listarTodas() {
        return dao.listarTodas();
    }

    public Empresa pesquisar(String nome) {
        return dao.pesquisar(nome);
    }

    public void inserir(Empresa empresa) {
        dao.inserir(empresa);
    }

}
