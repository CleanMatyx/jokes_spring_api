// src/main/java/com/matiasborra/jokes/model/entity/Category.java
package com.matiasborra.jokes.model.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories", schema = "public")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(name = "category", nullable = false)
	private String category;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Joke> jokes = new HashSet<>();

	public Category() {}
	// getters + setters

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }

	public Set<Joke> getJokes() { return jokes; }
	public void setJokes(Set<Joke> jokes) { this.jokes = jokes; }

	// helpers
	public void addJoke(Joke j) {
		jokes.add(j);
		j.setCategory(this);
	}
	public void removeJoke(Joke j) {
		jokes.remove(j);
		j.setCategory(null);
	}
}
