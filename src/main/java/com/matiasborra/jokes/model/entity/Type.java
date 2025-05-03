package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "types", schema = "public")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "type", nullable = false)
	private String type;

	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Joke> jokes = new HashSet<>();

	public Type() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public Set<Joke> getJokes() { return jokes; }
	public void setJokes(Set<Joke> jokes) { this.jokes = jokes; }

	public void addJoke(Joke joke) {
		jokes.add(joke);
		joke.setType(this);
	}
	public void removeJoke(Joke joke) {
		jokes.remove(joke);
		joke.setType(null);
	}
}
