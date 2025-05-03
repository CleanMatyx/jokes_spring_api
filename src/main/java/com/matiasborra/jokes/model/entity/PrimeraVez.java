package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="primera_vez")
public class PrimeraVez {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String programa;

    @Column(name="fecha_emision", nullable=false)
    private LocalDateTime fechaEmision;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="joke_id", unique=true)
    private Joke joke;

    @OneToMany(
            mappedBy="primeraVez",
            cascade=CascadeType.ALL,
            orphanRemoval=true
    )
    private List<Telefono> telefonos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}
