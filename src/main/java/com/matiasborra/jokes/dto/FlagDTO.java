package com.matiasborra.jokes.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para representar una bandera.
 * Contiene validaciones para los campos.
 *
 * @author Matias Borra
 */
public class FlagDTO {

    private Long id;

    /**
     * Nombre de la bandera.
     * No puede estar en blanco.
     */
    @NotBlank(message = "{flag.notblank}")
    private String flag;

    /**
     * Constructor por defecto.
     */
    public FlagDTO() {}

    /**
     * Obtiene el ID de la bandera.
     *
     * @return ID de la bandera
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la bandera.
     *
     * @param id ID de la bandera
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la bandera.
     *
     * @return Nombre de la bandera
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Establece el nombre de la bandera.
     *
     * @param flag Nombre de la bandera
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}