package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Flag.
 * Representa una bandera asociada a diferentes chistes.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "flags", schema = "public")
public class Flag implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "flag", nullable = false)
	private String flag;

	@OneToMany(mappedBy = "flag", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<JokeFlag> jokeFlags = new HashSet<>();

	/**
	 * Constructor por defecto.
	 */
	public Flag() {}

	/**
	 * Constructor con ID.
	 *
	 * @param id ID de la bandera
	 */
	public Flag(Long id) {
		this.id = id;
	}

	/**
	 * Constructor con ID y nombre de la bandera.
	 *
	 * @param id   ID de la bandera
	 * @param flag Nombre de la bandera
	 */
	public Flag(Long id, String flag) {
		this.id = id;
		this.flag = flag;
	}

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

	/**
	 * Obtiene el conjunto de relaciones JokeFlag asociadas a la bandera.
	 *
	 * @return Conjunto de relaciones JokeFlag
	 */
	public Set<JokeFlag> getJokeFlags() {
		return jokeFlags;
	}

	/**
	 * Establece el conjunto de relaciones JokeFlag asociadas a la bandera.
	 *
	 * @param jokeFlags Conjunto de relaciones JokeFlag
	 */
	public void setJokeFlags(Set<JokeFlag> jokeFlags) {
		this.jokeFlags = jokeFlags;
	}
}