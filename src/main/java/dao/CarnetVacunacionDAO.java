package dao;

import entidades.CarnetVacunacion;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CarnetVacunacionDAO {
    private EntityManager em;

    public CarnetVacunacionDAO(EntityManager em) {
        this.em = em;
    }

    public void registrar(CarnetVacunacion carnet) {
        em.getTransaction().begin();
        em.persist(carnet);
        em.getTransaction().commit();
    }

    public List<CarnetVacunacion> consultar() {
        return em.createQuery("SELECT c FROM CarnetVacunacion c", CarnetVacunacion.class).getResultList();
    }

    public CarnetVacunacion buscarPorId(int id) {
        return em.find(CarnetVacunacion.class, id);
    }

    public void actualizar(CarnetVacunacion carnet) {
        em.getTransaction().begin();
        em.merge(carnet);
        em.getTransaction().commit();
    }

    public void eliminar(int id) {
        CarnetVacunacion carnet = em.find(CarnetVacunacion.class, id);
        if (carnet != null) {
            em.getTransaction().begin();
            em.remove(carnet);
            em.getTransaction().commit();
        }
    }
}
