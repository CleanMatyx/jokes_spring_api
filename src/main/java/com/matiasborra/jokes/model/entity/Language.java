package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase Language.
 * Representa un idioma con su código y nombre, y la relación con los chistes asociados.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "language", schema = "public")
public class Language implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "code", length = 2)
	private String code;

	@Column(name = "language")
	private String language;

	@OneToMany(mappedBy = "language", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<Joke> jokes = new HashSet<>();

	/**
	 * Constructor por defecto.
	 */
	public Language() {}

	/**
	 * Obtiene el ID del idioma.
	 *
	 * @return ID del idioma
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID del idioma.
	 *
	 * @param id ID del idioma
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el código del idioma.
	 *
	 * @return Código del idioma
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Establece el código del idioma.
	 *
	 * @param code Código del idioma
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Obtiene el nombre del idioma.
	 *
	 * @return Nombre del idioma
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Establece el nombre del idioma.
	 *
	 * @param language Nombre del idioma
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Obtiene el conjunto de chistes asociados al idioma.
	 *
	 * @return Conjunto de chistes asociados
	 */
	public Set<Joke> getJokes() {
		return jokes;
	}

	/**
	 * Establece el conjunto de chistes asociados al idioma.
	 *
	 * @param jokes Conjunto de chistes asociados
	 */
	public void setJokes(Set<Joke> jokes) {
		this.jokes = jokes;
	}
}