package br.cefetmg.gestaoentregasdao;

import br.cefetmg.gestaoentregasentidades.Funcionario;
import br.cefetmg.gestaoentregasentidades.Pedido;
// import br.cefetmg.gestaoentregasentidades.Status; // Supondo que Status é um enum
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
