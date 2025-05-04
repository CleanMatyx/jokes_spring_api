package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;

/**
 * Clase Telefono.
 * Representa un teléfono asociado a una primera emisión de un programa.
 * Contiene el número de teléfono y la relación con la entidad PrimeraVez.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "telefonos")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primera_vez_id", nullable = false)
    private PrimeraVez primeraVez;

    /**
     * Obtiene el ID del teléfono.
     *
     * @return ID del teléfono
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del teléfono.
     *
     * @param id ID del teléfono
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el número del teléfono.
     *
     * @return Número del teléfono
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número del teléfono.
     *
     * @param numero Número del teléfono
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la primera emisión asociada al teléfono.
     *
     * @return Primera emisión asociada
     */
    public PrimeraVez getPrimeraVez() {
        return primeraVez;
    }

    /**
     * Establece la primera emisión asociada al teléfono.
     *
     * @param primeraVez Primera emisión asociada
     */
    public void setPrimeraVez(PrimeraVez primeraVez) {
        this.primeraVez = primeraVez;
    }
}