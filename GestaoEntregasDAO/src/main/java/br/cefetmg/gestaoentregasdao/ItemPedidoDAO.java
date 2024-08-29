
package br.cefetmg.gestaoentregasdao;


import  br.cefetmg.gestaoentregasentidades.ItemPedido;
import br.cefetmg.gestaoentregasentidades.Pedido;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ItemPedidoDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ItemPedidoDAO() {
        emf = Persistence.createEntityManagerFactory("br.cefetmg_GestaoEntregasDAO_jar_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
    }

    public void create(ItemPedido itemPedido) {
        em.getTransaction().begin();
        em.persist(itemPedido);
        em.getTransaction().commit();
    }

    public ItemPedido read(int id) {
        return em.find(ItemPedido.class, id);
    }

    public void update(ItemPedido itemPedido) {
        em.getTransaction().begin();
        em.merge(itemPedido);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        ItemPedido itemPedido = em.find(ItemPedido.class, id);
        if (itemPedido != null) {
            em.remove(itemPedido);
        }
        em.getTransaction().commit();
    }

    public List<ItemPedido> listAll() {
        return em.createQuery("FROM ItemPedido", ItemPedido.class).getResultList();
    }

    public ItemPedido selecionar(int id) {
        em.getTransaction().begin();
        ItemPedido x = em.find(ItemPedido.class, id);
        return x;
    }
}
