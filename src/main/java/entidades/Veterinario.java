package entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veterinarios")
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_veterinario;
    private String nombre;

    @ManyToMany
    @JoinTable(
            name = "mascotas_veterinarios",
            joinColumns = @JoinColumn(name = "veterinario_id"),
            inverseJoinColumns = @JoinColumn(name = "mascota_id")
    )
    private List<Mascota> mascotas = new ArrayList<>();

    public int getId_veterinario() {
        return id_veterinario;
    }

    public void setId_veterinario(int id_veterinario) {
        this.id_veterinario = id_veterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public int getId() {
        return id_veterinario;
    }
}