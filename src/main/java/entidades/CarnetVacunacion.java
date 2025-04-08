package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "carnet_vacunacion")
public class CarnetVacunacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String observaciones;
    private String nombreVacuna;

    @OneToOne
    @JoinColumn(name = "mascota_id", unique = true)
    private Mascota mascota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }
}
