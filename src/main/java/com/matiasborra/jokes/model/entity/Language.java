package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "language", schema = "public")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", length = 2)
	private String code;

	@Column(name = "language")
	private String language;

	@OneToMany(mappedBy = "language", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Joke> jokes = new HashSet<>();

	public Language() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }

	public String getLanguage() { return language; }
	public void setLanguage(String language) { this.language = language; }

	public Set<Joke> getJokes() { return jokes; }
	public void setJokes(Set<Joke> jokes) { this.jokes = jokes; }

	public void addJoke(Joke joke) {
		jokes.add(joke);
		joke.setLanguage(this);
	}
	public void removeJoke(Joke joke) {
		jokes.remove(joke);
		joke.setLanguage(null);
	}
}
