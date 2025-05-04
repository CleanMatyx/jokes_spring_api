package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase PrimeraVez.
 * Representa la primera emisión de un programa asociado a un chiste, incluyendo los teléfonos relacionados.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "primera_vez")
public class PrimeraVez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String programa;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDateTime fechaEmision;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joke_id", unique = true)
    private Joke joke;

    @OneToMany(
            mappedBy = "primeraVez",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Telefono> telefonos = new ArrayList<>();

    /**
     * Obtiene el ID de la primera emisión.
     *
     * @return ID de la primera emisión
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la primera emisión.
     *
     * @param id ID de la primera emisión
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del programa.
     *
     * @return Nombre del programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Establece el nombre del programa.
     *
     * @param programa Nombre del programa
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     * Obtiene la fecha de emisión.
     *
     * @return Fecha de emisión
     */
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Establece la fecha de emisión.
     *
     * @param fechaEmision Fecha de emisión
     */
    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene el chiste asociado.
     *
     * @return Chiste asociado
     */
    public Joke getJoke() {
        return joke;
    }

    /**
     * Establece el chiste asociado.
     *
     * @param joke Chiste asociado
     */
    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    /**
     * Obtiene la lista de teléfonos asociados.
     *
     * @return Lista de teléfonos asociados
     */
    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    /**
     * Establece la lista de teléfonos asociados.
     *
     * @param telefonos Lista de teléfonos asociados
     */
    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}