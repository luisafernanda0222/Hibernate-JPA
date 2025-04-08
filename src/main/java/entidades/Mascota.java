package entidades;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_mascota;
    private String nombre;
    private String raza;
    private String color;
    private String sexo;

    @ManyToOne
    @JoinColumn(name = "id_dueno")
    private Dueno dueno;

    @ManyToMany(mappedBy = "mascotas")
    private List<Veterinario> veterinarios = new ArrayList<>();

    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL)
    private CarnetVacunacion carnet;

    public Mascota() {}

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Dueno getDueno() {
        return dueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public List<Veterinario> getVeterinarios() {
        return veterinarios;
    }

    public void setVeterinarios(List<Veterinario> veterinarios) {
        this.veterinarios = veterinarios;
    }

    public CarnetVacunacion getCarnet() {
        return carnet;
    }

    public void setCarnet(CarnetVacunacion carnet) {
        this.carnet = carnet;
    }

    public int getId() {
        return id_mascota;
    }
}