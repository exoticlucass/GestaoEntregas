package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class PedidoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PedidoDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_GestaoEntregasDAO_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
    }

    public Pedido read(int id) {
        return em.find(Pedido.class, id);
    }

    public void update(Pedido pedido) {
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Pedido pedido = em.find(Pedido.class, id);
        if (pedido != null) {
            em.remove(pedido);
        }
        em.getTransaction().commit();
    }

    public List<Pedido> listAll() {
        return em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
    }

    public Pedido selecionar(int id) {
        em.getTransaction().begin();
        Pedido x = em.find(Pedido.class, id);
        return x;
    }

    public List<Pedido> pesquisarData(String data) {
        var cb = em.getCriteriaBuilder();
        CriteriaQuery<Pedido> criteria = cb.createQuery(Pedido.class);
        var root = criteria.from(Pedido.class);
        criteria.select(root).where(cb.like(root.get("data"), "%" + data + "%"));
        List<Pedido> lista = em.createQuery(criteria).getResultList();
        return lista;
    }

    public List<Pedido> pesquisarPeriodo(Funcionario funcionario, Date startDate, Date endDate) {
        String jpql = "SELECT p FROM Pedido p WHERE p.funcionario = :funcionario AND p.dt BETWEEN :startDate AND :endDate";
        TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
        query.setParameter("funcionario", funcionario);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    public List<Pedido> pesquisarPedidos(String cpfCliente, String cpfFuncionario, String status, Date dataInicio, Date dataFim) {
        StringBuilder queryStr = new StringBuilder("SELECT p FROM Pedido p WHERE 1=1");

        if (cpfCliente != null && !cpfCliente.isEmpty()) {
            queryStr.append(" AND p.cliente.CPF = :cpfCliente");
        }
        if (cpfFuncionario != null && !cpfFuncionario.isEmpty()) {
            queryStr.append(" AND p.funcionario.CPF = :cpfFuncionario");
        }
        if (status != null && !status.isEmpty()) {
            queryStr.append(" AND p.status = :status");
        }
        if (dataInicio != null) {
            queryStr.append(" AND p.dt >= :dataInicio");
        }
        if (dataFim != null) {
            queryStr.append(" AND p.dt <= :dataFim");
        }

        TypedQuery<Pedido> query = em.createQuery(queryStr.toString(), Pedido.class);

        if (cpfCliente != null && !cpfCliente.isEmpty()) {
            query.setParameter("cpfCliente", cpfCliente);
        }
        if (cpfFuncionario != null && !cpfFuncionario.isEmpty()) {
            query.setParameter("cpfFuncionario", cpfFuncionario);
        }
        if (status != null && !status.isEmpty()) {
            if (status.equals("EM_PREPARAÇÃO")) {
                query.setParameter("status", Pedido.Status.EM_PREPARACAO);
            } else if (status.equals("ENTREGA")) {
                query.setParameter("status", Pedido.Status.ENTREGA);
            } else if (status.equals("ENTREGUE")) {
                query.setParameter("status", Pedido.Status.ENTREGUE);
            }
        }
        if (dataInicio != null) {
            query.setParameter("dataInicio", dataInicio);
        }
        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }

        return query.getResultList();
    }
}
