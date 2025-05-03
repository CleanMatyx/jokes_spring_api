package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="telefonos")
public class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primera_vez_id", nullable = false)
    private PrimeraVez primeraVez;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PrimeraVez getPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(PrimeraVez primeraVez) {
        this.primeraVez = primeraVez;
    }
}
