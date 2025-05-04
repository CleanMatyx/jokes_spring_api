package com.matiasborra.jokes.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO para representar un chiste.
 * Contiene información sobre el texto, categoría, tipo, idioma, banderas y otros detalles.
 * Incluye validaciones y estructuras para diferentes usos (API Rest y Thymeleaf).
 *
 * @author Matias Borra
 */
public class JokeDTO {

    private Long id;
    private String text1;
    private String text2;
    private CategoryDTO category;
    private TypeDTO type;
    private LanguageDTO language;

    // Para la API Rest → lista de objetos completos
    private List<FlagDTO> flags = new ArrayList<>();

    // Para Thymeleaf → bind de los checkbox con los IDs
    private List<Long> flagIds = new ArrayList<>();

    // Datos de “primera vez”
    private String programa;
    private LocalDateTime fechaEmision;

    // Teléfonos del programa
    private List<String> telefonos;

    private PrimeraVezDTO primeraVez;

    /**
     * Obtiene el DTO de la primera vez.
     *
     * @return Objeto PrimeraVezDTO
     */
    public PrimeraVezDTO getPrimeraVez() {
        return primeraVez;
    }

    /**
     * Establece el DTO de la primera vez.
     *
     * @param primeraVez Objeto PrimeraVezDTO
     */
    public void setPrimeraVez(PrimeraVezDTO primeraVez) {
        this.primeraVez = primeraVez;
    }

    // --- getters / setters ---

    /**
     * Obtiene el ID del chiste.
     *
     * @return ID del chiste
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del chiste.
     *
     * @param id ID del chiste
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el primer texto del chiste.
     *
     * @return Primer texto del chiste
     */
    public String getText1() {
        return text1;
    }

    /**
     * Establece el primer texto del chiste.
     *
     * @param text1 Primer texto del chiste
     */
    public void setText1(String text1) {
        this.text1 = text1;
    }

    /**
     * Obtiene el segundo texto del chiste.
     *
     * @return Segundo texto del chiste
     */
    public String getText2() {
        return text2;
    }

    /**
     * Establece el segundo texto del chiste.
     *
     * @param text2 Segundo texto del chiste
     */
    public void setText2(String text2) {
        this.text2 = text2;
    }

    /**
     * Obtiene la categoría del chiste.
     *
     * @return Objeto CategoryDTO
     */
    public CategoryDTO getCategory() {
        return category;
    }

    /**
     * Establece la categoría del chiste.
     *
     * @param category Objeto CategoryDTO
     */
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    /**
     * Obtiene el tipo del chiste.
     *
     * @return Objeto TypeDTO
     */
    public TypeDTO getType() {
        return type;
    }

    /**
     * Establece el tipo del chiste.
     *
     * @param type Objeto TypeDTO
     */
    public void setType(TypeDTO type) {
        this.type = type;
    }

    /**
     * Obtiene el idioma del chiste.
     *
     * @return Objeto LanguageDTO
     */
    public LanguageDTO getLanguage() {
        return language;
    }

    /**
     * Establece el idioma del chiste.
     *
     * @param language Objeto LanguageDTO
     */
    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    /**
     * Obtiene la lista de banderas asociadas al chiste.
     *
     * @return Lista de objetos FlagDTO
     */
    public List<FlagDTO> getFlags() {
        return flags;
    }

    /**
     * Establece la lista de banderas asociadas al chiste.
     *
     * @param flags Lista de objetos FlagDTO
     */
    public void setFlags(List<FlagDTO> flags) {
        this.flags = flags;
    }

    /**
     * Obtiene la lista de IDs de banderas asociadas al chiste.
     *
     * @return Lista de IDs de banderas
     */
    public List<Long> getFlagIds() {
        return flagIds;
    }

    /**
     * Establece la lista de IDs de banderas asociadas al chiste.
     *
     * @param flagIds Lista de IDs de banderas
     */
    public void setFlagIds(List<Long> flagIds) {
        this.flagIds = flagIds;
    }

    /**
     * Obtiene el nombre del programa asociado al chiste.
     *
     * @return Nombre del programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * Establece el nombre del programa asociado al chiste.
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
     * Obtiene la lista de teléfonos del programa asociado al chiste.
     *
     * @return Lista de teléfonos
     */
    public List<String> getTelefonos() {
        return telefonos;
    }

    /**
     * Establece la lista de teléfonos del programa asociado al chiste.
     *
     * @param telefonos Lista de teléfonos
     */
    public void setTelefonos(List<String> telefonos) {
        this.telefonos = telefonos;
    }
}