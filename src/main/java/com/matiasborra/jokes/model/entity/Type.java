package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase Type.
 * Representa un tipo de chiste con su identificador único y su descripción.
 * Contiene la relación con los chistes asociados.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "types", schema = "public")
public class Type implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "type", nullable = false)
	private String type;

	@OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<Joke> jokes = new HashSet<>();

	/**
	 * Constructor por defecto.
	 */
	public Type() {}

	/**
	 * Obtiene el identificador único del tipo.
	 *
	 * @return Identificador único del tipo
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el identificador único del tipo.
	 *
	 * @param id Identificador único del tipo
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene la descripción del tipo.
	 *
	 * @return Descripción del tipo
	 */
	public String getType() {
		return type;
	}

	/**
	 * Establece la descripción del tipo.
	 *
	 * @param type Descripción del tipo
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Obtiene el conjunto de chistes asociados al tipo.
	 *
	 * @return Conjunto de chistes asociados
	 */
	public Set<Joke> getJokes() {
		return jokes;
	}

	/**
	 * Establece el conjunto de chistes asociados al tipo.
	 *
	 * @param jokes Conjunto de chistes asociados
	 */
	public void setJokes(Set<Joke> jokes) {
		this.jokes = jokes;
	}
}