package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Category.
 * Representa una categoría que agrupa diferentes chistes.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "categories", schema = "public")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "category", nullable = false)
	private String category;

//	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Joke> jokes = new HashSet<>();

	@OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<Joke> jokes = new HashSet<>();

	/**
	 * Constructor por defecto.
	 */
	public Category() {}

	/**
	 * Obtiene el ID de la categoría.
	 *
	 * @return ID de la categoría
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID de la categoría.
	 *
	 * @param id ID de la categoría
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre de la categoría.
	 *
	 * @return Nombre de la categoría
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Establece el nombre de la categoría.
	 *
	 * @param category Nombre de la categoría
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Obtiene el conjunto de chistes asociados a la categoría.
	 *
	 * @return Conjunto de chistes
	 */
	public Set<Joke> getJokes() {
		return jokes;
	}

	/**
	 * Establece el conjunto de chistes asociados a la categoría.
	 *
	 * @param jokes Conjunto de chistes
	 */
	public void setJokes(Set<Joke> jokes) {
		this.jokes = jokes;
	}

	/**
	 * Agrega un chiste a la categoría.
	 *
	 * @param j Chiste a agregar
	 */
	public void addJoke(Joke j) {
		jokes.add(j);
		j.setCategory(this);
	}

	/**
	 * Elimina un chiste de la categoría.
	 *
	 * @param j Chiste a eliminar
	 */
	public void removeJoke(Joke j) {
		jokes.remove(j);
		j.setCategory(null);
	}
}