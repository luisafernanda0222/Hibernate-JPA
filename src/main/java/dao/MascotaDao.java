package dao;

import aplicacion.JPAUtil;
import entidades.Mascota;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class MascotaDao {

    public String registrarMascota(Mascota mascota) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(mascota);
        em.getTransaction().commit();
        em.close();
        return "Mascota registrada correctamente.";
    }

    public Mascota consultarMascota(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Mascota mascota = em.find(Mascota.class, id);
        em.close();
        return mascota;
    }

    public List<Mascota> consultarListaMascotas() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Mascota> lista = em.createQuery("SELECT m FROM Mascota m").getResultList();
        em.close();
        return lista;
    }

    public List<Mascota> consultarListaMascotasPorSexo(String sexo) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("SELECT m FROM Mascota m WHERE m.sexo = :sexo");
        query.setParameter("sexo", sexo);
        List<Mascota> lista = query.getResultList();
        em.close();
        return lista;
    }

    public String actualizarMascota(Mascota mascota) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.merge(mascota);
        em.getTransaction().commit();
        em.close();
        return "Mascota actualizada correctamente.";
    }

    public String eliminarMascota(Mascota mascota) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Mascota mascotaEliminar = em.find(Mascota.class, mascota.getIdMascota());
        if (mascotaEliminar != null) {
            em.remove(mascotaEliminar);
        }
        em.getTransaction().commit();
        em.close();
        return "Mascota eliminada correctamente.";
    }

    public void close() {
        JPAUtil.shutdown();
    }
}
