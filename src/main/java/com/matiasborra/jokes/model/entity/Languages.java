package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;

import java.io.Serial;

/**
 * Clase Languages.
 * Representa un idioma con su identificador único y su nombre.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "languages", schema = "public")
public class Languages implements java.io.Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "language", nullable = false)
	private String language;

	/**
	 * Constructor por defecto.
	 */
	public Languages() {}

	/**
	 * Constructor con parámetros.
	 *
	 * @param id Identificador único del idioma
	 * @param language Nombre del idioma
	 */
	public Languages(int id, String language) {
		this.id = id;
		this.language = language;
	}

	/**
	 * Obtiene el identificador único del idioma.
	 *
	 * @return Identificador único del idioma
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Establece el identificador único del idioma.
	 *
	 * @param id Identificador único del idioma
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del idioma.
	 *
	 * @return Nombre del idioma
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * Establece el nombre del idioma.
	 *
	 * @param language Nombre del idioma
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}