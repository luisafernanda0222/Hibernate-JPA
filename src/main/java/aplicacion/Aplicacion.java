package aplicacion;

import dao.CarnetVacunacionDAO;
import dao.DuenoDAO;
import dao.MascotaDAO;
import dao.VeterinarioDAO;
import entidades.CarnetVacunacion;
import entidades.Dueno;
import entidades.Mascota;
import entidades.Veterinario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.util.List;

public class Aplicacion {
    private EntityManagerFactory emf;
    private EntityManager em;
    private MascotaDAO mascotaDAO;
    private DuenoDAO duenoDAO;
    private VeterinarioDAO veterinarioDAO;
    private CarnetVacunacionDAO carnetVacunacionDAO;

    public void iniciar() {
        emf = Persistence.createEntityManagerFactory("PruebaHibernate");
        em = emf.createEntityManager();

        mascotaDAO = new MascotaDAO(em);
        duenoDAO = new DuenoDAO(em);
        veterinarioDAO = new VeterinarioDAO(em);
        carnetVacunacionDAO = new CarnetVacunacionDAO(em);

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("""
                MENÚ PRINCIPAL:
                1. Registrar Dueño
                2. Consultar Dueños
                3. Registrar Mascota
                4. Consultar Mascotas
                5. Registrar Veterinario
                6. Consultar Veterinarios
                7. Asignar Mascota a Veterinario
                8. Registrar Carnet de Vacunación
                9. Consultar Carnets de Vacunación
                10. Ver Dueños con sus Mascotas
                11. Ver Veterinarios con Mascotas Atendidas
                0. Salir
            """));

