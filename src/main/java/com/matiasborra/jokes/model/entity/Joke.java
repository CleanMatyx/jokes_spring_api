package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Joke.
 * Representa un chiste con su categoría, idioma, tipo y banderas asociadas.
 * También puede estar vinculado a una entidad PrimeraVez.
 *
 * @author Matias Borra
 */
@Entity
@Table(name = "jokes", schema = "public")
public class Joke implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", nullable = false)
	private Language language;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private Type type;

	@Column(name = "text1", nullable = false)
	private String text1;

	@Column(name = "text2", nullable = false)
	private String text2;

	@OneToMany(mappedBy = "joke", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<JokeFlag> jokeFlags = new HashSet<>();

	@OneToOne(mappedBy = "joke", cascade = CascadeType.ALL, orphanRemoval = true,
			fetch = FetchType.LAZY, optional = true)
	private PrimeraVez primeraVez;

	@ManyToMany
	@JoinTable(name="jokes_flags", joinColumns = @JoinColumn(name="joke_id"),
			inverseJoinColumns = @JoinColumn(name="flag_id"))
	private Set<Flag> flags = new HashSet<>();

	/**
	 * Constructor por defecto.
	 */
	public Joke() {}

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
	 * Obtiene la categoría del chiste.
	 *
	 * @return Categoría del chiste
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Establece la categoría del chiste.
	 *
	 * @param category Categoría del chiste
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Obtiene el idioma del chiste.
	 *
	 * @return Idioma del chiste
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Establece el idioma del chiste.
	 *
	 * @param language Idioma del chiste
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * Obtiene el tipo del chiste.
	 *
	 * @return Tipo del chiste
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Establece el tipo del chiste.
	 *
	 * @param type Tipo del chiste
	 */
	public void setType(Type type) {
		this.type = type;
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
	 * Obtiene el conjunto de banderas asociadas al chiste.
	 *
	 * @return Conjunto de banderas asociadas
	 */
	public Set<JokeFlag> getJokeFlags() {
		return jokeFlags;
	}

	/**
	 * Establece el conjunto de banderas asociadas al chiste.
	 *
	 * @param jokeFlags Conjunto de banderas asociadas
	 */
	public void setJokeFlags(Set<JokeFlag> jokeFlags) {
		this.jokeFlags = jokeFlags;
	}

	/**
	 * Agrega una bandera al chiste.
	 *
	 * @param f Bandera a agregar
	 */
	public void addFlag(Flag f) {
		JokeFlag jf = new JokeFlag(this, f);
		jokeFlags.add(jf);
		f.getJokeFlags().add(jf);
	}

	/**
	 * Elimina una bandera del chiste.
	 *
	 * @param f Bandera a eliminar
	 */
	public void removeFlag(Flag f) {
		jokeFlags.removeIf(jf -> jf.getFlag().equals(f));
		f.getJokeFlags().removeIf(jf -> jf.getJoke().equals(this));
	}

	/**
	 * Obtiene la entidad PrimeraVez asociada al chiste.
	 *
	 * @return Entidad PrimeraVez asociada
	 */
	public PrimeraVez getPrimeraVez() {
		return primeraVez;
	}

	/**
	 * Establece la entidad PrimeraVez asociada al chiste.
	 *
	 * @param primeraVez Entidad PrimeraVez asociada
	 */
	public void setPrimeraVez(PrimeraVez primeraVez) {
		this.primeraVez = primeraVez;
	}

	/**
	 * Obtiene el conjunto de banderas asociadas al chiste.
	 *
	 * @return Conjunto de banderas asociadas
	 */
	public Set<Flag> getFlags() {
		return flags;
	}

	/**
	 * Establece el conjunto de banderas asociadas al chiste.
	 *
	 * @param flags Conjunto de banderas asociadas
	 */
	public void setFlags(Set<Flag> flags) {
		this.flags = flags;
	}
}