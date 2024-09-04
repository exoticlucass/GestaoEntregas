package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Perfil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class FuncionarioDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public FuncionarioDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_GestaoEntregasDAO_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Funcionario funcionario) {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public Funcionario read(int id) {
        return em.find(Funcionario.class, id);
    }

    public void update(Funcionario funcionario) {
        em.getTransaction().begin();
        em.merge(funcionario);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Funcionario funcionario = em.find(Funcionario.class, id);
        if (funcionario != null) {
            em.remove(funcionario);
        }
        em.getTransaction().commit();
    }

    public List<Funcionario> listAll() {
        return em.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();
    }

    public Funcionario selecionar(int id) {
        em.getTransaction().begin();
        Funcionario x = em.find(Funcionario.class, id);
        return x;
    }

    public List<Funcionario> pesquisarFuncionarios(String cpf, String nome, String telefone, Double porcentagem, String tipos) {
        StringBuilder queryStr = new StringBuilder("SELECT f FROM Funcionario f WHERE 1=1");

        if (cpf != null && !cpf.isEmpty()) {
            queryStr.append(" AND f.CPF = :cpf");
        }
        if (nome != null && !nome.isEmpty()) {
            queryStr.append(" AND f.nome = :nome");
        }
        if (tipos != null && !tipos.isEmpty()) {
            queryStr.append(" AND f.perfil.tipo_perfil = :tipos");
        }
        if (telefone != null && !telefone.isEmpty()) {
            queryStr.append(" AND f.telefone = :tipos");
        }
        if (porcentagem != null) {
            queryStr.append(" AND f.porcentagem_comissao_entregador = :porcentagem");
        }

        TypedQuery<Funcionario> query = em.createQuery(queryStr.toString(), Funcionario.class);

        if (cpf != null && !cpf.isEmpty()) {
            query.setParameter("cpf", cpf);
        }
        if (nome != null && !nome.isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (telefone != null && !telefone.isEmpty()) {
            query.setParameter("telefone", telefone);
        }
        if (porcentagem != null) {
            query.setParameter("porcentagem", porcentagem);
        }
        if (tipos != null && !tipos.isEmpty()) {
            if (tipos.equals("ADMINISTRADOR")) {
                query.setParameter("tipos", Perfil.TipoPerfil.ADMINISTRADOR);
            } else if (tipos.equals("ATENDENTE")) {
                query.setParameter("status", Perfil.TipoPerfil.ATENDENTE);
            } else if (tipos.equals("CLIENTE")) {
                query.setParameter("status", Perfil.TipoPerfil.CLIENTE);
            } else if (tipos.equals("ENTREGADOR")) {
                query.setParameter("status", Perfil.TipoPerfil.ENTREGADOR);
            }
        }

        return query.getResultList();
    }
}
