package dao;

import entidades.Mascota;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MascotaDAO {
    private EntityManager em;

    public MascotaDAO(EntityManager em) {
        this.em = em;
    }

    public void registrar(Mascota mascota) {
        em.getTransaction().begin();
        em.persist(mascota);
        em.getTransaction().commit();
    }

    public List<Mascota> consultar() {
        return em.createQuery("SELECT m FROM Mascota m", Mascota.class).getResultList();
    }

    public Mascota buscarPorId(int id) {
        return em.find(Mascota.class, id);
    }

    public void actualizar(Mascota mascota) {
        em.getTransaction().begin();
        em.merge(mascota);
        em.getTransaction().commit();
    }

    public void eliminar(int id) {
        Mascota mascota = em.find(Mascota.class, id);
        if (mascota != null) {
            em.getTransaction().begin();
            em.remove(mascota);
            em.getTransaction().commit();
        }
    }

    public List<Mascota> consultarPorSexo(String sexo) {
        return em.createQuery("SELECT m FROM Mascota m WHERE m.sexo = :sexo", Mascota.class)
                .setParameter("sexo", sexo)
                .getResultList();
    }

    public void insertar(Mascota mascota) {
    }
}

