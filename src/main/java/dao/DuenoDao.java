package dao;

import aplicacion.JPAUtil;
import entidades.Dueno;
import jakarta.persistence.EntityManager;

public class DuenoDao {

    public String registrarDueno(Dueno dueno) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(dueno);
        em.getTransaction().commit();
        em.close();
        return "Dueño registrado correctamente.";
    }

    public Dueno consultarDueno(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Dueno dueno = em.find(Dueno.class, id);
        em.close();
        return dueno;
    }
}