            switch (opcion) {
                case 1 -> registrarDueno();
                case 2 -> consultarDuenos();
                case 3 -> registrarMascota();
                case 4 -> consultarMascotas();
                case 5 -> registrarVeterinario();
                case 6 -> consultarVeterinarios();
                case 7 -> asignarMascotaAVeterinario();
                case 8 -> registrarCarnetVacunacion();
                case 9 -> consultarCarnets();
                case 10 -> verDuenosConMascotas();
                case 11 -> verVeterinariosConMascotas();
                case 0 -> JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida");
            }

        } while (opcion != 0);

        em.close();
        emf.close();
    }

    // MÉTODOS

    private void registrarDueno() {
        String nombre = JOptionPane.showInputDialog("Nombre del dueño:");
        Dueno dueno = new Dueno();
        dueno.setNombre(nombre);
        duenoDAO.registrar(dueno);
        JOptionPane.showMessageDialog(null, "Dueño registrado con éxito.");
    }

    private void consultarDuenos() {
        List<Dueno> lista = duenoDAO.consultar();
        StringBuilder sb = new StringBuilder("Dueños registrados:\n");
        for (Dueno d : lista) {
            sb.append("ID: ").append(d.getId_dueno()).append(", Nombre: ").append(d.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void registrarMascota() {
        String nombre = JOptionPane.showInputDialog("Nombre de la mascota:");
        String raza = JOptionPane.showInputDialog("Raza:");
        String color = JOptionPane.showInputDialog("Color:");
        String sexo = JOptionPane.showInputDialog("Sexo:");
        int idDueno = Integer.parseInt(JOptionPane.showInputDialog("ID del dueño:"));

        Dueno dueno = duenoDAO.buscarPorId(idDueno);
        if (dueno != null) {
            Mascota mascota = new Mascota();
            mascota.setNombre(nombre);
            mascota.setRaza(raza);
            mascota.setColor(color);
            mascota.setSexo(sexo);
            mascota.setDueno(dueno);

            mascotaDAO.registrar(mascota);
            JOptionPane.showMessageDialog(null, "Mascota registrada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Dueño no encontrado.");
        }
    }

    private void consultarMascotas() {
        List<Mascota> lista = mascotaDAO.consultar();
        StringBuilder sb = new StringBuilder("Mascotas registradas:\n");
        for (Mascota m : lista) {
            sb.append("ID: ").append(m.getId_mascota()).append(", Nombre: ").append(m.getNombre())
                    .append(", Dueño: ").append(m.getDueno().getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void registrarVeterinario() {
        String nombre = JOptionPane.showInputDialog("Nombre del veterinario:");
        Veterinario veterinario = new Veterinario();
        veterinario.setNombre(nombre);
        veterinarioDAO.registrar(veterinario);
        JOptionPane.showMessageDialog(null, "Veterinario registrado correctamente.");
    }

    private void consultarVeterinarios() {
        List<Veterinario> lista = veterinarioDAO.consultar();
        StringBuilder sb = new StringBuilder("Veterinarios registrados:\n");
        for (Veterinario v : lista) {
            sb.append("ID: ").append(v.getId_veterinario()).append(", Nombre: ").append(v.getNombre()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void asignarMascotaAVeterinario() {
        int idVet = Integer.parseInt(JOptionPane.showInputDialog("ID del veterinario:"));
        int idMascota = Integer.parseInt(JOptionPane.showInputDialog("ID de la mascota:"));

        Mascota mascota = mascotaDAO.buscarPorId(idMascota);
        if (mascota != null) {
            veterinarioDAO.asignarMascota(idVet, mascota);
            JOptionPane.showMessageDialog(null, "Mascota asignada al veterinario correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Mascota no encontrada.");
        }
    }

    private void registrarCarnetVacunacion() {
        int idMascota = Integer.parseInt(JOptionPane.showInputDialog("ID de la mascota:"));
        String nombreVacuna = JOptionPane.showInputDialog("Nombre de la vacuna:");
        String observaciones = JOptionPane.showInputDialog("Observaciones:");

        Mascota mascota = mascotaDAO.buscarPorId(idMascota);
        if (mascota != null) {
            CarnetVacunacion carnet = new CarnetVacunacion();
            carnet.setMascota(mascota);
            carnet.setNombreVacuna(nombreVacuna);
            carnet.setObservaciones(observaciones);

            carnetVacunacionDAO.registrar(carnet);
            JOptionPane.showMessageDialog(null, "Carnet de vacunación registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Mascota no encontrada.");
        }
    }

    private void consultarCarnets() {
        List<CarnetVacunacion> lista = carnetVacunacionDAO.consultar();
        StringBuilder sb = new StringBuilder("Carnets registrados:\n");
        for (CarnetVacunacion c : lista) {
            sb.append("ID: ").append(c.getId()).append(", Mascota: ").append(c.getMascota().getNombre())
                    .append(", Vacuna: ").append(c.getNombreVacuna()).append(", Obs: ").append(c.getObservaciones()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void verDuenosConMascotas() {
        List<Dueno> duenos = duenoDAO.consultar();
        StringBuilder sb = new StringBuilder("Dueños y sus mascotas:\n");

        for (Dueno d : duenos) {
            sb.append("Dueño: ").append(d.getNombre()).append(" (ID: ").append(d.getId_dueno()).append(")\n");
            for (Mascota m : d.getMascotas()) {
                sb.append("   Mascota: ").append(m.getNombre())
                        .append(", Raza: ").append(m.getRaza())
                        .append(", Color: ").append(m.getColor())
                        .append(", Sexo: ").append(m.getSexo()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void verVeterinariosConMascotas() {
        List<Veterinario> vets = veterinarioDAO.consultar();
        StringBuilder sb = new StringBuilder("Veterinarios con sus mascotas atendidas:\n");

        for (Veterinario v : vets) {
            sb.append("Veterinario: ").append(v.getNombre()).append(" (ID: ").append(v.getId()).append(")\n");
            for (Mascota m : v.getMascotas()) {
                sb.append("   Mascota: ").append(m.getNombre())
                        .append(", Raza: ").append(m.getRaza())
                        .append(", Color: ").append(m.getColor())
                        .append(", Sexo: ").append(m.getSexo()).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
