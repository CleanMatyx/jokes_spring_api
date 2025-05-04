package com.matiasborra.jokes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO para representar la primera vez que se emite un chiste.
 * Contiene información sobre el programa, fecha de emisión, ID del chiste y teléfonos asociados.
 *
 * @author Matias Borra
 */
public class PrimeraVezDTO {

    private Long id;

    /**
     * Nombre del programa asociado.
     * No puede estar en blanco.
     */
    @NotBlank
    private String programa;

    /**
     * Fecha de emisión del chiste.
     * No puede ser nula.
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaEmision;

    /**
     * ID del chiste asociado.
     * No puede ser nulo.
     */
    @NotNull
    private Long jokeId;

    /**
     * Lista de teléfonos asociados al programa.
     * Debe contener al menos un teléfono válido.
     */
    @Size(min = 1, message = "Debes indicar al menos un teléfono")
    @Valid
    private List<TelefonoDTO> telefonos = new ArrayList<>();

    /**
     * Obtiene el ID de la primera vez.
     *
     * @return ID de la primera vez
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la primera vez.
     *
     * @param id ID de la primera vez
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del programa asociado.
     *
     * @return Nombre del programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Establece el nombre del programa asociado.
     *
     * @param programa Nombre del programa
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     * Obtiene la fecha de emisión del chiste.
     *
     * @return Fecha de emisión
     */
    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Establece la fecha de emisión del chiste.
     *
     * @param fechaEmision Fecha de emisión
     */
    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * Obtiene el ID del chiste asociado.
     *
     * @return ID del chiste
     */
    public Long getJokeId() {
        return jokeId;
    }

    /**
     * Establece el ID del chiste asociado.
     *
     * @param jokeId ID del chiste
     */
    public void setJokeId(Long jokeId) {
        this.jokeId = jokeId;
    }

    /**
     * Obtiene la lista de teléfonos asociados al programa.
     *
     * @return Lista de teléfonos
     */
    public List<TelefonoDTO> getTelefonos() {
        return telefonos;
    }

    /**
     * Establece la lista de teléfonos asociados al programa.
     *
     * @param telefonos Lista de teléfonos
     */
    public void setTelefonos(List<TelefonoDTO> telefonos) {
        this.telefonos = telefonos;
    }
}