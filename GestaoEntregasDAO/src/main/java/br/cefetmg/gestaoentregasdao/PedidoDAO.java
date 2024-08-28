package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
// import br.cefetmg.gestaoentregasentidades.Status; // Supondo que Status é um enum
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public class PedidoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public PedidoDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_gestaoentregasdao_jar_0.1.0-SNAPSHOTPU");
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
        return em.createQuery("FROM Pedido", Pedido.class).getResultList();
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
        criteria.select(root).where(cb.like(root.get("data"), "%"+data+"%"));
        List<Pedido> lista = em.createQuery(criteria).getResultList();
        return lista;
    }
    
    public List<Pedido> pesquisarPeriodo(Funcionario funcionario, Date startDate, Date endDate) {
        String jpql = "SELECT p FROM Pedido p JOIN p.funcionario f WHERE f = :funcionario AND p.dt BETWEEN :startDate AND :endDate";
        TypedQuery<Pedido> query = em.createQuery(jpql, Pedido.class);
        query.setParameter("funcionario", funcionario);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
