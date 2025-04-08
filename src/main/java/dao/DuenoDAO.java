package dao;

import entidades.Dueno;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DuenoDAO {
    private EntityManager em;

    public DuenoDAO(EntityManager em) {
        this.em = em;
    }

    public void registrar(Dueno dueno) {
        em.getTransaction().begin();
        em.persist(dueno);
        em.getTransaction().commit();
    }

    public List<Dueno> consultar() {
        return em.createQuery("SELECT d FROM Dueno d", Dueno.class).getResultList();
    }

    public Dueno buscarPorId(int id) {
        return em.find(Dueno.class, id);
    }

    public void actualizar(Dueno dueno) {
        em.getTransaction().begin();
        em.merge(dueno);
        em.getTransaction().commit();
    }

    public void eliminar(int id) {
        Dueno dueno = em.find(Dueno.class, id);
        if (dueno != null) {
            em.getTransaction().begin();
            em.remove(dueno);
            em.getTransaction().commit();
        }
    }
}
