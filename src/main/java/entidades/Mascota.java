package entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    private String nombre;
    private String raza;

    @Column(name = "color")
    private String colorMascota;

    private String sexo;

    @ManyToOne
    @JoinColumn(name = "id_dueno")
    private Dueno dueno;

    public Mascota() {}

    // Getters y Setters
    public Long getIdMascota() { return idMascota; }
    public void setIdMascota(Long idMascota) { this.idMascota = idMascota; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public String getColorMascota() { return colorMascota; }
    public void setColorMascota(String colorMascota) { this.colorMascota = colorMascota; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Dueno getDueno() { return dueno; }
    public void setDueno(Dueno dueno) { this.dueno = dueno; }

    @Override
    public String toString() {
        return "Mascota{" +
                "idMascota=" + idMascota +
                ", nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", color='" + colorMascota + '\'' +
                ", sexo='" + sexo + '\'' +
                ", dueno='" + (dueno != null ? dueno.getNombre() : "Sin dueño") + '\'' +
                '}';
    }
}
