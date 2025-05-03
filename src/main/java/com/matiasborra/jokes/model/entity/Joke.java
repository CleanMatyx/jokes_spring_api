package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

	@OneToMany(
			mappedBy      = "joke",
			cascade       = CascadeType.ALL,
			orphanRemoval = true
	)
	private Set<JokeFlag> jokeFlags = new HashSet<>();

	public Joke() {}
	public Joke(Long id) { this.id = id; }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Category getCategory() { return category; }
	public void setCategory(Category category) { this.category = category; }

	public Language getLanguage() { return language; }
	public void setLanguage(Language language) { this.language = language; }

	public Type getType() { return type; }
	public void setType(Type type) { this.type = type; }

	public String getText1() { return text1; }
	public void setText1(String text1) { this.text1 = text1; }

	public String getText2() { return text2; }
	public void setText2(String text2) { this.text2 = text2; }

	public Set<JokeFlag> getJokeFlags() { return jokeFlags; }
	public void setJokeFlags(Set<JokeFlag> jokeFlags) { this.jokeFlags = jokeFlags; }

	// helpers para la relación
	public void addFlag(Flag flag) {
		JokeFlag jf = new JokeFlag(this, flag);
		jokeFlags.add(jf);
	}
	public void removeFlag(Flag flag) {
		jokeFlags.removeIf(jf -> jf.getFlag().equals(flag));
	}
}
