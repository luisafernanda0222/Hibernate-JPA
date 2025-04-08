package dao;

import entidades.Veterinario;
import entidades.Mascota;
import jakarta.persistence.EntityManager;
import java.util.List;

public class VeterinarioDAO {
    private EntityManager em;

    public VeterinarioDAO(EntityManager em) {
        this.em = em;
    }

    public void registrar(Veterinario veterinario) {
        em.getTransaction().begin();
        em.persist(veterinario);
        em.getTransaction().commit();
    }

    public List<Veterinario> consultar() {
        return em.createQuery("SELECT v FROM Veterinario v", Veterinario.class).getResultList();
    }

    public Veterinario buscarPorId(int id) {
        return em.find(Veterinario.class, id);
    }

    public void actualizar(Veterinario veterinario) {
        em.getTransaction().begin();
        em.merge(veterinario);
        em.getTransaction().commit();
    }

    public void eliminar(int id) {
        Veterinario veterinario = em.find(Veterinario.class, id);
        if (veterinario != null) {
            em.getTransaction().begin();
            em.remove(veterinario);
            em.getTransaction().commit();
        }
    }

    public void asignarMascota(int idVeterinario, Mascota mascota) {
        Veterinario veterinario = em.find(Veterinario.class, idVeterinario);
        if (veterinario != null) {
            em.getTransaction().begin();
            veterinario.getMascotas().add(mascota);
            mascota.getVeterinarios().add(veterinario);
            em.merge(veterinario);
            em.merge(mascota);
            em.getTransaction().commit();
        }
    }
}
